package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.evaluator.CSSCondition;
import io.github.seleniumquery.by.evaluator.DriverSupportMap;
import io.github.seleniumquery.by.evaluator.SelectorUtils;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.LangCondition;
import org.w3c.css.sac.Selector;

public class LangPseudoClassEvaluator implements CSSCondition<LangCondition> {

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
	public CompiledSelector compileCondition(WebDriver driver, Map<String, String> stringMap, Selector simpleSelector, LangCondition langCondition) {
		String wantedLangIndex = langCondition.getLang();
		String wantedLang = stringMap.get(wantedLangIndex);
		
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:lang
		if (DriverSupportMap.getInstance().supportsNatively(driver, ":lang(en)")) {
			return CompiledSelector.createNoFilterSelector(":lang("+wantedLang+")");
		}
		SqCSSFilter langPseudoClassFilter = new PseudoClassFilter(new LangPseudoClassAdapter(wantedLang));
		return CompiledSelector.createFilterOnlySelector(langPseudoClassFilter);
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
		@Override public CompiledSelector compilePseudoClass(WebDriver x, PseudoClassSelector pseudoClassSelector) { /* not used */ return null; }
	}

}