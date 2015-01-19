package io.github.seleniumquery.by.csstree.condition.attribute;

/**
 * [simple]
 * [restart="never"]
 *
 * Case INsensitive!
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssEqualsOrHasAttributeCondition extends SQCssAttributeCondition {

    /**
     * [simple]
     * Attribute value is null in this case.
     */
    public SQCssEqualsOrHasAttributeCondition(String attributeName) {
        super(attributeName, null);
    }

    /**
     * [restart="never"]
     */
    public SQCssEqualsOrHasAttributeCondition(String attributeName, String wantedValue) {
        super(attributeName, wantedValue);
    }

}