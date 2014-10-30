package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selectorcss.CssConditionalSelector;
import io.github.seleniumquery.selectorxpath.XPathExpression;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.LangCondition;
import org.w3c.css.sac.Selector;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:lang
 * 
 * @author acdcjunior
 */
public class LangPseudoClassEvaluator implements CssConditionalSelector<LangCondition> {

	private static final LangPseudoClassEvaluator instance = new LangPseudoClassEvaluator();
	public static LangPseudoClassEvaluator getInstance() {
		return instance;
	}
	private LangPseudoClassEvaluator() { }

	/**
	 * @see {@link org.w3c.css.sac.Condition#SAC_LANG_CONDITION}
	 *
	 * This condition checks the language of the node. Example: :lang(fr)
	 */
	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, LangCondition langCondition) {
		String wantedLangIndex = langCondition.getLang();
		String wantedLang = stringMap.get(wantedLangIndex);
		return wantedLang.equals(SelectorUtils.lang(element));
	}

	@Override
	public XPathExpression conditionToXPath(Map<String, String> stringMap, Selector simpleSelector, LangCondition langCondition) {
		String wantedLangIndex = langCondition.getLang();
		String wantedLang = stringMap.get(wantedLangIndex);
		return XPathSelectorFactory.createNoFilterSelector("[ancestor-or-self::*[@lang][1]/@lang = '"+wantedLang+"']");
	}

}