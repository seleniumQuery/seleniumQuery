package io.github.seleniumquery.by.parser.translator.condition;

import com.steadystate.css.parser.selectors.ConditionalSelectorImpl;
import io.github.seleniumquery.by.csstree.condition.SQCssAndCondition;
import io.github.seleniumquery.by.csstree.condition.SQCssCondition;
import org.w3c.css.sac.CombinatorCondition;
import org.w3c.css.sac.SimpleSelector;

import java.util.Map;

/**
 * E.firstCondition.secondCondition
 *
 * see {@link org.w3c.css.sac.Condition#SAC_AND_CONDITION}
 *
 * @author acdcjunior
 *
 * @since 0.10.0
 */
public class SQCssAndConditionTranslator {

	private SQCssConditionTranslator sqCssConditionTranslator;

	public SQCssAndConditionTranslator(SQCssConditionTranslator sqCssConditionTranslator) {
		this.sqCssConditionTranslator = sqCssConditionTranslator;
	}

	public SQCssAndCondition translate(SimpleSelector selectorUpToThisPoint, Map<String, String> stringMap, CombinatorCondition combinatorCondition) {
		ConditionalSelectorImpl selectorUpToThisPointPlusFirstCondition = new ConditionalSelectorImpl(
																					selectorUpToThisPoint,
																					combinatorCondition.getFirstCondition());

		SQCssCondition firstCondition = sqCssConditionTranslator.translate(selectorUpToThisPoint, stringMap, combinatorCondition.getFirstCondition());
		SQCssCondition secondCondition = sqCssConditionTranslator.translate(selectorUpToThisPointPlusFirstCondition, stringMap, combinatorCondition.getSecondCondition());
		return new SQCssAndCondition(firstCondition, secondCondition);
	}

}