package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.parser.parsetree.condition.attribute.SQCssStartsWithAttributeCondition;
import org.w3c.css.sac.AttributeCondition;

/**
 * [attribute^=stringToStart]
 *
 * CASE INsensitive!
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssStartsWithAttributeConditionTranslator {

	public SQCssStartsWithAttributeCondition translate(AttributeCondition attributeCondition) {
		String attributeName = attributeCondition.getLocalName();
		String wantedValue = attributeCondition.getValue();
		return new SQCssStartsWithAttributeCondition(attributeName, wantedValue);
	}

}