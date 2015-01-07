package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.xpath.component.SimpleConditionalComponent;
import io.github.seleniumquery.by.xpath.component.XPathComponent;
import io.github.seleniumquery.by.css.CssConditionalSelector;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.LangCondition;
import org.w3c.css.sac.Selector;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:lang
 * 
 * @author acdcjunior
 * @since 0.9.0
 */
public class LangPseudoClassEvaluator implements CssConditionalSelector<LangCondition> {

	/*
	 * see also: {@link org.w3c.css.sac.Condition#SAC_LANG_CONDITION}
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
	public XPathComponent conditionToXPath(Map<String, String> stringMap, Selector simpleSelector, LangCondition langCondition) {
		String wantedLangIndex = langCondition.getLang();
		String wantedLang = stringMap.get(wantedLangIndex);
		return new SimpleConditionalComponent("[ancestor-or-self::*[@lang][1]/@lang = '" + wantedLang + "']");
	}

}