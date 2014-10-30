package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selectorxpath.XPathExpression;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContainsPseudoClass implements PseudoClass {
	
	private static final ContainsPseudoClass instance = new ContainsPseudoClass();
	public static ContainsPseudoClass getInstance() {
		return instance;
	}
	private ContainsPseudoClass() { }
	
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
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		String textToContain = pseudoClassSelector.getPseudoClassContent();
		textToContain = SelectorUtils.unescapeString(textToContain);
		String wantedTextToContain = SelectorUtils.intoEscapedXPathString(textToContain);
		return XPathSelectorFactory.createNoFilterSelector("[contains(string(.), "+wantedTextToContain+")]");
	}

}