package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.filter.ElementFilterList;
import io.github.seleniumquery.by.xpath.CssCombinationType;

import java.util.List;

public class TagComponent extends XPathComponent {

    public TagComponent(String xPathExpression) {
        super(xPathExpression, ComponentUtils.toElementFilterList(ElementFilter.FILTER_NOTHING), CssCombinationType.TAG);
    }

    TagComponent(String xPathExpression, List<XPathComponent> combinatedComponents, ElementFilterList elementFilterList) {
        super(xPathExpression, combinatedComponents, elementFilterList, CssCombinationType.TAG);
    }

    @Override
    public String toXPath() {
        if ("*".equals(this.xPathExpression)) {
            this.xPathExpression = ".//*";
        } else {
            this.xPathExpression = ".//*[self::" + this.xPathExpression+"]";
        }
        for (XPathComponent other : combinatedComponents) {
            merge(other);
        }
        return "(" + this.xPathExpression + ")";
    }

    private void merge(XPathComponent other) {
        this.elementFilterList = other.mergeFilter(this.elementFilterList);
        this.xPathExpression = other.mergeExpression(this.xPathExpression);
    }

    @Override
    public String toXPathCondition() {
        if ("*".equals(this.xPathExpression)) {
            this.xPathExpression = MATCH_EVERYTHING_XPATH_CONDITIONAL;
        } else {
            this.xPathExpression = "local-name() = '"+this.xPathExpression+"'";
        }
        for (XPathComponent other : combinatedComponents) {
            mergeAsCondition(other);
        }
        return this.xPathExpression;
    }

    private void mergeAsCondition(XPathComponent other) {
        this.elementFilterList = other.mergeFilterAsCondition(this.elementFilterList);
        this.xPathExpression = other.mergeExpressionAsCondition(this.xPathExpression);
    }


    @Override
    public String mergeExpression(String sourceXPathExpression) {
        return CssCombinationType.TAG.merge(sourceXPathExpression, null, this.xPathExpression);
    }

    @Override
    public String mergeExpressionAsCondition(String sourceXPathExpression) {
        return CssCombinationType.TAG.mergeAsCondition(sourceXPathExpression, null, this.xPathExpression);
    }

}