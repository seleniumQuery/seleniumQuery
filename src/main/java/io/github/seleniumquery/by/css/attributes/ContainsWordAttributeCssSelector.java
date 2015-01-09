package io.github.seleniumquery.by.css.attributes;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.css.CssConditionalSelector;
import io.github.seleniumquery.by.xpath.component.ConditionSimpleComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

import java.util.Map;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;

/**
 * [values~="10"]
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class ContainsWordAttributeCssSelector implements CssConditionalSelector<AttributeCondition, ConditionSimpleComponent> {

	public static final String CONTAINS_WORD_ATTRIBUTE_SELECTOR_SYMBOL = "~=";
	
	/**
	 * see {@link org.w3c.css.sac.Condition#SAC_ONE_OF_ATTRIBUTE_CONDITION}
	 * 
	 * This condition checks for a value in a space-separated values in a specified attribute example:
	 * [values~="10"]
	 * 
	 * Case INsensitive!
	 */
	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		String attributeName = attributeCondition.getLocalName();
		if (!SelectorUtils.hasAttribute(element, attributeName)) {
			return false;
		}
		String wantedValue = attributeCondition.getValue();
		String attributeValue = element.getAttribute(attributeName);
		String[] values = attributeValue.split(" ");
		for (String value : values) {
			if (equalsIgnoreCase(value, wantedValue)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public ConditionSimpleComponent conditionToXPath(Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		String attributeName = AttributeEvaluatorUtils.getXPathAttribute(attributeCondition);
		String wantedValueSurroundedBySpaces = SelectorUtils.intoEscapedXPathString(" " + attributeCondition.getValue() + " ");

		return new ConditionSimpleComponent("[contains(concat(' ', normalize-space(" + attributeName + "), ' '), " + wantedValueSurroundedBySpaces + ")]");
	}

}