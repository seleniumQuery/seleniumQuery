package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.PSEUDO_CLASS_VALUE_NOT_USED;
import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.SELECTOR_NOT_USED;
import io.github.seleniumquery.by.evaluator.DriverSupportMap;
import io.github.seleniumquery.by.evaluator.SelectorUtils;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class OnlyChildPseudoClass implements PseudoClass {
	
	private static final OnlyChildPseudoClass instance = new OnlyChildPseudoClass();
	public static OnlyChildPseudoClass getInstance() {
		return instance;
	}
	private OnlyChildPseudoClass() { }
	
	private static final String ONLY_CHILD_PSEUDO_CLASS_NO_COLON = "only-child";
	private static final String ONLY_CHILD_PSEUDO_CLASS = ":"+ONLY_CHILD_PSEUDO_CLASS_NO_COLON;
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return ONLY_CHILD_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		WebElement parent = SelectorUtils.parent(element);
		if (parent == null // parent is null when element is <HTML>
				|| parent.getTagName().equals("html")
				|| parent.getTagName().equals("body")
				|| parent.getTagName().equals("head")) {
			// I have tested and :only-child never worked direct children of those
			return false;
		}
		return SelectorUtils.itselfWithSiblings(element).size() == 1;
	}
	
	private static final SqCSSFilter onlyChildPseudoClassFilter = new PseudoClassFilter(getInstance(), SELECTOR_NOT_USED,
			PSEUDO_CLASS_VALUE_NOT_USED);

	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:only-child
		if (DriverSupportMap.getInstance().supportsNatively(driver, ONLY_CHILD_PSEUDO_CLASS)) {
			return CompiledSelector.createNoFilterSelector(ONLY_CHILD_PSEUDO_CLASS);
		}
		return CompiledSelector.createFilterOnlySelector(onlyChildPseudoClassFilter);
	}
	
}