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
 * [simple]
 * [restart="never"]
 *
 * #Cross-Driver
 * Who knows why, HtmlUnitDriver, while emulating IE, bugs on the selector: [title="a\\tc"]
 * So we should never allow HtmlUnitDriver+Emulating IE to handle attribute selectors natively...
 *
 *
 * see {@link org.w3c.css.sac.Condition#SAC_ATTRIBUTE_CONDITION}
 *
 * This condition checks an attribute. example:
 *
 * [simple]
 * [restart="never"]
 *
 * Case INsensitive!
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssEqualsOrHasAttributeConditionTranslator {

	public static final String EQUALS_ATTRIBUTE_SELECTOR_SYMBOL = "=";

	public ConditionSimpleComponent conditionToXPath(Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		String attributeName = AttributeEvaluatorUtils.getXPathAttribute(attributeCondition);
		// [attribute=wantedValue]
		if (attributeCondition.getSpecified()) {
			String wantedValue = SelectorUtils.intoEscapedXPathString(attributeCondition.getValue());
			return new ConditionSimpleComponent("[" + attributeName + " = " + wantedValue + "]");
		}
		// [attribute]
		return new ConditionSimpleComponent("[" + attributeName + "]");
	}

	public SQCssCondition translate(SimpleSelector simpleSelector, Map<String, String> stringMap, AttributeCondition condition) {
		return null;
	}

}