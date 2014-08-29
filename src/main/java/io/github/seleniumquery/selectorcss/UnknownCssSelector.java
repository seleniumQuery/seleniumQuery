package io.github.seleniumquery.selectorcss;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selectorxpath.SqXPathSelector;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

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
	public CompiledCssSelector compile(WebDriver driver, Map<String, String> stringMap, T selector) {
		// if it is unknown, we just push it forward, hoping the browser will know what to do
		return new CompiledCssSelector(selector.toString(), ElementFilter.FILTER_NOTHING);
	}

	@Override
	public SqXPathSelector toXPath(WebDriver driver, Map<String, String> stringMap, T selector) {
		// if it is unknown, we can't convert it, so we simply ignore it
		LOGGER.warn("CSS Selector '"+selector+"' is unknown. Ignoring it.");
		return XPathSelectorFactory.createFilterOnlySelector(ElementFilter.FILTER_NOTHING);
	}
	
}