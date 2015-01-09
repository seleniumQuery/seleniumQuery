package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.filter.ElementFilterList;
import io.github.seleniumquery.by.xpath.UnsupportedConditionalSelector;

import java.util.ArrayList;
import java.util.List;

public class ComponentUtils {

    public static List<XPathComponent> joinComponents(List<XPathComponent> oneComponents, XPathComponent otherCopyWithModifiedType) {
        List<XPathComponent> aggregatedComponents = new ArrayList<XPathComponent>(oneComponents);
        aggregatedComponents.add(otherCopyWithModifiedType);
        aggregatedComponents.addAll(otherCopyWithModifiedType.combinatedComponents);
        return aggregatedComponents;
    }

    public static ElementFilterList joinFilters(ElementFilterList oneElementFilterList,
                                                @SuppressWarnings("UnusedParameters") XPathComponent otherCopyWithModifiedType) {
        // should combine otherCopyWithModifiedType.elementFilterList here as well
        return oneElementFilterList;
    }

    static ElementFilterList toElementFilterList(ElementFilter filter) {
        if (filter == ElementFilter.FILTER_NOTHING) {
            return emptyElementFilterList();
        }
        ArrayList<ElementFilter> elementFilters = new ArrayList<ElementFilter>();
        elementFilters.add(filter);
        return new ElementFilterList(elementFilters);
    }

    static ElementFilterList emptyElementFilterList() {
        return new ElementFilterList(new ArrayList<ElementFilter>());
    }

    public static String unsupported(String cssSelectorType) {
        throw new UnsupportedConditionalSelector("The "+cssSelectorType+" selector is not supported as condition inside selectors.");
    }

    public static String removeBraces(String src) {
        return src.substring(1, src.length()-1);
    }
}