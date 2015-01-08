package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.filter.ElementFilterList;
import io.github.seleniumquery.by.xpath.CssCombinationType;

import java.util.List;

public class SimpleConditionalComponent extends ConditionComponent {

    private final static String EMPTY_XPATH_EXPRESSION = "";

    /**
     * Creates a XPath Component that is empty (has no XPath expression) and no Element Filter.
     */
    public SimpleConditionalComponent() {
        this(ElementFilter.FILTER_NOTHING);
    }

    public SimpleConditionalComponent(String xPathExpression) {
        this(xPathExpression, ElementFilter.FILTER_NOTHING);
    }

    public SimpleConditionalComponent(ElementFilter filter) {
        this(EMPTY_XPATH_EXPRESSION, filter);
    }

    public SimpleConditionalComponent(String xPathExpression, ElementFilter filter) {
        super(xPathExpression, ComponentUtils.toElementFilterList(filter));
    }

    SimpleConditionalComponent(String xPathExpression, List<XPathComponent> combinatedComponents, ElementFilterList elementFilterList) {
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

}