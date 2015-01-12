package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.parser.parsetree.condition.SQCssCondition;
import io.github.seleniumquery.by.xpath.component.ConditionSimpleComponent;
import io.github.seleniumquery.by.xpath.component.special.IdConditionComponent;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SimpleSelector;

import java.util.Map;

/**
 * #id
 *
 * see {@link org.w3c.css.sac.Condition#SAC_ID_CONDITION}
 *
 * This condition checks an id attribute. Example:
 *
 * #myId
 *
 * CASE SENSITIVE!
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssIdAttributeConditionTranslator {

	private static final String ID_ATTRIBUTE = "id";

	public ConditionSimpleComponent conditionToXPath(Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		String wantedId = attributeCondition.getValue();
		return new IdConditionComponent(wantedId);
	}

	public SQCssCondition translate(SimpleSelector simpleSelector, Map<String, String> stringMap, AttributeCondition condition) {
		return null;
	}

}