package io.github.seleniumquery.selectors.attributes;

import static org.apache.commons.lang3.StringUtils.startsWithIgnoreCase;
import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssConditionalSelector;
import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selector.SqXPathSelector;
import io.github.seleniumquery.selector.XPathSelectorFactory;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

public class StartsWithAttributeCssSelector implements CssConditionalSelector<AttributeCondition> {

	private static final StartsWithAttributeCssSelector instance = new StartsWithAttributeCssSelector();
	public static StartsWithAttributeCssSelector getInstance() {
		return instance;
	}
	private StartsWithAttributeCssSelector() { }
	
	
	public static final String STARTS_WITH_ATTRIBUTE_SELECTOR_SYMBOL = "^=";

	/**
	 * Currently it is (mistakenly?) mapped to the type {@link org.w3c.css.sac.Condition#SAC_ATTRIBUTE_CONDITION}.
	 * The factory then inspects the actual type and redirects here.
	 * 
	 * This selector is:
	 * [attribute^=stringToStart]
	 * 
	 * CASE INsensitive!
	 */
	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		String wantedValue = attributeCondition.getValue();
		String actualValue = element.getAttribute(attributeCondition.getLocalName());
		return startsWithIgnoreCase(actualValue, wantedValue);
	}

	@Override
	public CompiledCssSelector compileCondition(WebDriver driver, Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		// nothing to do, everyone supports this selector
		return AttributeEvaluatorUtils.createAttributeNoFilterCompiledSelector(attributeCondition, STARTS_WITH_ATTRIBUTE_SELECTOR_SYMBOL);
	}
	
	@Override
	public SqXPathSelector conditionToXPath(WebDriver driver, Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		String attributeName = AttributeEvaluatorUtils.getXPathAttribute(attributeCondition);
		String wantedValue = SelectorUtils.intoEscapedXPathString(attributeCondition.getValue());
		return XPathSelectorFactory.createNoFilterSelector("[starts-with("+attributeName+", "+wantedValue+")]");
	}

}