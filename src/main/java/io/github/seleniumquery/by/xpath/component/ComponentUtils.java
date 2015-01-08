package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.filter.ElementFilterList;

import java.util.ArrayList;
import java.util.List;

public class ComponentUtils {

    public static TagComponent combineKeepingTypeOfFirstArg(TagComponent one, XPathComponent other) {
        XPathComponent otherCopy = copy(other);
        return new TagComponent(one.xPathExpression, combineComponents(one, other, otherCopy), combineFilters(one, other, otherCopy));
    }

    public static SimpleConditionalComponent combineKeepingTypeOfFirstArg(SimpleConditionalComponent one, XPathComponent other) {
        XPathComponent otherCopy = copy(other);
        return new SimpleConditionalComponent(one.xPathExpression, combineComponents(one, other, otherCopy), combineFilters(one, other, otherCopy));
    }

    public static ConditionalAppliedToAllComponent combineKeepingTypeOfFirstArg(ConditionalAppliedToAllComponent one, XPathComponent other) {
        XPathComponent otherCopy = copy(other);
        return new ConditionalAppliedToAllComponent(one.xPathExpression, combineComponents(one, other, otherCopy), combineFilters(one, other, otherCopy));
    }

    @Deprecated
    public static XPathComponent combineKeepingTypeOfFirstArg(XPathComponent one, XPathComponent other) {
        if (one.getClass() == SimpleConditionalComponent.class) {
            return combineKeepingTypeOfFirstArg((SimpleConditionalComponent) one, other);
        }
        if (one.getClass() == ConditionalAppliedToAllComponent.class) {
            return combineKeepingTypeOfFirstArg((ConditionalAppliedToAllComponent) one, other);
        }
        if (one.getClass() == TagComponent.class) {
            return combineKeepingTypeOfFirstArg((TagComponent) one, other);
        }
        throw new RuntimeException("Unexpected: "+one.getClass());
    }

    // gotta test this to make sure they all are ever called
    private static XPathComponent copy(XPathComponent other) {
        if (other.getClass() == SimpleConditionalComponent.class) {
            return new SimpleConditionalComponent(other.xPathExpression, other.combinatedComponents, other.elementFilterList);
        }
        if (other.getClass() == ConditionalAppliedToAllComponent.class) {
            return new ConditionalAppliedToAllComponent(other.xPathExpression, other.combinatedComponents, other.elementFilterList);
        }
        if (other.getClass() == TagComponent.class) {
            return new TagComponent(other.xPathExpression, other.combinatedComponents, other.elementFilterList);
        }
        throw new RuntimeException("Unexpected: "+other.getClass());
    }

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

    static void assertTagComponent(XPathComponent xPathComponent) {
        if (xPathComponent.getClass() != TagComponent.class) {
            throw new RuntimeException("Unexpected: Only TagComponent is expected here. Got: " + xPathComponent.getClass());
        }
    }
}