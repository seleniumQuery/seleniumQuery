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

public class FirstChildPseudoClass implements PseudoClass {

	private static final FirstChildPseudoClass instance = new FirstChildPseudoClass();
	public static FirstChildPseudoClass getInstance() {
		return instance;
	}
	private FirstChildPseudoClass() { }
	
	private static final String FIRST_CHILD_PSEUDO_CLASS_NO_COLON = "first-child";
	private static final String FIRST_CHILD_PSEUDO_CLASS = ":"+FIRST_CHILD_PSEUDO_CLASS_NO_COLON;

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return FIRST_CHILD_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		WebElement parent = SelectorUtils.parent(element);
		// parent is null when element is <HTML>
		if (parent == null) {
			return false;
		}
		return SelectorUtils.itselfWithSiblings(element).get(0).equals(element);
	}

	private static final SqCSSFilter firstChildPseudoClassFilter = new PseudoClassFilter(getInstance(), SELECTOR_NOT_USED,
			PSEUDO_CLASS_VALUE_NOT_USED);

	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:first-child
		if (DriverSupportMap.getInstance().supportsNatively(driver, FIRST_CHILD_PSEUDO_CLASS)) {
			return CompiledSelector.createNoFilterSelector(FIRST_CHILD_PSEUDO_CLASS);
		}
		return CompiledSelector.createFilterOnlySelector(firstChildPseudoClassFilter);
	}

}