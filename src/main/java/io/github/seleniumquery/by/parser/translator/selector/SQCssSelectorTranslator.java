package io.github.seleniumquery.by.parser.translator.selector;

import io.github.seleniumquery.by.parser.translator.selector.combinator.SQCssDescendantSelectorTranslator;
import io.github.seleniumquery.by.parser.translator.selector.combinator.SQCssDirectAdjacentSelectorTranslator;
import io.github.seleniumquery.by.parser.translator.selector.combinator.SQCssDirectDescendantSelectorTranslator;
import io.github.seleniumquery.by.parser.translator.selector.combinator.SQCssGeneralAdjacentSelectorTranslator;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssSelector;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssUnknownSelector;
import org.w3c.css.sac.*;

import java.util.Map;

/**
 * Translates a Selector into a {@link SQCssSelector}.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssSelectorTranslator {

    private final SQCssConditionalSelectorTranslator conditionalCssSelector = new SQCssConditionalSelectorTranslator(this);
    private final SQCssTagNameSelectorTranslator tagNameSelector = new SQCssTagNameSelectorTranslator();
    private final SQCssDescendantSelectorTranslator descendantSelectorTranslator = new SQCssDescendantSelectorTranslator(this);
    private final SQCssDirectDescendantSelectorTranslator directDescendantSelectorTranslator = new SQCssDirectDescendantSelectorTranslator(this);
    private final SQCssDirectAdjacentSelectorTranslator directAdjacentSelectorTranslator = new SQCssDirectAdjacentSelectorTranslator(this);
    private final SQCssGeneralAdjacentSelectorTranslator generalAdjacentSelectorTranslator = new SQCssGeneralAdjacentSelectorTranslator(this);

	public SQCssSelector translate(Map<String, String> stringMap, Selector selector) {
		switch (selector.getSelectorType()) {
			case Selector.SAC_CONDITIONAL_SELECTOR:
				return conditionalCssSelector.translate(stringMap, (ConditionalSelector) selector);

			case Selector.SAC_ELEMENT_NODE_SELECTOR:
				return tagNameSelector.translate(stringMap, (ElementSelector) selector);

			// COMBINATORS
			case Selector.SAC_DESCENDANT_SELECTOR:
				return descendantSelectorTranslator.translate(stringMap, (DescendantSelector) selector);
			case Selector.SAC_CHILD_SELECTOR:
				return directDescendantSelectorTranslator.translate(stringMap, (DescendantSelector) selector);
			case Selector.SAC_DIRECT_ADJACENT_SELECTOR:
				return directAdjacentSelectorTranslator.translate(stringMap, (SiblingSelector) selector);
			// the parser returns this code for the "E ~ F" selector. Go figure...
			case Selector.SAC_ANY_NODE_SELECTOR:
				return generalAdjacentSelectorTranslator.translate(stringMap, (SiblingSelector) selector);

			case Selector.SAC_ROOT_NODE_SELECTOR:
			case Selector.SAC_NEGATIVE_SELECTOR:
			case Selector.SAC_TEXT_NODE_SELECTOR:
			case Selector.SAC_CDATA_SECTION_NODE_SELECTOR:
			case Selector.SAC_PROCESSING_INSTRUCTION_NODE_SELECTOR:
			case Selector.SAC_COMMENT_NODE_SELECTOR:
			case Selector.SAC_PSEUDO_ELEMENT_SELECTOR:
			default:
				return new SQCssUnknownSelector(selector.getSelectorType());
		}
	}

}