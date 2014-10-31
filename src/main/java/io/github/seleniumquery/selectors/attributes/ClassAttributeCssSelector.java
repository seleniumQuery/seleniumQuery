package io.github.seleniumquery.selectors.attributes;

import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathSelectorFactory;
import io.github.seleniumquery.selectorcss.CssConditionalSelector;

import java.util.Arrays;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

/**
 * .class
 *
 * @author acdcjunior
 * @since 1.0.0
 */
public class ClassAttributeCssSelector implements CssConditionalSelector<AttributeCondition> {

	private static final String CLASS_ATTRIBUTE = "class";
	
	/**
	 * @see {@link org.w3c.css.sac.Condition#SAC_CLASS_CONDITION}
	 *
	 * This condition checks for a specified class. Example: .example
	 */
	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		if (!SelectorUtils.hasAttribute(element, CLASS_ATTRIBUTE)) {
			return false;
		}
		String wantedClassName = attributeCondition.getValue();
		String classAttributeValue = element.getAttribute(CLASS_ATTRIBUTE);
		return Arrays.asList(classAttributeValue.split("\\s+")).contains(wantedClassName);
	}

	@Override
	public XPathExpression conditionToXPath(Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		String wantedClassName = attributeCondition.getValue();
		// nothing to do, everyone supports filtering by class
		return XPathSelectorFactory.createNoFilterSelector("[contains(concat(' ', normalize-space(@class), ' '), ' "+wantedClassName+" ')]");
	}
	
}