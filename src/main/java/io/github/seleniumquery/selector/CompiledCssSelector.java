package io.github.seleniumquery.selector;


import io.github.seleniumquery.locator.ElementFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class CompiledCssSelector {

	private static final List<ElementFilter> EMPTY_UNMODIFIABLE_LIST = Collections.unmodifiableList(new ArrayList<ElementFilter>());
	private final static String EMPTY_SELECTOR = ""; 
	
	/**
	 * Creates a compiled selector that does no filtering, meaning
	 * it is entirely supported by the driver.
	 */
	public static CompiledCssSelector createNoFilterSelector(Selector selector) {
		return createNoFilterSelector(selector.toString());
	}
	
	/**
	 * Creates a compiled selector that does no filtering, meaning
	 * it is entirely supported by the driver.
	 */
	public static CompiledCssSelector createNoFilterSelector(String selector) {
		return new CompiledCssSelector(selector, ElementFilter.FILTER_NOTHING);
	}

	/**
	 * Creates a compiled selector that only does filtering, meaning
	 * it is entirely NOT supported by the driver.
	 */
	public static CompiledCssSelector createFilterOnlySelector(ElementFilter filter) {
		return new CompiledCssSelector(EMPTY_SELECTOR, filter);
	}
	
	private String cssSelector;
	private List<ElementFilter> cssFilter;

	public CompiledCssSelector(String cssSelector, ElementFilter cssFilter) {
		this.cssSelector = cssSelector;
		if (cssFilter == ElementFilter.FILTER_NOTHING) {
			this.cssFilter = EMPTY_UNMODIFIABLE_LIST;
		} else {
			this.cssFilter = new ArrayList<ElementFilter>(Arrays.asList(cssFilter));
		}
	}
	
	public CompiledCssSelector(String cssSelector, List<ElementFilter> cssFilter) {
		this.cssSelector = cssSelector;
		this.cssFilter = cssFilter;
	}
	
	public List<WebElement> execute(SearchContext context) {
		List<WebElement> elements = new By.ByCssSelector(this.cssSelector).findElements(context);
		WebDriver driver = SelectorUtils.getWebDriver(context);
		return filter(driver, elements);
	}
	
	public String getCssSelector() {
		return cssSelector;
	}
	
	public List<ElementFilter> getCssFilter() {
		return cssFilter;
	}
	
	@Override
	public String toString() {
		return "CS! Selector: "+cssSelector+" // Filter: "+cssFilter;
	}

	public List<WebElement> filter(WebDriver driver, List<WebElement> elements) {
		for (ElementFilter cf : cssFilter) {
			elements = cf.filterElements(driver, elements);
		}
		return elements;
	}

}