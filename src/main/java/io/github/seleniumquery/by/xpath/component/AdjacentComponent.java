package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.xpath.CssCombinationType;

public class AdjacentComponent extends XPathComponent {

    public static XPathComponent create(XPathComponent one, XPathComponent other) {
        AdjacentComponent otherCopyWithModifiedType = new AdjacentComponent(other);
        switch (one.cssCombinationType) {
            case TAG:
                return new TagComponent(one.xPathExpression,
                        ComponentUtils.combineComponents(one, other, otherCopyWithModifiedType),
                        ComponentUtils.combineFilters(one, other, otherCopyWithModifiedType));
            default:
                throw new RuntimeException("Unexpected: Only TAG cssCombinationType is expected here. Got: "+one.cssCombinationType);
        }
    }

    private AdjacentComponent(XPathComponent other) {
        super(other.xPathExpression, other.combinatedComponents, other.elementFilterList, CssCombinationType.ADJACENT);
    }

    @Override
    public String mergeExpression(String sourceXPathExpression) {
        return CssCombinationType.ADJACENT.merge(sourceXPathExpression, null, this.xPathExpression);
    }

    @Override
    public String mergeExpressionAsCondition(String sourceXPathExpression) {
        return CssCombinationType.ADJACENT.mergeAsCondition(sourceXPathExpression, null, this.xPathExpression);
    }

}