package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.parser.parsetree.condition.SQCssCondition;
import io.github.seleniumquery.by.parser.parsetree.condition.attribute.SQCssIdAttributeCondition;
import org.unbescape.java.JavaEscape;
import org.w3c.css.sac.AttributeCondition;

/**
 * #id
 *
 * CASE SENSITIVE!
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssIdAttributeConditionTranslator {

	public SQCssCondition translate(AttributeCondition attributeCondition) {
		String wantedId = attributeCondition.getValue();
		String escapedId = JavaEscape.unescapeJava(wantedId);
		return new SQCssIdAttributeCondition(escapedId);
	}

}