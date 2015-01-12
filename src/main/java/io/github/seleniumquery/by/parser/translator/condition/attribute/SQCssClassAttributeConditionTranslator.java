package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.parser.parsetree.condition.attribute.SQCssClassAttributeCondition;
import org.apache.commons.lang3.StringEscapeUtils;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.SimpleSelector;

import java.util.Map;

/**
 * .class
 *
 * see {@link org.w3c.css.sac.Condition#SAC_CLASS_CONDITION}
 *
 * This condition checks for a specified class. Example: .example
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssClassAttributeConditionTranslator {

	public SQCssClassAttributeCondition translate(SimpleSelector simpleSelector, Map<String, String> stringMap, AttributeCondition attributeCondition) {
		String wantedClassName = attributeCondition.getValue();
		String unescapedClassName = StringEscapeUtils.unescapeJava(wantedClassName);
		return new SQCssClassAttributeCondition(unescapedClassName);
	}
	
}