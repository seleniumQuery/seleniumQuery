package io.github.seleniumquery.by.xpath.component;

/**
 * cssA ~ cssB
 * cssA + cssB
 */
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
        return merge(sourceXPathExpression, this.xPathExpression);
    }

    @Override
    public String mergeExpressionAsCondition(String sourceXPathExpression) {
        return merge(sourceXPathExpression, this.xPathExpression);
    }

    // "/following-sibling::"
    // This one will be used by the "General Adjacent" and the "Direct Adjacent" selectors
    // (in order to differentiate, the "Direct Adjacent" selector will itself add a [position()=1] to its expression)
    private String merge(String sourceXPathExpression, String otherXPathExpression) {
        return sourceXPathExpression + "/following-sibling::" + otherXPathExpression;
    }

}