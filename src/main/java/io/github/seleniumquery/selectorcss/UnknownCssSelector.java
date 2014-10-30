package io.github.seleniumquery.selectorcss;

import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathSelectorFactory;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Represents an unknown CSS selector type.
 */
public class UnknownCssSelector<T> implements CssSelector<T> {
	
	private static final Log LOGGER = LogFactory.getLog(UnknownCssSelector.class);
	
	private short type;
	
	public UnknownCssSelector(short type) {
		this.type = type;
	}

	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, T selector) {
		throw new RuntimeException("CSS "+selector.getClass().getSimpleName()+" of type "+type+" is invalid or not supported!");
	}

	@Override
	public XPathExpression toXPath(Map<String, String> stringMap, T selector) {
		// if it is unknown, we can't convert it, so we simply ignore it
		LOGGER.warn("CSS Selector '"+selector+"' is unknown. Ignoring it.");
		return XPathSelectorFactory.createEmptyXPathExpression();
	}
	
}