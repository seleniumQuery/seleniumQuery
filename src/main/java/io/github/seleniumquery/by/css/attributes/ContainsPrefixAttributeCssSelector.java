package io.github.seleniumquery.by.css.attributes;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.apache.commons.lang3.StringUtils.startsWithIgnoreCase;
import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.xpath.XPathComponent;
import io.github.seleniumquery.by.xpath.XPathComponentFactory;
import io.github.seleniumquery.by.css.CssConditionalSelector;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

/**
 * [languages|="fr"]
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class ContainsPrefixAttributeCssSelector implements CssConditionalSelector<AttributeCondition> {

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
	public XPathComponent conditionToXPath(Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		String attributeName = AttributeEvaluatorUtils.getXPathAttribute(attributeCondition);
		String wantedValue = SelectorUtils.intoEscapedXPathString(attributeCondition.getValue());
		String wantedValueWithSuffix = SelectorUtils.intoEscapedXPathString(attributeCondition.getValue() + "-");
		return XPathComponentFactory.createNoFilter("[(" + attributeName + " = " + wantedValue + " or starts-with(" + attributeName + ", " + wantedValueWithSuffix + "))]");
	}
	
}