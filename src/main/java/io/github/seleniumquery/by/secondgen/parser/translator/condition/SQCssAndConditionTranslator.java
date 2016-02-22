/*
 * Copyright (c) 2016 seleniumQuery authors
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

package io.github.seleniumquery.by.secondgen.parser.translator.condition;

import com.steadystate.css.parser.selectors.ConditionalSelectorImpl;
import io.github.seleniumquery.by.common.preparser.ArgumentMap;
import io.github.seleniumquery.by.secondgen.csstree.condition.SQCssAndCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.SQCssCondition;
import org.w3c.css.sac.CombinatorCondition;
import org.w3c.css.sac.SimpleSelector;

/**
 * E.firstCondition.secondCondition
 *
 * see {@link org.w3c.css.sac.Condition#SAC_AND_CONDITION}
 *
 * @author acdcjunior
 *
 * @since 0.10.0
 */
class SQCssAndConditionTranslator {

	private SQCssConditionTranslator sqCssConditionTranslator;

	SQCssAndConditionTranslator(SQCssConditionTranslator sqCssConditionTranslator) {
		this.sqCssConditionTranslator = sqCssConditionTranslator;
	}

	public SQCssAndCondition translate(SimpleSelector selectorUpToThisPoint, ArgumentMap argumentMap, CombinatorCondition combinatorCondition) {
		ConditionalSelectorImpl selectorUpToThisPointPlusFirstCondition = new ConditionalSelectorImpl(
																					selectorUpToThisPoint,
																					combinatorCondition.getFirstCondition());

		SQCssCondition firstCondition = sqCssConditionTranslator.translate(selectorUpToThisPoint, argumentMap, combinatorCondition.getFirstCondition());
		SQCssCondition secondCondition = sqCssConditionTranslator.translate(selectorUpToThisPointPlusFirstCondition, argumentMap, combinatorCondition.getSecondCondition());
		return new SQCssAndCondition(firstCondition, secondCondition);
	}

}