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
import io.github.seleniumquery.by.secondgen.csstree.selector.combinator.CssGeneralAdjacentSelector;
import io.github.seleniumquery.by.secondgen.parser.translator.selector.SQCssSelectorTranslator;
import org.w3c.css.sac.SiblingSelector;

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

	public CssGeneralAdjacentSelector translate(ArgumentMap argumentMap, SiblingSelector sacSiblingSelector) {
		CssSelector previousSelector = sqCssSelectorTranslator.translate(argumentMap, sacSiblingSelector.getSelector());
		CssSelector siblingSelector = sqCssSelectorTranslator.translate(argumentMap, sacSiblingSelector.getSiblingSelector());

		return new CssGeneralAdjacentSelector(previousSelector, siblingSelector);
	}

}
