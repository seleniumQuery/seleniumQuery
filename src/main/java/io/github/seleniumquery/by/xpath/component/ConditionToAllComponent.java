package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.filter.ElementFilterList;
import io.github.seleniumquery.by.xpath.CssCombinationType;
import io.github.seleniumquery.by.xpath.component.special.Combinable;

import java.util.List;

/**
 * Represents a condition that must be applied to all the expression, not just appended (using AND) to it.
 */
public class ConditionToAllComponent extends ConditionComponent {

    public ConditionToAllComponent(String xPathExpression) {
        super(xPathExpression, ComponentUtils.emptyElementFilterList());
    }

    ConditionToAllComponent(String xPathExpression, List<XPathComponent> combinatedComponents, ElementFilterList elementFilterList) {
        super(xPathExpression, combinatedComponents, elementFilterList);
    }

    @Override
    public String mergeIntoExpression(String sourceXPathExpression) {
        return CssCombinationType.CONDITIONAL_TO_ALL.merge(sourceXPathExpression, this.xPathExpression);
    }

    @Override
    public String mergeExpressionAsCondition(String sourceXPathExpression) {
        return CssCombinationType.CONDITIONAL_TO_ALL.mergeAsCondition(sourceXPathExpression, this.xPathExpression);
    }

    @Override
    public ConditionToAllComponent cloneComponent() {
        return new ConditionToAllComponent(this.xPathExpression, this.combinatedComponents, this.elementFilterList);
    }

    @Override
    public ConditionToAllComponent cloneAndCombineTo(Combinable other) {
        XPathComponent otherCopy = other.cloneComponent();
        return new ConditionToAllComponent(this.xPathExpression,
                ComponentUtils.joinComponents(this.combinatedComponents, otherCopy),
                ComponentUtils.joinFilters(this.elementFilterList, otherCopy));
    }

}