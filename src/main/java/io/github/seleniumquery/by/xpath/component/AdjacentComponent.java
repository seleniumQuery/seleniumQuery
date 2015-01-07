package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.xpath.CssCombinationType;

public class AdjacentComponent extends XPathComponent {

    public static XPathComponent combine(XPathComponent one, XPathComponent other) {
        ComponentUtils.assertTagComponent(one);
        AdjacentComponent otherCopyWithModifiedType = new AdjacentComponent(other);
        return new TagComponent(one.xPathExpression,
                                ComponentUtils.combineComponents(one, other, otherCopyWithModifiedType),
                                ComponentUtils.combineFilters(one, other, otherCopyWithModifiedType));
    }

    private AdjacentComponent(XPathComponent other) {
        super(other.xPathExpression, other.combinatedComponents, other.elementFilterList);
    }

    @Override
    public String mergeIntoExpression(String sourceXPathExpression) {
        return CssCombinationType.ADJACENT.merge(sourceXPathExpression, this.xPathExpression);
    }

    @Override
    public String mergeExpressionAsCondition(String sourceXPathExpression) {
        return CssCombinationType.ADJACENT.mergeAsCondition(sourceXPathExpression, this.xPathExpression);
    }

}