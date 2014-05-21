package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * http://api.jquery.com/submit-selector/
 * 
 * @since 1.0.0
 * @author acdcjunior
 */
public class SubmitPseudoClass implements PseudoClass {
	
	private static final SubmitPseudoClass instance = new SubmitPseudoClass();
	public static SubmitPseudoClass getInstance() {
		return instance;
	}
	private SubmitPseudoClass() { }
	
	private static final String SUBMIT = "submit";
	private static final String INPUT = "input";
	private static final String BUTTON = "button";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return SUBMIT.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return (
					INPUT.equals(element.getTagName()) && SUBMIT.equalsIgnoreCase(element.getAttribute("type"))
			   )
				||
			   (
				   BUTTON.equals(element.getTagName()) && (
						   									element.getAttribute("type") == null ||
						   									SUBMIT.equalsIgnoreCase(element.getAttribute("type"))
						   								  )
			  );
	}
	
	private static final SqCSSFilter submitPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// :submit is an extension selector, nobody implements it natively
		return CompiledSelector.createFilterOnlySelector(submitPseudoClassFilter);
	}
	
}