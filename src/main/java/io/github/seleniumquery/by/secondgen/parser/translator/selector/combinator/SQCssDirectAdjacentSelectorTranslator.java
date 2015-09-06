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

package io.github.seleniumquery.by2.parser.translator.selector.combinator;

import io.github.seleniumquery.by2.csstree.selector.SQCssSelector;
import io.github.seleniumquery.by2.csstree.selector.combinator.SQCssDirectAdjacentSelector;
import io.github.seleniumquery.by2.parser.translator.selector.SQCssSelectorTranslator;
import io.github.seleniumquery.by.preparser.ArgumentMap;
import org.w3c.css.sac.SiblingSelector;

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

	public SQCssDirectAdjacentSelector translate(ArgumentMap argumentMap, SiblingSelector siblingSelector) {
		SQCssSelector sqCssSelector = sqCssSelectorTranslator.translate(argumentMap, siblingSelector.getSelector());
		SQCssSelector sqCssSiblingSelector = sqCssSelectorTranslator.translate(argumentMap, siblingSelector.getSiblingSelector());

		return new SQCssDirectAdjacentSelector(sqCssSelector, sqCssSiblingSelector);
	}

}