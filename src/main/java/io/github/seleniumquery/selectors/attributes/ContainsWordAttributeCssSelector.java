package io.github.seleniumquery.selectors.attributes;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;

import java.util.Map;

import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selectorcss.CompiledCssSelector;
import io.github.seleniumquery.selectorcss.CssConditionalSelector;
import io.github.seleniumquery.selectorxpath.XPathExpression;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

public class ContainsWordAttributeCssSelector implements CssConditionalSelector<AttributeCondition> {

	private static final ContainsWordAttributeCssSelector instance = new ContainsWordAttributeCssSelector();
	public static ContainsWordAttributeCssSelector getInstance() {
		return instance;
	}
	private ContainsWordAttributeCssSelector() { }
	

	public static final String CONTAINS_WORD_ATTRIBUTE_SELECTOR_SYMBOL = "~=";
	
	/**
	 * @see {@link org.w3c.css.sac.Condition#SAC_ONE_OF_ATTRIBUTE_CONDITION}
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
	public CompiledCssSelector compileCondition(WebDriver driver, Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		// nothing to do, everyone supports this selector
		return AttributeEvaluatorUtils.createAttributeNoFilterCompiledSelector(attributeCondition,
				CONTAINS_WORD_ATTRIBUTE_SELECTOR_SYMBOL);
	}
	
	@Override
	public XPathExpression conditionToXPath(WebDriver driver, Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		String attributeName = AttributeEvaluatorUtils.getXPathAttribute(attributeCondition);
		String wantedValueSurroundedBySpaces = SelectorUtils.intoEscapedXPathString(" " + attributeCondition.getValue() + " ");

		return XPathSelectorFactory.createNoFilterSelector("[contains(concat(' ', normalize-space("+attributeName+"), ' '), "+wantedValueSurroundedBySpaces+")]");
	}

}