package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.PSEUDO_CLASS_VALUE_NOT_USED;
import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.SELECTOR_NOT_USED;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class PresentPseudoClass implements PseudoClass {
	
	private static final PresentPseudoClass instance = new PresentPseudoClass();
	public static PresentPseudoClass getInstance() {
		return instance;
	}
	private PresentPseudoClass() { }
	
	private static final String PRESENT_PSEUDO_CLASS_NO_COLON = "present";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return PRESENT_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return isPresent(element);
	}
	
	public boolean isPresent(WebElement webElement) {
		try {
			// calling ANY method forces a staleness check
			webElement.isEnabled();
			// passed staleness check, thus present
			return true;
		} catch (StaleElementReferenceException expected) {
			// failed staleness check, so not present
			return false;
		}
	}
	
	private static final SqCSSFilter presentPseudoClassFilter = new PseudoClassFilter(getInstance(), SELECTOR_NOT_USED,
			PSEUDO_CLASS_VALUE_NOT_USED);

	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return CompiledSelector.createFilterOnlySelector(presentPseudoClassFilter);
	}
	
}