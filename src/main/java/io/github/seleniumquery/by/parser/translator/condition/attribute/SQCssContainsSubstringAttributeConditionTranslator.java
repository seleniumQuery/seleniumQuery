package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.parser.parsetree.condition.attribute.SQCssContainsSubstringAttributeCondition;
import org.w3c.css.sac.AttributeCondition;

/**
 * [attribute*=stringToContain]
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssContainsSubstringAttributeConditionTranslator {

	public SQCssContainsSubstringAttributeCondition translate(AttributeCondition attributeCondition) {
		String attributeName = attributeCondition.getLocalName();
		String wantedValue = attributeCondition.getValue();
		return new SQCssContainsSubstringAttributeCondition(attributeName, wantedValue);
	}

}