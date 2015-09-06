package io.github.seleniumquery.by2.parser.translator.condition.attribute;

import io.github.seleniumquery.by.csstree.condition.attribute.SQCssContainsWordAttributeCondition;
import org.w3c.css.sac.AttributeCondition;

/**
 * [values~="10"]
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssContainsWordAttributeConditionTranslator {

	public SQCssContainsWordAttributeCondition translate(AttributeCondition attributeCondition) {
		String attributeName = attributeCondition.getLocalName();
		String wantedValue = attributeCondition.getValue();
		return new SQCssContainsWordAttributeCondition(attributeName, wantedValue);
	}

}