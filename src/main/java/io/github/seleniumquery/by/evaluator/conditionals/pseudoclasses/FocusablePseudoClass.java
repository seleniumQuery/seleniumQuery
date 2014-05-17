package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.PSEUDO_CLASS_VALUE_NOT_USED;
import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.SELECTOR_NOT_USED;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

/**
 * @see http://api.jqueryui.com/focusable-selector/
 * 
 * 
 * Some elements are natively focusable, while others require explicitly setting a tab index. In all cases, the element must be visible in order to be focusable.
 * 
 * Elements of the following type are focusable if they are not disabled:
 * - If VISIBLE and NOT DISABLED:
 * 	- input,
 * 	- select,
 * 	- textarea,
 *  - button.
 * 
 * - Anchors are focusable if they have an href or tabindex attribute.
 * 
 * - area elements are focusable if they are inside a named map, have an href attribute, and there is a visible image using the map.
 * 
 *  All other elements are focusable based solely on their tabindex attribute and visibility.
 * 
 * @author acdcjunior
 */
public class FocusablePseudoClass implements PseudoClass {
	
	private static final FocusablePseudoClass instance = new FocusablePseudoClass();
	public static FocusablePseudoClass getInstance() {
		return instance;
	}
	private FocusablePseudoClass() { }
	
	private static final String FOCUSABLE_PSEUDO_CLASS_NO_COLON = "focusable";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return FOCUSABLE_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		if (!element.isDisplayed()) {
			return false;
		}
		if (DisabledPseudoClass.DISABLEABLE_TAGS.contains(element.getTagName())) {
			return element.isEnabled();
		}
		if (element.getTagName().equals("a") && element.getAttribute("href") != null) {
			return true;
		}
		if (element.getTagName().equals("area") && element.getAttribute("href") != null /* && are inside a named map */ /* && there is a visible image using the map */) {
			return true;
		}
		return element.getAttribute("tabindex") != null;
	}
	
	private static final SqCSSFilter focusablePseudoClassFilter = new PseudoClassFilter(getInstance(),
																		SELECTOR_NOT_USED, PSEUDO_CLASS_VALUE_NOT_USED);
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		// no browser supports :focusable natively
		return CompiledSelector.createFilterOnlySelector(focusablePseudoClassFilter);
	}
	
}