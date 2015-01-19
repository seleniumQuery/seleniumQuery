package io.github.seleniumquery.by.parser.translator.selector.combinator;

import io.github.seleniumquery.by.csstree.selector.combinator.SQCssDirectAdjacentSelector;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssSelector;
import io.github.seleniumquery.by.parser.translator.selector.SQCssSelectorTranslator;
import org.w3c.css.sac.SiblingSelector;

import java.util.Map;

/**
 * E + F
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssDirectAdjacentSelectorTranslator {

	private final SQCssSelectorTranslator sqCssSelectorTranslator;

	public SQCssDirectAdjacentSelectorTranslator(SQCssSelectorTranslator sqCssSelectorTranslator) {
		this.sqCssSelectorTranslator = sqCssSelectorTranslator;
	}

	public SQCssDirectAdjacentSelector translate(Map<String, String> stringMap, SiblingSelector siblingSelector) {
		SQCssSelector sqCssSelector = sqCssSelectorTranslator.translate(stringMap, siblingSelector.getSelector());
		SQCssSelector sqCssSiblingSelector = sqCssSelectorTranslator.translate(stringMap, siblingSelector.getSiblingSelector());

		return new SQCssDirectAdjacentSelector(sqCssSelector, sqCssSiblingSelector);
	}

}