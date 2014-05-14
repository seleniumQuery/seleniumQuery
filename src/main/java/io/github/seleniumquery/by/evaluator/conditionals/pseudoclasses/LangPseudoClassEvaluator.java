package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.PSEUDO_CLASS_VALUE_NOT_USED;
import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.SELECTOR_NOT_USED;
import io.github.seleniumquery.by.evaluator.CSSCondition;
import io.github.seleniumquery.by.evaluator.DriverSupportMap;
import io.github.seleniumquery.by.evaluator.SelectorUtils;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

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
	public boolean is(WebDriver driver, WebElement element, Selector selectorUpToThisPoint, LangCondition langCondition) {
		String wantedLang = langCondition.getLang();
		return wantedLang.equals(SelectorUtils.lang(element));
	}

	@Override
	public CompiledSelector compile(WebDriver driver, Selector simpleSelector, LangCondition langCondition) {
		String wantedLang = langCondition.getLang();
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:lang
		if (DriverSupportMap.getInstance().supportsNatively(driver, ":lang(en)")) {
			return CompiledSelector.createNoFilterSelector(":lang("+wantedLang+")");
		}
		SqCSSFilter langPseudoClassFilter = new PseudoClassFilter(new LangPseudoClassAdapter(wantedLang), SELECTOR_NOT_USED,
				PSEUDO_CLASS_VALUE_NOT_USED);
		return CompiledSelector.createFilterOnlySelector(langPseudoClassFilter);
	}
	
	/**
	 * Adapter to PseudoClass for LangPseudoClassEvaluator, so LangPseudoClassEvaluator doesnt
	 * need to implement that interface.
	 * 
	 * This class bridges only the necessary methods (if LangPseudoClassEvaluator implemented
	 * PseudoClass, it would have to have all those methods.)
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
		public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String wantedLang) {
			return is(driver, element, null, langCondition);
		}
		@Override public boolean isApplicable(String x) { /* not used */ return false; }
		@Override public CompiledSelector compilePseudoClass(WebDriver x, Selector y, String z) { /* not used */ return null; }
	}
}