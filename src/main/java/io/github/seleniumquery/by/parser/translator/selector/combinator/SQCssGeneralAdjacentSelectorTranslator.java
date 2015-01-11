package io.github.seleniumquery.by.parser.translator.selector.combinator;

import io.github.seleniumquery.by.parser.translator.selector.SQCssSelectorTranslator;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssSelector;
import io.github.seleniumquery.by.parser.parsetree.selector.combinator.SQCssGeneralAdjacentSelector;
import org.w3c.css.sac.SiblingSelector;

import java.util.Map;

/**
 * E ~ PRE
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssGeneralAdjacentSelectorTranslator {

	private final SQCssSelectorTranslator sqCssSelectorTranslator;

	public SQCssGeneralAdjacentSelectorTranslator(SQCssSelectorTranslator sqCssSelectorTranslator) {
		this.sqCssSelectorTranslator = sqCssSelectorTranslator;
	}

	public SQCssGeneralAdjacentSelector translate(Map<String, String> stringMap, SiblingSelector sacSiblingSelector) {
		SQCssSelector previousSelector = sqCssSelectorTranslator.translate(stringMap, sacSiblingSelector.getSelector());
		SQCssSelector siblingSelector = sqCssSelectorTranslator.translate(stringMap, sacSiblingSelector.getSiblingSelector());

		return new SQCssGeneralAdjacentSelector(previousSelector, siblingSelector);
	}

}