package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.xpath.CssCombinationType;

public class AdjacentComponent extends XPathComponent {

    public static TagComponent combine(TagComponent one, TagComponent other) {
        AdjacentComponent otherCopyWithModifiedType = new AdjacentComponent(other);
        return new TagComponent(one.xPathExpression,
                                ComponentUtils.joinComponents(one.combinatedComponents, otherCopyWithModifiedType),
                                ComponentUtils.joinFilters(one.elementFilterList, otherCopyWithModifiedType));
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