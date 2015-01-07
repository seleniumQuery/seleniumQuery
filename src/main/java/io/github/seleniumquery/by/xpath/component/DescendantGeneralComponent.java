package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.xpath.CssCombinationType;

public class DescendantGeneralComponent extends XPathComponent {

    public static XPathComponent create(XPathComponent one, XPathComponent other) {
        DescendantGeneralComponent otherCopyWithModifiedType = new DescendantGeneralComponent(other);
        switch (one.cssCombinationType) {
            case TAG:
                return new TagComponent(one.xPathExpression,
                        ComponentUtils.combineComponents(one, other, otherCopyWithModifiedType),
                        ComponentUtils.combineFilters(one, other, otherCopyWithModifiedType));
            default:
                throw new RuntimeException("Unexpected: Only TAG cssCombinationType is expected here. Got: "+one.cssCombinationType);
        }
    }

    private DescendantGeneralComponent(XPathComponent other) {
        super(other.xPathExpression, other.combinatedComponents, other.elementFilterList,  CssCombinationType.DESCENDANT_GENERAL);
    }


    @Override
    public String mergeExpression(String sourceXPathExpression) {
        return CssCombinationType.DESCENDANT_GENERAL.merge(sourceXPathExpression, null, this.xPathExpression);
    }

    @Override
    public String mergeExpressionAsCondition(String sourceXPathExpression) {
        return CssCombinationType.DESCENDANT_GENERAL.mergeAsCondition(sourceXPathExpression, null, this.xPathExpression);
    }

}