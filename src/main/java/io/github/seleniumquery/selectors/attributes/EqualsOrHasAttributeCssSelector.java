package io.github.seleniumquery.selectors.attributes;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;

import java.util.Map;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssConditionalSelector;
import io.github.seleniumquery.selector.SelectorUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

public class EqualsOrHasAttributeCssSelector implements CssConditionalSelector<AttributeCondition> {

	private static final EqualsOrHasAttributeCssSelector instance = new EqualsOrHasAttributeCssSelector();

	public static EqualsOrHasAttributeCssSelector getInstance() {
		return instance;
	}
	
	private EqualsOrHasAttributeCssSelector() { }
	
	public static final String EQUALS_ATTRIBUTE_SELECTOR_SYMBOL = "=";

	/**
	 * @see {@link org.w3c.css.sac.Condition#SAC_ATTRIBUTE_CONDITION}
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
	public CompiledCssSelector compileCondition(WebDriver driver, Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		// nothing to do, everyone supports this selector
		// [attribute=wantedValue]
		if (attributeCondition.getSpecified()) {
			return AttributeEvaluatorUtils.createAttributeNoFilterCompiledSelector(attributeCondition, EQUALS_ATTRIBUTE_SELECTOR_SYMBOL);
		}
		// [attribute]
		return AttributeEvaluatorUtils.createAttributeNoFilterCompiledSelector(attributeCondition);
		

	}

}