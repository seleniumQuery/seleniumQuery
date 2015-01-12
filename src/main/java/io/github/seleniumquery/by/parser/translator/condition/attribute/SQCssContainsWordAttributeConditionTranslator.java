package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.css.attributes.AttributeEvaluatorUtils;
import io.github.seleniumquery.by.parser.parsetree.condition.SQCssCondition;
import io.github.seleniumquery.by.xpath.component.ConditionSimpleComponent;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SimpleSelector;

import java.util.Map;

/**
 * [values~="10"]
 *
 * see {@link org.w3c.css.sac.Condition#SAC_ONE_OF_ATTRIBUTE_CONDITION}
 *
 * This condition checks for a value in a space-separated values in a specified attribute example:
 * [values~="10"]
 *
 * Case INsensitive!
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssContainsWordAttributeConditionTranslator {

	public static final String CONTAINS_WORD_ATTRIBUTE_SELECTOR_SYMBOL = "~=";
	
	public ConditionSimpleComponent conditionToXPath(Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		String attributeName = AttributeEvaluatorUtils.getXPathAttribute(attributeCondition);
		String wantedValueSurroundedBySpaces = SelectorUtils.intoEscapedXPathString(" " + attributeCondition.getValue() + " ");

		return new ConditionSimpleComponent("[contains(concat(' ', normalize-space(" + attributeName + "), ' '), " + wantedValueSurroundedBySpaces + ")]");
	}

	public SQCssCondition translate(SimpleSelector simpleSelector, Map<String, String> stringMap, AttributeCondition condition) {
		return null;
	}

}