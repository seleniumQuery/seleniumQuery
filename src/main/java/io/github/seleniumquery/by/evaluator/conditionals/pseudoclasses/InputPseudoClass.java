package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.PSEUDO_CLASS_VALUE_NOT_USED;
import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.SELECTOR_NOT_USED;

import java.util.Arrays;
import java.util.List;

import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

/**
 * http://api.jquery.com/input-selector/
 * 
 * @since 1.0.0
 * @author acdcjunior
 */
public class InputPseudoClass implements PseudoClass {
	
	private static final InputPseudoClass instance = new InputPseudoClass();
	public static InputPseudoClass getInstance() {
		return instance;
	}
	private InputPseudoClass() { }
	
	private static final List<String> FORM_ELEMENT_TAGS = Arrays.asList("input", "button", "select", "textarea");
	
	private static final String INPUT = "input";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return INPUT.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return FORM_ELEMENT_TAGS.contains(element.getTagName());
	}
	
	private static final SqCSSFilter inputPseudoClassFilter = new PseudoClassFilter(getInstance(),
																		SELECTOR_NOT_USED, PSEUDO_CLASS_VALUE_NOT_USED);
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		// :input is an extension selector, nobody implements it natively
		return CompiledSelector.createFilterOnlySelector(inputPseudoClassFilter);
	}
	
}