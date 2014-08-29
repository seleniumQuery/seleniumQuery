package io.github.seleniumquery.selectorxpath;

import io.github.seleniumquery.locator.ElementFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.w3c.css.sac.Selector;

public class XPathSelectorFactory {
	
	private final static String EMPTY_SELECTOR = ""; 

	/**
	 * Creates a compiled selector that only does filtering, meaning
	 * it is entirely NOT supported by the driver.
	 */
	public static XPathExpression createFilterOnlySelector(ElementFilter filter) {
		return create(EMPTY_SELECTOR, filter);
	}

	public static XPathExpression createNoFilterSelectorAppliedToAll(String cssSelector) {
		XPathExpression create = create(cssSelector, ElementFilter.FILTER_NOTHING);
		create.kind = SqSelectorKind.CONDITIONAL_TO_ALL;
		return create;
	}

	/**
	 * Creates a compiled selector that does no filtering, meaning
	 * it is entirely supported by the driver.
	 */
	public static XPathExpression createNoFilterSelector(String selector) {
		return create(selector, ElementFilter.FILTER_NOTHING);
	}

	/**
	 * Creates a compiled selector that does no filtering, meaning
	 * it is entirely supported by the driver.
	 */
	public static XPathExpression createNoFilterSelector(Selector selector) {
		return createNoFilterSelector(selector.toString());
	}

	public static XPathExpression create(String selector, ElementFilter filter) {
		
		List<ElementFilter> filterList = null;
		
		if (filter == ElementFilter.FILTER_NOTHING) {
			filterList = new ArrayList<ElementFilter>();
		} else {
			filterList = new ArrayList<ElementFilter>(Arrays.asList(filter));
		}
		
		return new XPathExpression(selector, filterList);
	}

}