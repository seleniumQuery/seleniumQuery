package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selector.DriverSupportService;
import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selectorcss.CompiledCssSelector;
import io.github.seleniumquery.selectorcss.CssConditionalSelector;
import io.github.seleniumquery.selectorxpath.SqXPathSelector;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.LangCondition;
import org.w3c.css.sac.Selector;

public class LangPseudoClassEvaluator implements CssConditionalSelector<LangCondition> {

	private static final LangPseudoClassEvaluator instance = new LangPseudoClassEvaluator();
	public static LangPseudoClassEvaluator getInstance() {
		return instance;
	}
	private LangPseudoClassEvaluator() { }

	/**
	 * @see {@link org.w3c.css.sac.Condition#SAC_LANG_CONDITION}
	 *
	 * This condition checks the language of the node. Example:
	 * :lang(fr)
	 */
	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, LangCondition langCondition) {
		String wantedLangIndex = langCondition.getLang();
		String wantedLang = stringMap.get(wantedLangIndex);
		return wantedLang.equals(SelectorUtils.lang(element));
	}

	@Override
	public CompiledCssSelector compileCondition(WebDriver driver, Map<String, String> stringMap, Selector simpleSelector, LangCondition langCondition) {
		String wantedLangIndex = langCondition.getLang();
		String wantedLang = stringMap.get(wantedLangIndex);
		
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:lang
		if (DriverSupportService.getInstance().supportsNatively(driver, ":lang(en)")) {
			return CompiledCssSelector.createNoFilterSelector(":lang("+wantedLang+")");
		}
		ElementFilter langPseudoClassFilter = new PseudoClassFilter(new LangPseudoClassAdapter(wantedLang));
		return CompiledCssSelector.createFilterOnlySelector(langPseudoClassFilter);
	}
	
	/**
	 * Adapts LangPseudoClassEvaluator into a PseudoClass, so LangPseudoClassEvaluator doesnt
	 * need to implement that interface directly.
	 * 
	 * This class bridges only the necessary methods (if LangPseudoClassEvaluator implemented
	 * PseudoClass, it would have to have all those methods, and not all of them are used.)
	 */
	private class LangPseudoClassAdapter implements PseudoClass {
		private LangCondition langCondition;
		public LangPseudoClassAdapter(final String wantedLang) {
			this.langCondition = new LangCondition() {
				@Override public short getConditionType() { /* not used */ return 0; }
				@Override public String getLang() { return wantedLang; }
			};
		}
		@Override
		public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
			return isCondition(driver, element, pseudoClassSelector.getStringMap(), null, langCondition);
		}
		@Override public boolean isApplicable(String x) { /* not used */ return false; }
		@Override public CompiledCssSelector compilePseudoClass(WebDriver x, PseudoClassSelector pseudoClassSelector) { /* not used */ return null; }
		@Override public SqXPathSelector pseudoClassToXPath(WebDriver driver, PseudoClassSelector pseudoClassSelector) { /* not used */ return null; }
	}

	@Override
	public SqXPathSelector conditionToXPath(WebDriver driver, Map<String, String> stringMap, Selector simpleSelector, LangCondition langCondition) {
		String wantedLangIndex = langCondition.getLang();
		String wantedLang = stringMap.get(wantedLangIndex);
		return XPathSelectorFactory.createNoFilterSelector("[ancestor-or-self::*[@lang][1]/@lang = '"+wantedLang+"']");
	}

}