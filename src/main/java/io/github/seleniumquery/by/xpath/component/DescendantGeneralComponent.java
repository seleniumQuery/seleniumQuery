package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.xpath.CssCombinationType;

public class DescendantGeneralComponent extends XPathComponent {

    public static TagComponent combine(TagComponent one, TagComponent other) {
        DescendantGeneralComponent otherCopyWithModifiedType = new DescendantGeneralComponent(other);
        return new TagComponent(one.xPathExpression,
                                ComponentUtils.joinComponents(one.combinatedComponents, otherCopyWithModifiedType),
                                ComponentUtils.joinFilters(one.elementFilterList, otherCopyWithModifiedType));
    }

    private DescendantGeneralComponent(XPathComponent other) {
        super(other.xPathExpression, other.combinatedComponents, other.elementFilterList);
    }

    @Override
    public String mergeIntoExpression(String sourceXPathExpression) {
        return CssCombinationType.DESCENDANT_GENERAL.merge(sourceXPathExpression, this.xPathExpression);
    }

    @Override
    public String mergeExpressionAsCondition(String sourceXPathExpression) {
        return CssCombinationType.DESCENDANT_GENERAL.mergeAsCondition(sourceXPathExpression, this.xPathExpression);
    }

}