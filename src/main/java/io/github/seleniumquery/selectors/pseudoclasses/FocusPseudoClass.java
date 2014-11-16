package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.by.selector.filter.ElementFilter;
import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathExpressionFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:focus
 *
 * @author acdcjunior
 * @since 1.0.0
 */
public class FocusPseudoClass implements PseudoClass {

	private static final String FOCUS_PSEUDO_CLASS_NO_COLON = "focus";

    private final ElementFilter focusPseudoClassFilter = new PseudoClassFilter(this);

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return FOCUS_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		WebElement currentlyActiveElement = driver.switchTo().activeElement();
		return element.equals(currentlyActiveElement);
	}

	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		UnsupportedXPathPseudoClassException.xPathFiltersAreNotImplementedYed(":focus");
		
		// #no-xpath
		return XPathExpressionFactory.createFilterOnlySelector(focusPseudoClassFilter);
	}
	
}