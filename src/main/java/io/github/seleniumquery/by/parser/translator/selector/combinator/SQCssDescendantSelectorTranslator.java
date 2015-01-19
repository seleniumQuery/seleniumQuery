package io.github.seleniumquery.by.parser.translator.selector.combinator;

import io.github.seleniumquery.by.csstree.selector.SQCssSelector;
import io.github.seleniumquery.by.csstree.selector.combinator.SQCssDescendantSelector;
import io.github.seleniumquery.by.parser.translator.selector.SQCssSelectorTranslator;
import org.w3c.css.sac.DescendantSelector;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SimpleSelector;

import java.util.Map;

/**
 * E F
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssDescendantSelectorTranslator {

	private final SQCssSelectorTranslator sqCssSelectorTranslator;

	public SQCssDescendantSelectorTranslator(SQCssSelectorTranslator sqCssSelectorTranslator) {
		this.sqCssSelectorTranslator = sqCssSelectorTranslator;
	}

	public SQCssDescendantSelector translate(Map<String, String> stringMap, DescendantSelector sacDescendantSelector) {
		Selector ancestorCSSSelector = sacDescendantSelector.getAncestorSelector();
		SQCssSelector ancestorSelector = sqCssSelectorTranslator.translate(stringMap, ancestorCSSSelector);

		SimpleSelector descendantCSSSelector = sacDescendantSelector.getSimpleSelector();
		SQCssSelector descendantSelector = sqCssSelectorTranslator.translate(stringMap, descendantCSSSelector);

		return new SQCssDescendantSelector(ancestorSelector, descendantSelector);
	}
	
}