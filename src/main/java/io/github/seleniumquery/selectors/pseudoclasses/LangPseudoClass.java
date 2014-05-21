package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssFilter;
import io.github.seleniumquery.selector.DriverSupportService;
import io.github.seleniumquery.selector.SelectorUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		String wantedLang = pseudoClassSelector.getPseudoClassContent();
		CssFilter langPseudoClassFilter = new PseudoClassFilter(getInstance(), pseudoClassSelector);
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:lang
		if (DriverSupportService.getInstance().supportsNatively(driver, ":lang(en)")) {
			return CompiledCssSelector.createNoFilterSelector(":lang("+wantedLang+")");
		}
		return CompiledCssSelector.createFilterOnlySelector(langPseudoClassFilter);
	}
	
}