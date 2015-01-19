package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.csstree.condition.SQCssCondition;
import io.github.seleniumquery.by.csstree.condition.attribute.SQCssEqualsOrHasAttributeCondition;
import org.w3c.css.sac.AttributeCondition;

/**
 * [simple]
 * [restart="never"]
 *
 * Case INsensitive!
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssEqualsOrHasAttributeConditionTranslator {

	public SQCssCondition translate(AttributeCondition attributeCondition) {
		String attributeName = attributeCondition.getLocalName();
		// [attribute=wantedValue]
		if (attributeCondition.getSpecified()) {
			String wantedValue = attributeCondition.getValue();
			return new SQCssEqualsOrHasAttributeCondition(attributeName, wantedValue);
		}
		// [attribute]
		return new SQCssEqualsOrHasAttributeCondition(attributeName);
	}

}