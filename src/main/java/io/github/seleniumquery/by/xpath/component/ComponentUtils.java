package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.filter.ElementFilterList;

import java.util.ArrayList;
import java.util.List;

public class ComponentUtils {

    static List<XPathComponent> combineComponents(XPathComponent one, XPathComponent other, XPathComponent otherCopyWithModifiedType) {
        List<XPathComponent> aggregatedComponents = new ArrayList<XPathComponent>(one.combinatedComponents);
        aggregatedComponents.add(otherCopyWithModifiedType);
        aggregatedComponents.addAll(other.combinatedComponents);
        return aggregatedComponents;
    }

    static ElementFilterList combineFilters(XPathComponent one,
                                            @SuppressWarnings("UnusedParameters") XPathComponent other,
                                            @SuppressWarnings("UnusedParameters") XPathComponent otherCopyWithModifiedType) {
        // should combine ElementFilterList here as well
        return one.elementFilterList;
    }

    static ElementFilterList toElementFilterList(ElementFilter filter) {
        if (filter == ElementFilter.FILTER_NOTHING) {
            return new ElementFilterList(new ArrayList<ElementFilter>());
        }
        ArrayList<ElementFilter> elementFilters = new ArrayList<ElementFilter>();
        elementFilters.add(filter);
        return new ElementFilterList(elementFilters);
    }

}