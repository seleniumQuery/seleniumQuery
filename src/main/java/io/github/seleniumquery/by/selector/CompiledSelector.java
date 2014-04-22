package io.github.seleniumquery.by.selector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class CompiledSelector {

	private static final List<CSSFilter> EMPTY_UNMODIFIABLE_LIST = Collections.unmodifiableList(new ArrayList<CSSFilter>());
	
	private String cssSelector;
	private List<CSSFilter> cssFilter;

	public CompiledSelector(String cssSelector, CSSFilter cssFilter) {
		this.cssSelector = cssSelector;
		if (cssFilter == CSSFilter.FILTER_NOTHING) {
			this.cssFilter = EMPTY_UNMODIFIABLE_LIST;
		} else {
			this.cssFilter = new ArrayList<CSSFilter>(Arrays.asList(cssFilter));
		}
	}
	
	public CompiledSelector(String cssSelector, List<CSSFilter> cssFilter) {
		this.cssSelector = cssSelector;
		this.cssFilter = cssFilter;
	}
	
	public CompiledSelector(String cssSelector, final String filterName) {
		this(cssSelector, new CSSFilter() {
			@Override
			public List<WebElement> filter(List<WebElement> elements) {
				return elements;
			}
			@Override
			public String toString() {
				return "Filter nothing by "+filterName;
			};
		});
	}

	public List<WebElement> execute(SearchContext context) {
		List<WebElement> elements = new By.ByCssSelector(this.cssSelector).findElements(context);
		for (CSSFilter cf : cssFilter) {
			elements = cf.filter(elements);
		}
		return elements;
	}
	
	public String getCssSelector() {
		return cssSelector;
	}
	
	public List<CSSFilter> getCssFilter() {
		return cssFilter;
	}
	
	@Override
	public String toString() {
		return "CS! Selector: "+cssSelector+" // Filter: "+cssFilter;
	}

}