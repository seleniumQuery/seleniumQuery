package io.github.seleniumquery.by.xpath;

import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.filter.ElementFilterList;

import java.util.ArrayList;

public class XPathExpressionFactory {
	
	private final static String EMPTY_SELECTOR = ""; 

	/**
	 * Creates a XPath expression that is empty and filters nothing.
	 */
	public static XPathComponent createEmptyXPathExpression() {
		return createFilterOnlySelector(ElementFilter.FILTER_NOTHING);
	}
	
	/**
	 * Creates a compiled selector that only does filtering, meaning
	 * it is entirely NOT supported by the driver.
	 */
	public static XPathComponent createFilterOnlySelector(ElementFilter filter) {
		return createSimpleConditional(EMPTY_SELECTOR, filter);
	}

	public static XPathComponent createNoFilterSelectorAppliedToAll(String cssSelector) {
		return create(cssSelector, ElementFilter.FILTER_NOTHING, CssSelectorType.CONDITIONAL_TO_ALL);
	}

	/**
	 * Creates a XPath expression that does no additional filtering.
	 */
	public static XPathComponent createNoFilterSelector(String selector) {
		return createSimpleConditional(selector, ElementFilter.FILTER_NOTHING);
	}

	public static XPathComponent createNoFilterSelector(String selector, CssSelectorType cssSelectorType) {
		return create(selector, ElementFilter.FILTER_NOTHING, cssSelectorType);
	}

	public static XPathComponent createSimpleConditional(String selector, ElementFilter filter) {
		return new XPathComponent(selector, toElementFilterList(filter), CssSelectorType.CONDITIONAL_SIMPLE);
	}

	public static XPathComponent create(String selector, ElementFilter filter, CssSelectorType cssSelectorType) {
		return new XPathComponent(selector, toElementFilterList(filter), cssSelectorType);
	}

    private static ElementFilterList toElementFilterList(ElementFilter filter) {
        if (filter == ElementFilter.FILTER_NOTHING) {
            return new ElementFilterList(new ArrayList<ElementFilter>());
        }
        ArrayList<ElementFilter> elementFilters = new ArrayList<ElementFilter>();
        elementFilters.add(filter);
        return new ElementFilterList(elementFilters);
    }

}