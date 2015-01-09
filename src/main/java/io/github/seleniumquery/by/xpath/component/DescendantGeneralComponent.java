package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.xpath.UnsupportedConditionalSelector;

/**
 * cssA cssB -> "//" -> xpathA//xpathB
 */
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
        if ("*".equals(this.xPathExpression)) {
            return sourceXPathExpression + "//*";
        }
        return sourceXPathExpression + "//*[self::" + this.xPathExpression + "]";
    }

    @Override
    public String mergeExpressionAsCondition(String sourceXPathExpression) {
        throw new UnsupportedConditionalSelector("The 'general descendant' (\"a b\") selector is not supported as condition inside other selectors.");
    }

}