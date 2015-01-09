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
import static org.apache.commons.lang3.StringUtils.startsWithIgnoreCase;

/**
 * [languages|="fr"]
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class ContainsPrefixAttributeCssSelector implements CssConditionalSelector<AttributeCondition, ConditionSimpleComponent> {

	/**
	 * see {@link org.w3c.css.sac.Condition#SAC_BEGIN_HYPHEN_ATTRIBUTE_CONDITION}
	 *
	 * This condition checks if the value is in a hypen-separated list of values in a specified attribute. example:
	 * [languages|="fr"]
	 * 
	 * Case INsensitve
	 */
	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		String wantedValue = attributeCondition.getValue();
		String actualValue = element.getAttribute(attributeCondition.getLocalName());
		return equalsIgnoreCase(actualValue, wantedValue) || startsWithIgnoreCase(actualValue, wantedValue+'-');
	}

	@Override
	public ConditionSimpleComponent conditionToXPath(Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		String attributeName = AttributeEvaluatorUtils.getXPathAttribute(attributeCondition);
		String wantedValue = SelectorUtils.intoEscapedXPathString(attributeCondition.getValue());
		String wantedValueWithSuffix = SelectorUtils.intoEscapedXPathString(attributeCondition.getValue() + "-");
		return new ConditionSimpleComponent("[(" + attributeName + " = " + wantedValue + " or starts-with(" + attributeName + ", " + wantedValueWithSuffix + "))]");
	}
	
}