package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.PSEUDO_CLASS_VALUE_NOT_USED;
import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.SELECTOR_NOT_USED;
import io.github.seleniumquery.by.evaluator.DriverSupportMap;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class RootPseudoClass implements PseudoClass {
	
	private static final RootPseudoClass instance = new RootPseudoClass();
	public static RootPseudoClass getInstance() {
		return instance;
	}
	private RootPseudoClass() { }
	
	private static final String ROOT_PSEUDO_CLASS_NO_COLON = "root";
	private static final String ROOT_PSEUDO_CLASS = ":"+ROOT_PSEUDO_CLASS_NO_COLON;
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return ROOT_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return element.getTagName().equals("html");
	}
	
	private static final SqCSSFilter rootPseudoClassFilter = new PseudoClassFilter(getInstance(), SELECTOR_NOT_USED,
			PSEUDO_CLASS_VALUE_NOT_USED);

	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:root
		if (DriverSupportMap.getInstance().supportsNatively(driver, ROOT_PSEUDO_CLASS)) {
			return CompiledSelector.createNoFilterSelector(ROOT_PSEUDO_CLASS);
		}
		return CompiledSelector.createFilterOnlySelector(rootPseudoClassFilter);
	}
	
}