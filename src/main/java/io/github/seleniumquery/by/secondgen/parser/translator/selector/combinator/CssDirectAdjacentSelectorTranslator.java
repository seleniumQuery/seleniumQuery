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
import io.github.seleniumquery.by.secondgen.csstree.selector.combinator.CssDirectAdjacentSelector;
import io.github.seleniumquery.by.secondgen.parser.translator.selector.CssSelectorTranslator;
import org.w3c.css.sac.SiblingSelector;

/**
 * E + F
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssDirectAdjacentSelectorTranslator {

	private final CssSelectorTranslator cssSelectorTranslator;

	public CssDirectAdjacentSelectorTranslator(CssSelectorTranslator cssSelectorTranslator) {
		this.cssSelectorTranslator = cssSelectorTranslator;
	}

	public CssDirectAdjacentSelector translate(ArgumentMap argumentMap, SiblingSelector siblingSelector) {
		CssSelector cssSelector = cssSelectorTranslator.translate(argumentMap, siblingSelector.getSelector());
		CssSelector sqCssSiblingSelector = cssSelectorTranslator.translate(argumentMap, siblingSelector.getSiblingSelector());

		return new CssDirectAdjacentSelector(cssSelector, sqCssSiblingSelector);
	}

}
