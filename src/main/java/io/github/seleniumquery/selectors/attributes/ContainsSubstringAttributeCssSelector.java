package io.github.seleniumquery.selectors.attributes;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;
import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathSelectorFactory;
import io.github.seleniumquery.selectorcss.CssConditionalSelector;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

public class ContainsSubstringAttributeCssSelector implements CssConditionalSelector<AttributeCondition> {

	private static final ContainsSubstringAttributeCssSelector instance = new ContainsSubstringAttributeCssSelector();

	public static ContainsSubstringAttributeCssSelector getInstance() {
		return instance;
	}
	
	private ContainsSubstringAttributeCssSelector() { }

	/**
	 * Currently it is (mistakenly?) mapped to the type {@link org.w3c.css.sac.Condition#SAC_ATTRIBUTE_CONDITION}.
	 * The factory then inspects the actual type and redirects here.
	 * 
	 * This selector is:
	 * [attribute*=stringToStart]
	 * 
	 * CASE INsensitive!
	 */
	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		String attributeName = attributeCondition.getLocalName();
		if (!SelectorUtils.hasAttribute(element, attributeName)) {
			return false;
		}
		String wantedValue = attributeCondition.getValue();
		String attributeValue = element.getAttribute(attributeName);
		return containsIgnoreCase(attributeValue, wantedValue);
	}

	@Override
	public XPathExpression conditionToXPath(Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		String attributeName = AttributeEvaluatorUtils.getXPathAttribute(attributeCondition);
		String wantedValue = SelectorUtils.intoEscapedXPathString(attributeCondition.getValue());
		return XPathSelectorFactory.createNoFilterSelector("[contains("+attributeName+", "+wantedValue+")]");
	}
	
}