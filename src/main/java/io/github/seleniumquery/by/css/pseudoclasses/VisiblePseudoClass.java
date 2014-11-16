package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.xpath.XPathExpression;
import io.github.seleniumquery.by.xpath.XPathExpressionFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * :visible
 *
 * @author acdcjunior
 * @since 1.0.0
 */
public class VisiblePseudoClass implements PseudoClass {

	public static final String NOT_DISPLAY_NONE_XPATH = "not(" + HiddenPseudoClass.HIDDEN_XPATH_MUST_FILTER + ")";

    private final ElementFilter visiblePseudoClassFilter = new PseudoClassFilter(this);

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return "visible".equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return SelectorUtils.isVisible(driver, element);
	}

	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		UnsupportedXPathPseudoClassException.xPathFiltersAreNotImplementedYed(":visible");
		
		// #no-xpath
		System.err.println(":visible is not fully XPath supported (if the style is in a class, it won't know)!!!");
		return XPathExpressionFactory.create("[" + NOT_DISPLAY_NONE_XPATH + "]", visiblePseudoClassFilter);
	}
	
}