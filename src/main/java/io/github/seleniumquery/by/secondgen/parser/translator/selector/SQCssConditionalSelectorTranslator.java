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

import io.github.seleniumquery.by.common.preparser.ArgumentMap;
import io.github.seleniumquery.by.secondgen.csstree.condition.CssCondition;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssConditionalSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssSelector;
import io.github.seleniumquery.by.secondgen.parser.translator.condition.CssConditionTranslator;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.ConditionalSelector;
import org.w3c.css.sac.SimpleSelector;

public class SQCssConditionalSelectorTranslator {

	private final SQCssSelectorTranslator sqCssSelectorTranslator;
	private final CssConditionTranslator cssConditionTranslator;

	public SQCssConditionalSelectorTranslator(SQCssSelectorTranslator sqCssSelectorTranslator) {
		this.sqCssSelectorTranslator = sqCssSelectorTranslator;
		this.cssConditionTranslator = new CssConditionTranslator();
	}

	public CssConditionalSelector translate(ArgumentMap argumentMap, ConditionalSelector conditionalSelector) {
		Condition condition = conditionalSelector.getCondition();
		SimpleSelector simpleSelector = conditionalSelector.getSimpleSelector();

		CssSelector cssSelector = sqCssSelectorTranslator.translate(argumentMap, simpleSelector);

		CssCondition cssCondition = cssConditionTranslator.translate(simpleSelector, argumentMap, condition);
		return new CssConditionalSelector(cssSelector, cssCondition);
	}

}
