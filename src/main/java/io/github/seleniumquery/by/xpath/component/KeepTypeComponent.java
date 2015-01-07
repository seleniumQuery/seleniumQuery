package io.github.seleniumquery.by.xpath.component;

public class KeepTypeComponent {

    public static XPathComponent createKeepingType(XPathComponent one, XPathComponent other) {
        XPathComponent otherCopyWithModifiedType;
        switch (other.cssCombinationType) {
            case CONDITIONAL_SIMPLE:
                otherCopyWithModifiedType = new SimpleConditionalComponent(other.xPathExpression, other.combinatedComponents, other.elementFilterList); break;
            case CONDITIONAL_TO_ALL:
                otherCopyWithModifiedType = new ConditionalAppliedToAllComponent(other.xPathExpression, other.combinatedComponents, other.elementFilterList); break;
            case TAG:
                otherCopyWithModifiedType = new TagComponent(other.xPathExpression, other.combinatedComponents, other.elementFilterList); break;
            default: throw new RuntimeException("Unexpected: "+other.cssCombinationType+ " "+other.getClass());
        }

        switch (one.cssCombinationType) {
            case CONDITIONAL_SIMPLE:
                return new SimpleConditionalComponent(one.xPathExpression,
                        ComponentUtils.combineComponents(one, other, otherCopyWithModifiedType),
                        ComponentUtils.combineFilters(one, other, otherCopyWithModifiedType));
            case CONDITIONAL_TO_ALL:
                return new ConditionalAppliedToAllComponent(one.xPathExpression,
                        ComponentUtils.combineComponents(one, other, otherCopyWithModifiedType),
                        ComponentUtils.combineFilters(one, other, otherCopyWithModifiedType));
            case TAG:
                // im pretty sure other.cssCombinationType is always CONDITIONAL_SIMPLE here
                // maybe CONDITIONAL_SIPLE_APPLIED_TO_ALL...
                //
                // hmm... maybe it can be TAG as well.
                return new TagComponent(one.xPathExpression,
                        ComponentUtils.combineComponents(one, other, otherCopyWithModifiedType),
                        ComponentUtils.combineFilters(one, other, otherCopyWithModifiedType));
            default:
            throw new RuntimeException("Unexpected: Only TAG cssCombinationType is expected here. Got: "+one.cssCombinationType);
        }

    }

}