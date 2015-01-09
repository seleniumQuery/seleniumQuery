package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.filter.ElementFilterList;
import io.github.seleniumquery.by.xpath.CssCombinationType;
import io.github.seleniumquery.by.xpath.component.special.Combinable;

import java.util.List;

public class ConditionSimpleComponent extends ConditionComponent {

    private final static String EMPTY_XPATH_EXPRESSION = "";

    /**
     * Creates a XPath Component that is empty (has no XPath expression) and no Element Filter.
     */
    public ConditionSimpleComponent() {
        this(ElementFilter.FILTER_NOTHING);
    }

    public ConditionSimpleComponent(String xPathExpression) {
        this(xPathExpression, ElementFilter.FILTER_NOTHING);
    }

    public ConditionSimpleComponent(ElementFilter filter) {
        this(EMPTY_XPATH_EXPRESSION, filter);
    }

    public ConditionSimpleComponent(String xPathExpression, ElementFilter filter) {
        super(xPathExpression, ComponentUtils.toElementFilterList(filter));
    }

    protected ConditionSimpleComponent(String xPathExpression, List<XPathComponent> combinatedComponents, ElementFilterList elementFilterList) {
        super(xPathExpression, combinatedComponents, elementFilterList);
    }

    @Override
    public String mergeIntoExpression(String sourceXPathExpression) {
        return CssCombinationType.CONDITIONAL_SIMPLE.merge(sourceXPathExpression, this.xPathExpression);
    }

    @Override
    public String mergeExpressionAsCondition(String sourceXPathExpression) {
        return CssCombinationType.CONDITIONAL_SIMPLE.mergeAsCondition(sourceXPathExpression, this.xPathExpression);
    }

    @Override
    public ConditionSimpleComponent cloneComponent() {
        return new ConditionSimpleComponent(this.xPathExpression, this.combinatedComponents, this.elementFilterList);
    }

    @Override
    public ConditionSimpleComponent cloneAndCombineTo(Combinable other) {
        XPathComponent otherCopy = other.cloneComponent();
        return new ConditionSimpleComponent(this.xPathExpression,
                ComponentUtils.joinComponents(this.combinatedComponents, otherCopy),
                ComponentUtils.joinFilters(this.elementFilterList, otherCopy));
    }

}