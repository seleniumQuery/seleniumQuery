package io.github.seleniumquery.by.css.attributes;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.xpath.XPathExpression;
import io.github.seleniumquery.by.xpath.XPathExpressionFactory;
import io.github.seleniumquery.by.css.CssConditionalSelector;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

/**
 * [simple]
 * [restart="never"]
 *
 * #Cross-Driver
  * Who knows why, HtmlUnitDriver, while emulating IE, bugs on the selector: [title="a\\tc"]
 * So we should never allow HtmlUnitDriver+Emulating IE to handle attribute selectors natively...
 * 
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class EqualsOrHasAttributeCssSelector implements CssConditionalSelector<AttributeCondition> {

	public static final String EQUALS_ATTRIBUTE_SELECTOR_SYMBOL = "=";

	/**
	 * see {@link org.w3c.css.sac.Condition#SAC_ATTRIBUTE_CONDITION}
	 * 
	 * This condition checks an attribute. example:
	 * 
	 * [simple]
	 * [restart="never"]
	 * 
	 * Case INsensitive!
	 */
	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		String attributeName = attributeCondition.getLocalName();
		// [attribute=wantedValue]
		if (attributeCondition.getSpecified()) {
			String wantedValue = attributeCondition.getValue();
			String actualValue = element.getAttribute(attributeName);
			return equalsIgnoreCase(actualValue, wantedValue);
		}
		// [attribute]
		return SelectorUtils.hasAttribute(element, attributeName);
	}

	@Override
	public XPathExpression conditionToXPath(Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		String attributeName = AttributeEvaluatorUtils.getXPathAttribute(attributeCondition);
		// [attribute=wantedValue]
		if (attributeCondition.getSpecified()) {
			String wantedValue = SelectorUtils.intoEscapedXPathString(attributeCondition.getValue());
			return XPathExpressionFactory.createNoFilterSelector("[" + attributeName + " = " + wantedValue + "]");
		}
		// [attribute]
		return XPathExpressionFactory.createNoFilterSelector("[" + attributeName + "]");
	}

}