package io.github.seleniumquery.selector;


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

	private static final List<CssFilter> EMPTY_UNMODIFIABLE_LIST = Collections.unmodifiableList(new ArrayList<CssFilter>());
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
		return new CompiledCssSelector(selector, CssFilter.FILTER_NOTHING);
	}

	/**
	 * Creates a compiled selector that only does filtering, meaning
	 * it is entirely NOT supported by the driver.
	 */
	public static CompiledCssSelector createFilterOnlySelector(CssFilter filter) {
		return new CompiledCssSelector(EMPTY_SELECTOR, filter);
	}
	
	private String cssSelector;
	private List<CssFilter> cssFilter;

	public CompiledCssSelector(String cssSelector, CssFilter cssFilter) {
		this.cssSelector = cssSelector;
		if (cssFilter == CssFilter.FILTER_NOTHING) {
			this.cssFilter = EMPTY_UNMODIFIABLE_LIST;
		} else {
			this.cssFilter = new ArrayList<CssFilter>(Arrays.asList(cssFilter));
		}
	}
	
	public CompiledCssSelector(String cssSelector, List<CssFilter> cssFilter) {
		this.cssSelector = cssSelector;
		this.cssFilter = cssFilter;
	}
	
	/**
	 * THIS IS CREATED FOR DEVELOPMENT PURPOSES ONLY!
	 */
	public CompiledCssSelector(final String cssSelector, final String filterName) {
		this(cssSelector, new CssFilter() {
			@Override
			public List<WebElement> filter(WebDriver driver, List<WebElement> elements) {
				return elements;
			}
			@Override
			public String toString() {
				return "Wont filter nothing for selector '"+cssSelector+"' as said by filtername '"+filterName+"'.";
			};
		});
	}

	public List<WebElement> execute(SearchContext context) {
		List<WebElement> elements = new By.ByCssSelector(this.cssSelector).findElements(context);
		WebDriver driver = SelectorUtils.getWebDriver(context);
		return filter(driver, elements);
	}
	
	public String getCssSelector() {
		return cssSelector;
	}
	
	public List<CssFilter> getCssFilter() {
		return cssFilter;
	}
	
	@Override
	public String toString() {
		return "CS! Selector: "+cssSelector+" // Filter: "+cssFilter;
	}

	public List<WebElement> filter(WebDriver driver, List<WebElement> elements) {
		for (CssFilter cf : cssFilter) {
			elements = cf.filter(driver, elements);
		}
		return elements;
	}

}