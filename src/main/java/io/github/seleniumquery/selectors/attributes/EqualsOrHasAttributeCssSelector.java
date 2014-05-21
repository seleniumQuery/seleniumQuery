package io.github.seleniumquery.by.evaluator.conditionals.attributes;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;

import java.util.Map;

import io.github.seleniumquery.by.evaluator.CSSCondition;
import io.github.seleniumquery.by.evaluator.SelectorUtils;
import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

public class EqualsOrHasAttributeEvaluator implements CSSCondition<AttributeCondition> {

	private static final EqualsOrHasAttributeEvaluator instance = new EqualsOrHasAttributeEvaluator();

	public static EqualsOrHasAttributeEvaluator getInstance() {
		return instance;
	}
	
	private EqualsOrHasAttributeEvaluator() { }
	
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
	public CompiledSelector compileCondition(WebDriver driver, Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		// nothing to do, everyone supports this selector
		// [attribute=wantedValue]
		if (attributeCondition.getSpecified()) {
			return AttributeEvaluatorUtils.createAttributeNoFilterCompiledSelector(attributeCondition, EQUALS_ATTRIBUTE_SELECTOR_SYMBOL);
		}
		// [attribute]
		return AttributeEvaluatorUtils.createAttributeNoFilterCompiledSelector(attributeCondition);
		

	}

}