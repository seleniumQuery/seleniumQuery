package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.xpath.CssCombinationType;

public class DescendantDirectComponent extends XPathComponent {

    public static XPathComponent create(XPathComponent one, XPathComponent other) {
        DescendantDirectComponent otherCopyWithModifiedType = new DescendantDirectComponent(other);
        switch (one.cssCombinationType) {
            case TAG:
                return new TagComponent(one.xPathExpression,
                        ComponentUtils.combineComponents(one, other, otherCopyWithModifiedType),
                        ComponentUtils.combineFilters(one, other, otherCopyWithModifiedType));
            default:
                throw new RuntimeException("Unexpected: Only TAG cssCombinationType is expected here. Got: "+one.cssCombinationType);
        }
    }

    private DescendantDirectComponent(XPathComponent other) {
        super(other.xPathExpression, other.combinatedComponents, other.elementFilterList,  CssCombinationType.DESCENDANT_DIRECT);
    }

    @Override
    public String mergeExpression(String sourceXPathExpression) {
        return CssCombinationType.DESCENDANT_DIRECT.merge(sourceXPathExpression, null, this.xPathExpression);
    }

    @Override
    public String mergeExpressionAsCondition(String sourceXPathExpression) {
        return CssCombinationType.DESCENDANT_DIRECT.mergeAsCondition(sourceXPathExpression, null, this.xPathExpression);
    }

}