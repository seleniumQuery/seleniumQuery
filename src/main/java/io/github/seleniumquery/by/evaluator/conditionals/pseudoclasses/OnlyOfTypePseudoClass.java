package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.PSEUDO_CLASS_VALUE_NOT_USED;
import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.SELECTOR_NOT_USED;
import io.github.seleniumquery.by.evaluator.DriverSupportMap;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class OnlyOfTypePseudoClass implements PseudoClass {
	
	private static final OnlyOfTypePseudoClass instance = new OnlyOfTypePseudoClass();
	public static OnlyOfTypePseudoClass getInstance() {
		return instance;
	}
	private OnlyOfTypePseudoClass() { }
	
	private static final String ONLY_OF_TYPE_PSEUDO_CLASS_NO_COLON = "only-of-type";
	private static final String ONLY_OF_TYPE_PSEUDO_CLASS = ":"+ONLY_OF_TYPE_PSEUDO_CLASS_NO_COLON;
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return ONLY_OF_TYPE_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		String tagName = element.getTagName();
		return driver.findElements(By.tagName(tagName)).size() == 1;
	}
	
	private static final SqCSSFilter onlyOfTypePseudoClassFilter = new PseudoClassFilter(getInstance(), SELECTOR_NOT_USED,
			PSEUDO_CLASS_VALUE_NOT_USED);

	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:only-of-type
		if (DriverSupportMap.getInstance().supportsNatively(driver, ONLY_OF_TYPE_PSEUDO_CLASS)) {
			return CompiledSelector.createNoFilterSelector(ONLY_OF_TYPE_PSEUDO_CLASS);
		}
		return CompiledSelector.createFilterOnlySelector(onlyOfTypePseudoClassFilter);
	}
	
}