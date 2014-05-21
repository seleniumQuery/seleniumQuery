package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.evaluator.DriverSupportMap;
import io.github.seleniumquery.by.evaluator.SelectorUtils;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

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
	public CompiledSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		String wantedLang = pseudoClassSelector.getPseudoClassContent();
		SqCSSFilter langPseudoClassFilter = new PseudoClassFilter(getInstance(), pseudoClassSelector);
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:lang
		if (DriverSupportMap.getInstance().supportsNatively(driver, ":lang(en)")) {
			return CompiledSelector.createNoFilterSelector(":lang("+wantedLang+")");
		}
		return CompiledSelector.createFilterOnlySelector(langPseudoClassFilter);
	}
	
}