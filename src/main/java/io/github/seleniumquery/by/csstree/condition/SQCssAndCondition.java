package io.github.seleniumquery.by.csstree.condition;

/**
 * Chains conditions.
 * Example:
 * - tag.A.B
 * will be parsed/translated into
 * - tag[AndCondition(ClassCondition("A"), ClassCondition("B"))]
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssAndCondition implements SQCssCondition {

    private SQCssCondition firstCondition;
    private SQCssCondition secondCondition;

    public SQCssAndCondition(SQCssCondition firstCondition, SQCssCondition secondCondition) {
        this.firstCondition = firstCondition;
        this.secondCondition = secondCondition;
    }

    public SQCssCondition getFirstCondition() {
        return firstCondition;
    }

    public SQCssCondition getSecondCondition() {
        return secondCondition;
    }

}