package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selectorcss.CompiledCssSelector;
import io.github.seleniumquery.selectorxpath.XPathExpression;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

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
	
	private static final ElementFilter submitPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// :submit is an extension selector, nobody implements it natively
		return CompiledCssSelector.createFilterOnlySelector(submitPseudoClassFilter);
	}
	
	@Override
	public XPathExpression pseudoClassToXPath(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		return XPathSelectorFactory.createNoFilterSelector("[("
				+ "(name() = 'input' and @type = 'submit') or "
				+ "(name() = 'button' and (@type = 'submit' or not(@type)) )"
				+ ")]");
	}
	
}