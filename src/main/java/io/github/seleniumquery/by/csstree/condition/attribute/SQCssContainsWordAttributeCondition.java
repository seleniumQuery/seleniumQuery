package io.github.seleniumquery.by.csstree.condition.attribute;

/**
 * [values~="10"]
 *
 * Case INsensitive!
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssContainsWordAttributeCondition extends SQCssAttributeCondition {

    public SQCssContainsWordAttributeCondition(String attributeName, String wantedValue) {
        super(attributeName, wantedValue);
    }

}