package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.parser.parsetree.condition.attribute.SQCssClassAttributeCondition;
import org.unbescape.css.CssEscape;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.SimpleSelector;

import java.util.Map;

/**
 * .class
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssClassAttributeConditionTranslator {

	public SQCssClassAttributeCondition translate(SimpleSelector simpleSelector, Map<String, String> stringMap, AttributeCondition attributeCondition) {
		String wantedClassName = attributeCondition.getValue();
		String unescapedClassName = CssEscape.unescapeCss(wantedClassName);
		return new SQCssClassAttributeCondition(unescapedClassName);
	}
	
}