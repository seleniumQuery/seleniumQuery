package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathSelectorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:lang
 * 
 * @author acdcjunior
 */
public class LangPseudoClass implements PseudoClass {
	
	private static final LangPseudoClass instance = new LangPseudoClass();
	public static LangPseudoClass getInstance() {
		return instance;
	}
	private LangPseudoClass() { }
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return pseudoClassValue.matches("lang-sq\\(.*\\)");
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		String wantedLang = pseudoClassSelector.getPseudoClassContent();
		return wantedLang.equals(SelectorUtils.lang(element));
	}
	
	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		String wantedLang = pseudoClassSelector.getPseudoClassContent();
		return XPathSelectorFactory.createNoFilterSelector("[ancestor-or-self::*[@lang][1]/@lang = '"+wantedLang+"']");
	}
	
}