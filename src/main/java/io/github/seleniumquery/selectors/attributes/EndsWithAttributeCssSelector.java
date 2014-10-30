package io.github.seleniumquery.selectors.attributes;

import static org.apache.commons.lang3.StringUtils.endsWith;
import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selectorcss.CompiledCssSelector;
import io.github.seleniumquery.selectorcss.CssConditionalSelector;
import io.github.seleniumquery.selectorxpath.XPathExpression;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

public class EndsWithAttributeCssSelector implements CssConditionalSelector<AttributeCondition> {

	private static final EndsWithAttributeCssSelector instance = new EndsWithAttributeCssSelector();
	public static EndsWithAttributeCssSelector getInstance() {
		return instance;
	}
	private EndsWithAttributeCssSelector() { }
	
	public static final String ENDS_WITH_ATTRIBUTE_SELECTOR_SYMBOL = "$=";

	/**
	 * Currently it is (mistakenly?) mapped to the type {@link org.w3c.css.sac.Condition#SAC_ATTRIBUTE_CONDITION}.
	 * The factory then inspects the actual type and redirects here.
	 * 
	 * This selector is:
	 * [attribute$=stringToEnd]
	 * 
	 * CASE SENSITIVE! http://api.jquery.com/attribute-ends-with-selector/
	 */
	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		String wantedValue = attributeCondition.getValue();
		String actualValue = element.getAttribute(attributeCondition.getLocalName());
		return endsWith(actualValue, wantedValue);
	}

	@Override
	public CompiledCssSelector compileCondition(WebDriver driver, Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		// nothing to do, everyone supports this selector
		return AttributeEvaluatorUtils.createAttributeNoFilterCompiledSelector(attributeCondition, ENDS_WITH_ATTRIBUTE_SELECTOR_SYMBOL);
	}
	
	@Override
	public XPathExpression conditionToXPath(Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		String attributeName = AttributeEvaluatorUtils.getXPathAttribute(attributeCondition);
		String attrValue = attributeCondition.getValue();
		String wantedValue = SelectorUtils.intoEscapedXPathString(attrValue);
		return XPathSelectorFactory.createNoFilterSelector("[substring("+attributeName+", string-length("+attributeName+")-"+(attrValue.length()-1)+") = "+wantedValue+"]");
	}

}