package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.xpath.XPathComponent;
import io.github.seleniumquery.by.xpath.XPathComponentFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * :contains()
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class ContainsPseudoClass implements PseudoClass {
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return pseudoClassValue.matches("contains\\(.*\\)");
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		String textToContain = pseudoClassSelector.getPseudoClassContent();
		textToContain = SelectorUtils.unescapeString(textToContain);
		return element.getText().contains(textToContain);
	}
	
	@Override
	public XPathComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		String textToContain = pseudoClassSelector.getPseudoClassContent();
		textToContain = SelectorUtils.unescapeString(textToContain);
		String wantedTextToContain = SelectorUtils.intoEscapedXPathString(textToContain);
		return XPathComponentFactory.createNoFilter("[contains(string(.), " + wantedTextToContain + ")]");
	}

}