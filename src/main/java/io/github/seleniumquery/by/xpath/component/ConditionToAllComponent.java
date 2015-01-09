package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.filter.ElementFilterList;

import java.util.List;

/**
 * Represents a condition that must be applied to all the expression, not just appended (using AND) to it.
 *
 * This must be applied to the whole result of the other, such as:
 * (//*[@other])[@thisSelector]
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
        return "(" + sourceXPathExpression + ")" + this.xPathExpression;
    }

    @Override
    public String mergeExpressionAsCondition(String sourceXPathExpression) {
        if (sourceXPathExpression.equals(MATCH_EVERYTHING_XPATH_CONDITIONAL)) {
            return ComponentUtils.removeBraces(this.xPathExpression);
        }
        return sourceXPathExpression + " and " + ComponentUtils.removeBraces(this.xPathExpression);
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