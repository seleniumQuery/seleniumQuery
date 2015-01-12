package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.parser.parsetree.condition.SQCssCondition;
import io.github.seleniumquery.by.parser.parsetree.condition.attribute.SQCssContainsPrefixAttributeCondition;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.SimpleSelector;

import java.util.Map;

/**
 * [languages|="fr"]
 *
 * see {@link org.w3c.css.sac.Condition#SAC_BEGIN_HYPHEN_ATTRIBUTE_CONDITION}
 *
 * This condition checks if the value is in a hypen-separated list of values in a specified attribute. example:
 * [languages|="fr"]
 *
 * Case INsensitve
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssContainsPrefixAttributeConditionTranslator {

	public SQCssContainsPrefixAttributeCondition translate(SimpleSelector simpleSelector, Map<String, String> stringMap, AttributeCondition attributeCondition) {
		return new SQCssContainsPrefixAttributeCondition(attributeCondition.getLocalName(), attributeCondition.getValue());
	}

}