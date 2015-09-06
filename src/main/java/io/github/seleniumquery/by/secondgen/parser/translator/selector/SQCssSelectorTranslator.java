/*
 * Copyright (c) 2015 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.seleniumquery.by.secondgen.parser.translator.selector;

import io.github.seleniumquery.by.firstgen.preparser.ArgumentMap;
import io.github.seleniumquery.by.secondgen.csstree.selector.SQCssSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.SQCssUnknownSelectorException;
import io.github.seleniumquery.by.secondgen.parser.translator.selector.combinator.SQCssDescendantSelectorTranslator;
import io.github.seleniumquery.by.secondgen.parser.translator.selector.combinator.SQCssDirectAdjacentSelectorTranslator;
import io.github.seleniumquery.by.secondgen.parser.translator.selector.combinator.SQCssDirectDescendantSelectorTranslator;
import io.github.seleniumquery.by.secondgen.parser.translator.selector.combinator.SQCssGeneralAdjacentSelectorTranslator;
import org.w3c.css.sac.*;

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

	public SQCssSelector translate(ArgumentMap argumentMap, Selector selector) {
		switch (selector.getSelectorType()) {
			case Selector.SAC_CONDITIONAL_SELECTOR:
				return conditionalCssSelector.translate(argumentMap, (ConditionalSelector) selector);

			case Selector.SAC_ELEMENT_NODE_SELECTOR:
				return tagNameSelector.translate((ElementSelector) selector);

			// COMBINATORS
			case Selector.SAC_DESCENDANT_SELECTOR:
				return descendantSelectorTranslator.translate(argumentMap, (DescendantSelector) selector);
			case Selector.SAC_CHILD_SELECTOR:
				return directDescendantSelectorTranslator.translate(argumentMap, (DescendantSelector) selector);
			case Selector.SAC_DIRECT_ADJACENT_SELECTOR:
				return directAdjacentSelectorTranslator.translate(argumentMap, (SiblingSelector) selector);
			// the parser returns this code for the "E ~ F" selector. Go figure...
			case Selector.SAC_ANY_NODE_SELECTOR:
				return generalAdjacentSelectorTranslator.translate(argumentMap, (SiblingSelector) selector);

			case Selector.SAC_ROOT_NODE_SELECTOR:
			case Selector.SAC_NEGATIVE_SELECTOR:
			case Selector.SAC_TEXT_NODE_SELECTOR:
			case Selector.SAC_CDATA_SECTION_NODE_SELECTOR:
			case Selector.SAC_PROCESSING_INSTRUCTION_NODE_SELECTOR:
			case Selector.SAC_COMMENT_NODE_SELECTOR:
			case Selector.SAC_PSEUDO_ELEMENT_SELECTOR:
			default:
				throw new SQCssUnknownSelectorException(selector);
		}
	}

}