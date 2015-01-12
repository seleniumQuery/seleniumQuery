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
 * [attribute$=stringToEnd]
 *
 * Currently it is (mistakenly?) mapped to the type {@link org.w3c.css.sac.Condition#SAC_ATTRIBUTE_CONDITION}.
 * The factory then inspects the actual type and redirects here.
 *
 * This selector is:
 * [attribute$=stringToEnd]
 *
 * CASE SENSITIVE! http://api.jquery.com/attribute-ends-with-selector/
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class SQCssEndsWithAttributeConditionTranslator {

	public static final String ENDS_WITH_ATTRIBUTE_SELECTOR_SYMBOL = "$=";

	public ConditionSimpleComponent conditionToXPath(Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		String attributeName = AttributeEvaluatorUtils.getXPathAttribute(attributeCondition);
		String attrValue = attributeCondition.getValue();
		String wantedValue = SelectorUtils.intoEscapedXPathString(attrValue);
		return new ConditionSimpleComponent("[substring(" + attributeName + ", string-length(" + attributeName + ")-" + (attrValue.length() - 1) + ") = " + wantedValue + "]");
	}

	public SQCssCondition translate(SimpleSelector simpleSelector, Map<String, String> stringMap, AttributeCondition condition) {
		return null;
	}

}