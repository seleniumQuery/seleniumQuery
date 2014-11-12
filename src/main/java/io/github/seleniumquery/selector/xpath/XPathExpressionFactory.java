package io.github.seleniumquery.selector.xpath;

import io.github.seleniumquery.selector.filter.ElementFilter;
import io.github.seleniumquery.selector.filter.ElementFilterList;

import java.util.ArrayList;
import java.util.List;

public class XPathExpressionFactory {
	
	private final static String EMPTY_SELECTOR = ""; 

	/**
	 * Creates a XPath expression that is empty and filters nothing.
	 */
	public static XPathExpression createEmptyXPathExpression() {
		return createFilterOnlySelector(ElementFilter.FILTER_NOTHING);
	}
	
	/**
	 * Creates a compiled selector that only does filtering, meaning
	 * it is entirely NOT supported by the driver.
	 */
	public static XPathExpression createFilterOnlySelector(ElementFilter filter) {
		return create(EMPTY_SELECTOR, filter);
	}

	public static XPathExpression createNoFilterSelectorAppliedToAll(String cssSelector) {
		XPathExpression create = create(cssSelector, ElementFilter.FILTER_NOTHING);
		create.kind = CssSelectorType.CONDITIONAL_TO_ALL;
		return create;
	}

	/**
	 * Creates a compiled selector that does no filtering, meaning
	 * it is entirely supported by the driver.
	 */
	public static XPathExpression createNoFilterSelector(String selector) {
		return create(selector, ElementFilter.FILTER_NOTHING);
	}

	public static XPathExpression create(String selector, ElementFilter filter) {
        List<ElementFilter> elementFilterList = elementFilterList(filter);
		return new XPathExpression(selector, new ElementFilterList(elementFilterList));
	}

    private static List<ElementFilter> elementFilterList(ElementFilter filter) {
        if (filter == ElementFilter.FILTER_NOTHING) {
            return new ArrayList<ElementFilter>();
        }
        ArrayList<ElementFilter> elementFilters = new ArrayList<ElementFilter>();
        elementFilters.add(filter);
        return elementFilters;
    }

}