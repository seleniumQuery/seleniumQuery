/*
 * Copyright (c) 2017 seleniumQuery authors
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

package io.github.seleniumquery.by.secondgen.parser.translator;

import org.w3c.css.sac.ConditionalSelector;
import org.w3c.css.sac.DescendantSelector;
import org.w3c.css.sac.ElementSelector;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SiblingSelector;

import io.github.seleniumquery.by.common.preparser.ArgumentMap;
import io.github.seleniumquery.by.common.preparser.CssParsedSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssSelector;

/**
 * Translates a Selector into a {@link CssSelector}.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssSelectorTranslator {

    private final CssCombinatorSelectorTranslator combinatorSelectorTranslator = new CssCombinatorSelectorTranslator(this);
    private final CssConditionalSelectorTranslator conditionalCssSelector = new CssConditionalSelectorTranslator(this);
    private final CssTagNameSelectorTranslator tagNameSelector = new CssTagNameSelectorTranslator();

    public CssSelector translate(CssParsedSelector cssParsedSelector) {
        return translate(cssParsedSelector.getArgumentMap(), cssParsedSelector.getSelector());
    }

	public CssSelector translate(ArgumentMap argumentMap, Selector selector) {
		switch (selector.getSelectorType()) {
			case Selector.SAC_CONDITIONAL_SELECTOR:
				return conditionalCssSelector.translate(argumentMap, (ConditionalSelector) selector);

			case Selector.SAC_ELEMENT_NODE_SELECTOR:
				return tagNameSelector.translate((ElementSelector) selector);

			// COMBINATORS
			case Selector.SAC_DESCENDANT_SELECTOR:
				return combinatorSelectorTranslator.translateDescendant(argumentMap, (DescendantSelector) selector);
			case Selector.SAC_CHILD_SELECTOR:
				return combinatorSelectorTranslator.translateDirectDescendant(argumentMap, (DescendantSelector) selector);
			case Selector.SAC_DIRECT_ADJACENT_SELECTOR:
				return combinatorSelectorTranslator.translateDirectAdjacent(argumentMap, (SiblingSelector) selector);
			// the parser returns this code for the "E ~ F" selector. Go figure...
			case Selector.SAC_ANY_NODE_SELECTOR:
				return combinatorSelectorTranslator.translateGeneralAdjacent(argumentMap, (SiblingSelector) selector);

			case Selector.SAC_ROOT_NODE_SELECTOR:
			case Selector.SAC_NEGATIVE_SELECTOR:
			case Selector.SAC_TEXT_NODE_SELECTOR:
			case Selector.SAC_CDATA_SECTION_NODE_SELECTOR:
			case Selector.SAC_PROCESSING_INSTRUCTION_NODE_SELECTOR:
			case Selector.SAC_COMMENT_NODE_SELECTOR:
			case Selector.SAC_PSEUDO_ELEMENT_SELECTOR:
			default:
				throw new UnknownCssSelectorException(selector);
		}
	}
}
