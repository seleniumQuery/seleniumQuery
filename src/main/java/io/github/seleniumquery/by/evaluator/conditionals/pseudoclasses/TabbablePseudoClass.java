package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.PSEUDO_CLASS_VALUE_NOT_USED;
import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.SELECTOR_NOT_USED;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

/**
 * :tabbable elements are all those :focusable, except those that have a negative tabindex.
 * 
 * From http://api.jqueryui.com/tabbable-selector/: "Elements with a negative tab index are :focusable, but not :tabbable."
 * 
 * @author acdcjunior
 * @since 1.0.0
 */
public class TabbablePseudoClass implements PseudoClass {
	
	private static final TabbablePseudoClass instance = new TabbablePseudoClass();
	public static TabbablePseudoClass getInstance() {
		return instance;
	}
	private TabbablePseudoClass() { }
	
	private static final String TABBABLE_PSEUDO_CLASS_NO_COLON = "tabbable";
	private static final FocusablePseudoClass focusable = FocusablePseudoClass.getInstance();
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return TABBABLE_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		boolean isFocusable = focusable.isPseudoClass(driver, element, selectorThisConditionShouldApply, pseudoClassValue);
		if (!isFocusable) {
			return false;
		}
		// at this point, it is focusable!
		String tabindex = element.getAttribute("tabindex");
		if (tabindex == null) {
			return true;
		}
		// at this point, there is a tabindex
		boolean tabindexIsNegativeInteger = tabindex.matches("\\s*-\\d+\\s*");
		return !tabindexIsNegativeInteger;
	}
	
	private static final SqCSSFilter tabbablePseudoClassFilter = new PseudoClassFilter(getInstance(), SELECTOR_NOT_USED, PSEUDO_CLASS_VALUE_NOT_USED);
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		// no browser supports :tabbable natively
		return CompiledSelector.createFilterOnlySelector(tabbablePseudoClassFilter);
	}
	
}