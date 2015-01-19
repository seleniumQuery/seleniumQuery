package io.github.seleniumquery.by.csstree.condition.attribute;

/**
 * [attribute^=stringToStart]
 *
 * CASE INsensitive!
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssStartsWithAttributeCondition extends SQCssAttributeCondition {

    public SQCssStartsWithAttributeCondition(String attributeName, String wantedValue) {
        super(attributeName, wantedValue);
    }

}