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

import org.w3c.css.sac.DescendantSelector;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SiblingSelector;
import org.w3c.css.sac.SimpleSelector;

import io.github.seleniumquery.by.common.preparser.ArgumentMap;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.combinator.CssDescendantSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.combinator.CssDirectAdjacentSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.combinator.CssDirectDescendantSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.combinator.CssGeneralAdjacentSelector;


class CssCombinatorSelectorTranslator {

	private final CssSelectorTranslator cssSelectorTranslator;

	CssCombinatorSelectorTranslator(CssSelectorTranslator cssSelectorTranslator) {
		this.cssSelectorTranslator = cssSelectorTranslator;
	}

    /**
     * E ~ PRE
     *
     * @author acdcjunior
     * @since 0.10.0
     */
    CssGeneralAdjacentSelector translateGeneralAdjacent(ArgumentMap argumentMap, SiblingSelector sacSiblingSelector) {
		CssSelector previousSelector = cssSelectorTranslator.translate(argumentMap, sacSiblingSelector.getSelector());
		CssSelector siblingSelector = cssSelectorTranslator.translate(argumentMap, sacSiblingSelector.getSiblingSelector());

		return new CssGeneralAdjacentSelector(previousSelector, siblingSelector);
	}

    /**
     * E F
     *
     * @author acdcjunior
     * @since 0.10.0
     */
    CssDescendantSelector translateDescendant(ArgumentMap argumentMap, DescendantSelector sacDescendantSelector) {
        Selector ancestorCSSSelector = sacDescendantSelector.getAncestorSelector();
        CssSelector ancestorSelector = cssSelectorTranslator.translate(argumentMap, ancestorCSSSelector);

        SimpleSelector descendantCSSSelector = sacDescendantSelector.getSimpleSelector();
        CssSelector descendantSelector = cssSelectorTranslator.translate(argumentMap, descendantCSSSelector);

        return new CssDescendantSelector(ancestorSelector, descendantSelector);
    }

    /**
     * E + F
     *
     * @author acdcjunior
     * @since 0.10.0
     */
    CssDirectAdjacentSelector translateDirectAdjacent(ArgumentMap argumentMap, SiblingSelector siblingSelector) {
		CssSelector cssSelector = cssSelectorTranslator.translate(argumentMap, siblingSelector.getSelector());
		CssSelector sqCssSiblingSelector = cssSelectorTranslator.translate(argumentMap, siblingSelector.getSiblingSelector());

		return new CssDirectAdjacentSelector(cssSelector, sqCssSiblingSelector);
	}

    /**
     * {@code PARENT > ELEMENT}
     *
     * @author acdcjunior
     * @since 0.10.0
     */
    CssDirectDescendantSelector translateDirectDescendant(ArgumentMap argumentMap, DescendantSelector sacDescendantSelector) {
		CssSelector ancestorSelector = cssSelectorTranslator.translate(argumentMap, sacDescendantSelector.getAncestorSelector());
		CssSelector descendantSelector = cssSelectorTranslator.translate(argumentMap, sacDescendantSelector.getSimpleSelector());

		return new CssDirectDescendantSelector(ancestorSelector, descendantSelector);
	}

}
