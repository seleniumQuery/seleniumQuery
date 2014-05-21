package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssFilter;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return FORM_ELEMENT_TAGS.contains(element.getTagName());
	}
	
	private static final CssFilter inputPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// :input is an extension selector, nobody implements it natively
		return CompiledCssSelector.createFilterOnlySelector(inputPseudoClassFilter);
	}
	
}