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

package io.github.seleniumquery.by.secondgen.parser.translator.selector.combinator;

import io.github.seleniumquery.by.common.preparser.ArgumentMap;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.combinator.CssDescendantSelector;
import io.github.seleniumquery.by.secondgen.parser.translator.selector.SQCssSelectorTranslator;
import org.w3c.css.sac.DescendantSelector;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SimpleSelector;

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

	public CssDescendantSelector translate(ArgumentMap argumentMap, DescendantSelector sacDescendantSelector) {
		Selector ancestorCSSSelector = sacDescendantSelector.getAncestorSelector();
		CssSelector ancestorSelector = sqCssSelectorTranslator.translate(argumentMap, ancestorCSSSelector);

		SimpleSelector descendantCSSSelector = sacDescendantSelector.getSimpleSelector();
		CssSelector descendantSelector = sqCssSelectorTranslator.translate(argumentMap, descendantCSSSelector);

		return new CssDescendantSelector(ancestorSelector, descendantSelector);
	}

}
