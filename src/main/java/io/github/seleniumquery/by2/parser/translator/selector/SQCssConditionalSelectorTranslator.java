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

package io.github.seleniumquery.by2.parser.translator.selector;

import io.github.seleniumquery.by.csstree.condition.SQCssCondition;
import io.github.seleniumquery.by.csstree.selector.SQCssConditionalSelector;
import io.github.seleniumquery.by.csstree.selector.SQCssSelector;
import io.github.seleniumquery.by2.parser.translator.condition.SQCssConditionTranslator;
import io.github.seleniumquery.by.preparser.ArgumentMap;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.ConditionalSelector;
import org.w3c.css.sac.SimpleSelector;

public class SQCssConditionalSelectorTranslator {

	private final SQCssSelectorTranslator sqCssSelectorTranslator;
	private final SQCssConditionTranslator sqCssConditionTranslator;

	public SQCssConditionalSelectorTranslator(SQCssSelectorTranslator sqCssSelectorTranslator) {
		this.sqCssSelectorTranslator = sqCssSelectorTranslator;
		this.sqCssConditionTranslator = new SQCssConditionTranslator();
	}

	public SQCssConditionalSelector translate(ArgumentMap argumentMap, ConditionalSelector conditionalSelector) {
		Condition condition = conditionalSelector.getCondition();
		SimpleSelector simpleSelector = conditionalSelector.getSimpleSelector();

		SQCssSelector sqCssSelector = sqCssSelectorTranslator.translate(argumentMap, simpleSelector);

		SQCssCondition sqCssCondition = sqCssConditionTranslator.translate(simpleSelector, argumentMap, condition);
		return new SQCssConditionalSelector(sqCssSelector, sqCssCondition);
	}

}