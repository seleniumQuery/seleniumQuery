package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.xpath.CssCombinationType;

public class DescendantDirectComponent extends XPathComponent {

    public static XPathComponent combine(XPathComponent one, XPathComponent other) {
        ComponentUtils.assertTagComponent(one);
        DescendantDirectComponent otherCopyWithModifiedType = new DescendantDirectComponent(other);
        return new TagComponent(one.xPathExpression,
                                ComponentUtils.combineComponents(one, other, otherCopyWithModifiedType),
                                ComponentUtils.combineFilters(one, other, otherCopyWithModifiedType));
    }

    private DescendantDirectComponent(XPathComponent other) {
        super(other.xPathExpression, other.combinatedComponents, other.elementFilterList);
    }

    @Override
    public String mergeIntoExpression(String sourceXPathExpression) {
        return CssCombinationType.DESCENDANT_DIRECT.merge(sourceXPathExpression, this.xPathExpression);
    }

    @Override
    public String mergeExpressionAsCondition(String sourceXPathExpression) {
        return CssCombinationType.DESCENDANT_DIRECT.mergeAsCondition(sourceXPathExpression, this.xPathExpression);
    }

}