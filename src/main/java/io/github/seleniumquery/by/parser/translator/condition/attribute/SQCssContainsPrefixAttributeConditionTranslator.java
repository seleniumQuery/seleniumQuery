package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.csstree.condition.attribute.SQCssContainsPrefixAttributeCondition;
import org.w3c.css.sac.AttributeCondition;

/**
 * [languages|="pt"]
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssContainsPrefixAttributeConditionTranslator {

	public SQCssContainsPrefixAttributeCondition translate(AttributeCondition attributeCondition) {
		String attributeName = attributeCondition.getLocalName();
		String wantedValue = attributeCondition.getValue();
		return new SQCssContainsPrefixAttributeCondition(attributeName, wantedValue);
	}

}