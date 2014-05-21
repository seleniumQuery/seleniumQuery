package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * http://api.jquery.com/reset-selector/
 * 
 * @since 1.0.0
 * @author acdcjunior
 */
public class ResetPseudoClass implements PseudoClass {
	
	private static final ResetPseudoClass instance = new ResetPseudoClass();
	public static ResetPseudoClass getInstance() {
		return instance;
	}
	private ResetPseudoClass() { }
	
	private static final String RESET = "reset";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return RESET.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return ("input".equals(element.getTagName()) || "button".equals(element.getTagName()))
				&& RESET.equalsIgnoreCase(element.getAttribute("type"));
	}
	
	private static final SqCSSFilter resetPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// :reset is an extension selector, nobody implements it natively
		return CompiledSelector.createFilterOnlySelector(resetPseudoClassFilter);
	}
	
}