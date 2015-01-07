package io.github.seleniumquery.by.xpath;

import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.filter.ElementFilterList;

import java.util.ArrayList;

public class XPathComponentFactory {
	
	private final static String EMPTY_XPATH_EXPRESSION = "";

	/**
	 * Creates a XPath Component that is empty (has no XPath expression) and no Element Filter.
	 */
	public static XPathComponent createEmpty() {
		return createFilterOnly(ElementFilter.FILTER_NOTHING);
	}
	
	/**
	 * Creates a XPath Component that only does filtering.
	 */
	public static XPathComponent createFilterOnly(ElementFilter filter) {
		return createSimpleConditional(EMPTY_XPATH_EXPRESSION, filter);
	}

	public static XPathComponent createNoFilterAppliedToAll(String xPathExpression) {
		return create(xPathExpression, ElementFilter.FILTER_NOTHING, CssCombinationType.CONDITIONAL_TO_ALL);
	}

	/**
	 * Creates a XPath expression that does no additional filtering.
	 */
	public static XPathComponent createNoFilter(String xPathExpression) {
		return createSimpleConditional(xPathExpression, ElementFilter.FILTER_NOTHING);
	}

	public static XPathComponent createNoFilter(String xPathExpression, CssCombinationType cssCombinationType) {
		return create(xPathExpression, ElementFilter.FILTER_NOTHING, cssCombinationType);
	}

	public static XPathComponent createSimpleConditional(String xPathExpression, ElementFilter filter) {
		return new XPathComponent(xPathExpression, toElementFilterList(filter), CssCombinationType.CONDITIONAL_SIMPLE);
	}

	public static XPathComponent create(String xPathExpression, ElementFilter filter, CssCombinationType cssCombinationType) {
		return new XPathComponent(xPathExpression, toElementFilterList(filter), cssCombinationType);
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