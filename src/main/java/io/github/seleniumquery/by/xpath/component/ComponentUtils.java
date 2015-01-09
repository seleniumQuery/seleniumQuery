package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.filter.ElementFilterList;
import io.github.seleniumquery.by.xpath.component.special.IdConditionComponent;

import java.util.ArrayList;
import java.util.List;

public class ComponentUtils {

    public static TagComponent combineKeepingTypeOfFirstArg(TagComponent one, XPathComponent other) {
        XPathComponent otherCopy = cloneComponent(other);
        return new TagComponent(one.xPathExpression, combineComponents(one, other, otherCopy), combineFilters(one, other, otherCopy));
    }

    public static IdConditionComponent combineKeepingTypeOfFirstArg(IdConditionComponent one, XPathComponent other) {
        return one.cloneAndCombine(other);
    }

    public static SimpleConditionalComponent combineKeepingTypeOfFirstArg(SimpleConditionalComponent one, XPathComponent other) {
        XPathComponent otherCopy = cloneComponent(other);
        return new SimpleConditionalComponent(one.xPathExpression, combineComponents(one, other, otherCopy), combineFilters(one, other, otherCopy));
    }

    public static ConditionToAllComponent combineKeepingTypeOfFirstArg(ConditionToAllComponent one, XPathComponent other) {
        XPathComponent otherCopy = cloneComponent(other);
        return new ConditionToAllComponent(one.xPathExpression, combineComponents(one, other, otherCopy), combineFilters(one, other, otherCopy));
    }

    public static ConditionComponent combineKeepingTypeOfFirstArg(ConditionComponent one, XPathComponent other) {
        if (one.getClass() == IdConditionComponent.class) {
            return combineKeepingTypeOfFirstArg((IdConditionComponent) one, other);
        }
        if (one.getClass() == SimpleConditionalComponent.class) {
            return combineKeepingTypeOfFirstArg((SimpleConditionalComponent) one, other);
        }
        if (one.getClass() == ConditionToAllComponent.class) {
            return combineKeepingTypeOfFirstArg((ConditionToAllComponent) one, other);
        }
        throw new RuntimeException("Unexpected: "+one.getClass());
    }

    public static XPathComponent cloneComponent(XPathComponent other) {
        if (other.getClass() == IdConditionComponent.class) {
            return ((IdConditionComponent)other).cloneComponent();
        }
        if (other.getClass() == SimpleConditionalComponent.class) {
            return new SimpleConditionalComponent(other.xPathExpression, other.combinatedComponents, other.elementFilterList);
        }
        if (other.getClass() == ConditionToAllComponent.class) {
            return new ConditionToAllComponent(other.xPathExpression, other.combinatedComponents, other.elementFilterList);
        }
        if (other.getClass() == TagComponent.class) {
            return new TagComponent(other.xPathExpression, other.combinatedComponents, other.elementFilterList);
        }
        throw new RuntimeException("Unexpected: "+other.getClass());
    }

    public static List<XPathComponent> combineComponents(XPathComponent one,
                                                         @SuppressWarnings("UnusedParameters") XPathComponent other,
                                                         XPathComponent otherCopyWithModifiedType) {
        return combineComponents(one.combinatedComponents, otherCopyWithModifiedType);
    }

    public static List<XPathComponent> combineComponents(List<XPathComponent> oneComponents, XPathComponent otherCopyWithModifiedType) {
        List<XPathComponent> aggregatedComponents = new ArrayList<XPathComponent>(oneComponents);
        aggregatedComponents.add(otherCopyWithModifiedType);
        aggregatedComponents.addAll(otherCopyWithModifiedType.combinatedComponents);
        return aggregatedComponents;
    }

    public static ElementFilterList combineFilters(ElementFilterList oneElementFilterList,
                                            @SuppressWarnings("UnusedParameters") XPathComponent otherCopyWithModifiedType) {
        // should combine otherCopyWithModifiedType.elementFilterList here as well
        return oneElementFilterList;
    }

    public static ElementFilterList combineFilters(XPathComponent one,
                                            @SuppressWarnings("UnusedParameters") XPathComponent other,
                                            XPathComponent otherCopyWithModifiedType) {
        return combineFilters(one.elementFilterList, otherCopyWithModifiedType);
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