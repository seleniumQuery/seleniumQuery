package io.github.seleniumquery.selectors.conditionals;

import io.github.seleniumquery.selector.filter.ElementFilter;
import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathExpressionFactory;
import io.github.seleniumquery.selector.css.CssConditionalSelector;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.Selector;

/**
 * Represents an unknown condition type.
 */
public class UnknownConditionalCssSelector<T extends Condition> implements CssConditionalSelector<T> {
	
	private static final Log LOGGER = LogFactory.getLog(UnknownConditionalCssSelector.class);
	
	private short type;
	
	public UnknownConditionalCssSelector(short type) {
		this.type = type;
	}

	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, T condition) {
		throw new RuntimeException("CSS condition "+condition.getClass().getSimpleName()+" of type "+type+" is invalid or not supported!");
	}

	@Override
	public XPathExpression conditionToXPath(Map<String, String> stringMap, Selector simpleSelector, T condition) {
		// if it is unknown, we can't convert it, so we simply ignore it
		LOGGER.warn("CSS Selector Condition '"+condition+"' is unknown. Ignoring it.");
		return XPathExpressionFactory.createFilterOnlySelector(ElementFilter.FILTER_NOTHING);
	}
	
}