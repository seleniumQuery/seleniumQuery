package io.github.seleniumquery.by.parser.translator.condition.pseudoclass;

import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssLangPseudoClass;
import org.w3c.css.sac.LangCondition;
import org.w3c.css.sac.Selector;

import java.util.Map;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:lang
 * 
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssLangPseudoClassTranslator {

	public SQCssLangPseudoClass translate(Selector simpleSelector, Map<String, String> stringMap, LangCondition langCondition) {
		String wantedLangIndex = langCondition.getLang();
		String wantedLang = stringMap.get(wantedLangIndex);
		return new SQCssLangPseudoClass(wantedLang);
	}

}