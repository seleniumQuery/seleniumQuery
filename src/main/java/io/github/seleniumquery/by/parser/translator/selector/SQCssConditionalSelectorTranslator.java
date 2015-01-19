package io.github.seleniumquery.by.parser.translator.selector;

import io.github.seleniumquery.by.csstree.condition.SQCssCondition;
import io.github.seleniumquery.by.csstree.selector.SQCssConditionalSelector;
import io.github.seleniumquery.by.csstree.selector.SQCssSelector;
import io.github.seleniumquery.by.parser.translator.condition.SQCssConditionTranslator;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.ConditionalSelector;
import org.w3c.css.sac.SimpleSelector;

import java.util.Map;

public class SQCssConditionalSelectorTranslator {

	private final SQCssSelectorTranslator sqCssSelectorTranslator;
	private final SQCssConditionTranslator sqCssConditionTranslator;

	public SQCssConditionalSelectorTranslator(SQCssSelectorTranslator sqCssSelectorTranslator) {
		this.sqCssSelectorTranslator = sqCssSelectorTranslator;
		this.sqCssConditionTranslator = new SQCssConditionTranslator();
	}

	public SQCssConditionalSelector translate(Map<String, String> stringMap, ConditionalSelector conditionalSelector) {
		Condition condition = conditionalSelector.getCondition();
		SimpleSelector simpleSelector = conditionalSelector.getSimpleSelector();

		SQCssSelector sqCssSelector = sqCssSelectorTranslator.translate(stringMap, simpleSelector);

		SQCssCondition sqCssCondition = sqCssConditionTranslator.translate(simpleSelector, stringMap, condition);
		return new SQCssConditionalSelector(sqCssSelector, sqCssCondition);
	}

}