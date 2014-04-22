package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.evaluator.CSSCondition;
import io.github.seleniumquery.by.evaluator.SelectorUtils;
import io.github.seleniumquery.by.selector.CSSFilter;
import io.github.seleniumquery.by.selector.CompiledSelector;

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
	public CompiledSelector compile(WebDriver driver, Selector simpleSelector, LangCondition condition) {
		return new CompiledSelector(condition.toString(), ":LANG()");
	}
	
}