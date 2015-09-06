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

import io.github.seleniumquery.by.csstree.selector.SQCssSelector;
import io.github.seleniumquery.by.csstree.selector.combinator.SQCssDirectDescendantSelector;
import io.github.seleniumquery.by2.parser.translator.selector.SQCssSelectorTranslator;
import io.github.seleniumquery.by.preparser.ArgumentMap;
import org.w3c.css.sac.DescendantSelector;

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
	
	public SQCssDirectDescendantSelector translate(ArgumentMap argumentMap, DescendantSelector sacDescendantSelector) {
		SQCssSelector ancestorSelector = sqCssSelectorTranslator.translate(argumentMap, sacDescendantSelector.getAncestorSelector());
		SQCssSelector descendantSelector = sqCssSelectorTranslator.translate(argumentMap, sacDescendantSelector.getSimpleSelector());

		return new SQCssDirectDescendantSelector(ancestorSelector, descendantSelector);
	}

}