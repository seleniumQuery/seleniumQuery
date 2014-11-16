package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.by.xpath.XPathExpression;
import io.github.seleniumquery.by.xpath.XPathSelectorCompilerService;
import io.github.seleniumquery.by.xpath.XPathExpressionFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * http://api.jquery.com/odd-selector/
 * 
 * @author acdcjunior
 * @since 1.0.0
 */
public class OddPseudoClass implements PseudoClass {

	private static final String ODD_PSEUDO_CLASS_NO_COLON = "odd";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return ODD_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		XPathExpression compiledSelector = XPathSelectorCompilerService.compileSelector(pseudoClassSelector.getStringMap(), pseudoClassSelector.getSelector());
		List<WebElement> elements = compiledSelector.findWebElements(driver);
		return elements.indexOf(element) % 2 == 1;
	}
	
	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		// notice that XPath is 1-based and :odd is not.
		return XPathExpressionFactory.createNoFilterSelectorAppliedToAll("[(position() mod 2) = 0]");
	}

}