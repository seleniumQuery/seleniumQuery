package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.filter.ElementFilterList;

import java.util.List;

/*
 * if CONDITIONAL_SIMPLE, then the expr can be just appended to other, such as:
 * //*[@other][@thisSelector]
 *
 * @see also {@link io.github.seleniumquery.by.xpath.component.ConditionToAllComponent}
 */
public class ConditionSimpleComponent extends ConditionComponent {

    private final static String EMPTY_XPATH_EXPRESSION = "";

    /**
     * Creates a XPath Component that is empty (has no XPath expression) and no Element Filter.
     */
    public ConditionSimpleComponent() {
        this(ElementFilter.FILTER_NOTHING);
    }

    public ConditionSimpleComponent(String xPathExpression) {
        this(xPathExpression, ElementFilter.FILTER_NOTHING);
    }

    public ConditionSimpleComponent(ElementFilter filter) {
        this(EMPTY_XPATH_EXPRESSION, filter);
    }

    public ConditionSimpleComponent(String xPathExpression, ElementFilter filter) {
        super(xPathExpression, ComponentUtils.toElementFilterList(filter));
    }

    protected ConditionSimpleComponent(String xPathExpression, List<XPathComponent> combinatedComponents, ElementFilterList elementFilterList) {
        super(xPathExpression, combinatedComponents, elementFilterList);
    }

    @Override
        public String mergeIntoExpression(String sourceXPathExpression) {
        return merge(sourceXPathExpression, this.xPathExpression);
    }

        @Override
        public String mergeExpressionAsCondition(String sourceXPathExpression) {
        return mergeAsCondition(sourceXPathExpression, this.xPathExpression);
    }

    public static String merge(String sourceXPathExpression, String otherXPathExpression) {
        if (sourceXPathExpression.endsWith("]")) {
            // because the previous was merged as a conditional, and we are a conditional as well, so we just 'AND it
            return sourceXPathExpression.substring(0, sourceXPathExpression.length()-1) + " and " + otherXPathExpression.substring(1);
        }
        return sourceXPathExpression + otherXPathExpression;
    }
    public static String mergeAsCondition(String sourceXPathExpression, String otherXPathExpression) {
        if (sourceXPathExpression.equals(MATCH_EVERYTHING_XPATH_CONDITIONAL)) {
            return ComponentUtils.removeBraces(otherXPathExpression);
        }
        return sourceXPathExpression + " and " + ComponentUtils.removeBraces(otherXPathExpression);
    }

    @Override
    public ConditionSimpleComponent cloneComponent() {
        return new ConditionSimpleComponent(this.xPathExpression, this.combinatedComponents, this.elementFilterList);
    }

    @Override
    public ConditionSimpleComponent cloneAndCombineTo(Combinable other) {
        XPathComponent otherCopy = other.cloneComponent();
        return new ConditionSimpleComponent(this.xPathExpression,
                ComponentUtils.joinComponents(this.combinatedComponents, otherCopy),
                ComponentUtils.joinFilters(this.elementFilterList, otherCopy));
    }

}