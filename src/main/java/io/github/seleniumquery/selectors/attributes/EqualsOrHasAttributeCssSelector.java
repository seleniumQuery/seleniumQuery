package io.github.seleniumquery.selectors.attributes;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssConditionalSelector;
import io.github.seleniumquery.selector.CssFilter;
import io.github.seleniumquery.selector.DriverSupportService;
import io.github.seleniumquery.selector.SelectorUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	public CompiledCssSelector compileCondition(WebDriver driver, final Map<String, String> stringMap, final Selector simpleSelector, final AttributeCondition attributeCondition) {
		// #Cross-Driver
		// Who knows why, HtmlUnitDriver, while emulating ie, bugs on the following selector: [title="a\\tc"]
		// So we never allow HtmlUnitDriver+Emulatint IE to handle attribute selectors natively...
		if (DriverSupportService.isHtmlUnitDriverEmulatingIE(driver)) {
			CssFilter equalsOrHasAttributeFilter = new EqualsOrHasAttributeFilter(this, stringMap, simpleSelector, attributeCondition);
			return CompiledCssSelector.createFilterOnlySelector(equalsOrHasAttributeFilter);
		}
		// now, everyone else supports this selector...
		// [attribute=wantedValue]
		if (attributeCondition.getSpecified()) {
			return AttributeEvaluatorUtils.createAttributeNoFilterCompiledSelector(attributeCondition, EQUALS_ATTRIBUTE_SELECTOR_SYMBOL);
		}
		// [attribute]
		return AttributeEvaluatorUtils.createAttributeNoFilterCompiledSelector(attributeCondition);
	}

}

class EqualsOrHasAttributeFilter implements CssFilter {
	private final EqualsOrHasAttributeCssSelector equalsOrHasAttributeCssSelector;
	private final Map<String, String> stringMap;
	private final Selector simpleSelector;
	private final AttributeCondition attributeCondition;

	EqualsOrHasAttributeFilter(EqualsOrHasAttributeCssSelector equalsOrHasAttributeCssSelector,
			Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		this.equalsOrHasAttributeCssSelector = equalsOrHasAttributeCssSelector;
		this.stringMap = stringMap;
		this.simpleSelector = simpleSelector;
		this.attributeCondition = attributeCondition;
	}

	@Override
	public List<WebElement> filter(WebDriver driver, List<WebElement> elements) {
		for (Iterator<WebElement> iterator = elements.iterator(); iterator.hasNext();) {
			WebElement webElement = iterator.next();
			if (!equalsOrHasAttributeCssSelector.isCondition(driver, webElement, stringMap, simpleSelector, attributeCondition)) {
				iterator.remove();
			}
		}
		return elements;
	}
}