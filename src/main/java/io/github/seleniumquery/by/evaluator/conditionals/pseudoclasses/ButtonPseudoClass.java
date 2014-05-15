package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.PSEUDO_CLASS_VALUE_NOT_USED;
import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.SELECTOR_NOT_USED;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

/**
 * http://api.jquery.com/button-selector/
 * 
 * @since 1.0.0
 * @author acdcjunior
 */
public class ButtonPseudoClass implements PseudoClass {
	
	private static final ButtonPseudoClass instance = new ButtonPseudoClass();
	public static ButtonPseudoClass getInstance() {
		return instance;
	}
	private ButtonPseudoClass() { }
	
	private static final String INPUT = "input";
	private static final String BUTTON = "button";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return BUTTON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return (INPUT.equals(element.getTagName()) && BUTTON.equalsIgnoreCase(element.getAttribute("type")))
				||
			   BUTTON.equals(element.getTagName());
	}
	
	private static final SqCSSFilter buttonPseudoClassFilter = new PseudoClassFilter(getInstance(),
																		SELECTOR_NOT_USED, PSEUDO_CLASS_VALUE_NOT_USED);
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		// :button is an extension selector, nobody implements it natively
		return CompiledSelector.createFilterOnlySelector(buttonPseudoClassFilter);
	}
	
}