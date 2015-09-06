package io.github.seleniumquery.by2.parser.translator.condition.attribute;

import io.github.seleniumquery.by.csstree.condition.attribute.SQCssEndsWithAttributeCondition;
import org.w3c.css.sac.AttributeCondition;

/**
 * [attribute$=stringToEnd]
 *
 * CASE SENSITIVE! http://api.jquery.com/attribute-ends-with-selector/
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class SQCssEndsWithAttributeConditionTranslator {

	public SQCssEndsWithAttributeCondition translate(AttributeCondition attributeCondition) {
		String attributeName = attributeCondition.getLocalName();
		String wantedValue = attributeCondition.getValue();
		return new SQCssEndsWithAttributeCondition(attributeName, wantedValue);
	}

}