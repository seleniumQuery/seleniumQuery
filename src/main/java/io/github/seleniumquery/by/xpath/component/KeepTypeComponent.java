package io.github.seleniumquery.by.xpath.component;

public class KeepTypeComponent {

    public static XPathComponent combineKeepingType(XPathComponent one, XPathComponent other) {
        XPathComponent otherCopy = copy(other);

        if (one.getClass() == SimpleConditionalComponent.class) {
            return new SimpleConditionalComponent(one.xPathExpression,
                                                    ComponentUtils.combineComponents(one, other, otherCopy),
                                                    ComponentUtils.combineFilters(one, other, otherCopy));
        }
        if (one.getClass() == ConditionalAppliedToAllComponent.class) {
            return new ConditionalAppliedToAllComponent(one.xPathExpression,
                                                            ComponentUtils.combineComponents(one, other, otherCopy),
                                                            ComponentUtils.combineFilters(one, other, otherCopy));
        }
        if (one.getClass() == TagComponent.class) {
            return new TagComponent(one.xPathExpression,
                                                            ComponentUtils.combineComponents(one, other, otherCopy),
                                                            ComponentUtils.combineFilters(one, other, otherCopy));
        }
        throw new RuntimeException("Unexpected: "+one.getClass());
    }

    // im pretty sure other.cssCombinationType is always CONDITIONAL_SIMPLE here
    // maybe CONDITIONAL_SIPLE_APPLIED_TO_ALL...
    //
    // hmm... maybe it can be TAG as well.
    // gotta test this to make sure they all are ever called
    private static XPathComponent copy(XPathComponent other) {
        if (other.getClass() == SimpleConditionalComponent.class) {
            return new SimpleConditionalComponent(other.xPathExpression, other.combinatedComponents, other.elementFilterList);
        }
        if (other.getClass() == ConditionalAppliedToAllComponent.class) {
            return new ConditionalAppliedToAllComponent(other.xPathExpression, other.combinatedComponents, other.elementFilterList);
        }
        if (other.getClass() == TagComponent.class) {
            return new TagComponent(other.xPathExpression, other.combinatedComponents, other.elementFilterList);
        }
        throw new RuntimeException("Unexpected: "+other.getClass());
    }

}