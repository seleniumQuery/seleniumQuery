package io.github.seleniumquery.by.selector;

import io.github.seleniumquery.by.evaluator.SelectorUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class CompiledSelector {

	private static final List<SqCSSFilter> EMPTY_UNMODIFIABLE_LIST = Collections.unmodifiableList(new ArrayList<SqCSSFilter>());
	private final static String EMPTY_SELECTOR = ""; 
	
	/**
	 * Creates a compiled selector that does no filtering, meaning
	 * it is entirely supported by the driver.
	 */
	public static CompiledSelector createNoFilterSelector(Selector selector) {
		return createNoFilterSelector(selector.toString());
	}
	
	/**
	 * Creates a compiled selector that does no filtering, meaning
	 * it is entirely supported by the driver.
	 */
	public static CompiledSelector createNoFilterSelector(String selector) {
		return new CompiledSelector(selector, SqCSSFilter.FILTER_NOTHING);
	}

	/**
	 * Creates a compiled selector that only does filtering, meaning
	 * it is entirely NOT supported by the driver.
	 */
	public static CompiledSelector createFilterOnlySelector(SqCSSFilter filter) {
		return new CompiledSelector(EMPTY_SELECTOR, filter);
	}
	
	private String cssSelector;
	private List<SqCSSFilter> cssFilter;

	public CompiledSelector(String cssSelector, SqCSSFilter cssFilter) {
		this.cssSelector = cssSelector;
		if (cssFilter == SqCSSFilter.FILTER_NOTHING) {
			this.cssFilter = EMPTY_UNMODIFIABLE_LIST;
		} else {
			this.cssFilter = new ArrayList<SqCSSFilter>(Arrays.asList(cssFilter));
		}
	}
	
	public CompiledSelector(String cssSelector, List<SqCSSFilter> cssFilter) {
		this.cssSelector = cssSelector;
		this.cssFilter = cssFilter;
	}
	
	/**
	 * THIS IS CREATED FOR DEVELOPMENT PURPOSES ONLY!
	 */
	public CompiledSelector(final String cssSelector, final String filterName) {
		this(cssSelector, new SqCSSFilter() {
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
	
	public List<SqCSSFilter> getCssFilter() {
		return cssFilter;
	}
	
	@Override
	public String toString() {
		return "CS! Selector: "+cssSelector+" // Filter: "+cssFilter;
	}

	public List<WebElement> filter(WebDriver driver, List<WebElement> elements) {
		for (SqCSSFilter cf : cssFilter) {
			elements = cf.filter(driver, elements);
		}
		return elements;
	}

}