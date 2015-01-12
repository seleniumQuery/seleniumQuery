package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.parser.parsetree.condition.attribute.SQCssContainsSubstringAttributeCondition;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.SimpleSelector;

import java.util.Map;

/**
 * [attribute*=stringToContain]
 *
 * Currently it is (mistakenly?) mapped to the type {@link org.w3c.css.sac.Condition#SAC_ATTRIBUTE_CONDITION}.
 * The factory then inspects the actual type and redirects here.
 *
 * This selector is:
 * [attribute*=stringToContain]
 *
 * CASE INsensitive!
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssContainsSubstringAttributeConditionTranslator {

	public SQCssContainsSubstringAttributeCondition translate(SimpleSelector simpleSelector, Map<String, String> stringMap, AttributeCondition attributeCondition) {
		return new SQCssContainsSubstringAttributeCondition(attributeCondition.getLocalName(), attributeCondition.getValue());
	}

}