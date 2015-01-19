package io.github.seleniumquery.by.parser.translator.selector.combinator;

import io.github.seleniumquery.by.csstree.selector.SQCssSelector;
import io.github.seleniumquery.by.csstree.selector.combinator.SQCssDirectDescendantSelector;
import io.github.seleniumquery.by.parser.translator.selector.SQCssSelectorTranslator;
import org.w3c.css.sac.DescendantSelector;

import java.util.Map;

/**
 * PARENT > ELEMENT
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssDirectDescendantSelectorTranslator {

	private final SQCssSelectorTranslator sqCssSelectorTranslator;

	public SQCssDirectDescendantSelectorTranslator(SQCssSelectorTranslator sqCssSelectorTranslator) {
		this.sqCssSelectorTranslator = sqCssSelectorTranslator;
	}
	
	public SQCssDirectDescendantSelector translate(Map<String, String> stringMap, DescendantSelector sacDescendantSelector) {
		SQCssSelector ancestorSelector = sqCssSelectorTranslator.translate(stringMap, sacDescendantSelector.getAncestorSelector());
		SQCssSelector descendantSelector = sqCssSelectorTranslator.translate(stringMap, sacDescendantSelector.getSimpleSelector());

		return new SQCssDirectDescendantSelector(ancestorSelector, descendantSelector);
	}

}