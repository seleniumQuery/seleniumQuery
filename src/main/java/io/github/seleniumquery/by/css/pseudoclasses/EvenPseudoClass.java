package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.component.XPathComponent;
import io.github.seleniumquery.by.xpath.XPathSelectorCompilerService;
import io.github.seleniumquery.by.xpath.component.XPathComponentFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * :even
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class EvenPseudoClass implements PseudoClass {

	private static final String EVEN_PSEUDO_CLASS_NO_COLON = "even";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return EVEN_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		XPathComponent compiledSelector = XPathSelectorCompilerService.compileSelector(pseudoClassSelector.getStringMap(), pseudoClassSelector.getSelector());
		List<WebElement> elements = compiledSelector.findWebElements(driver);
		return elements.indexOf(element) % 2 == 0;
	}
	
	@Override
	public XPathComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		// notice that XPath is 1-based and :even is not.
		return XPathComponentFactory.createNoFilterAppliedToAll("[(position() mod 2) = 1]");
	}

}