package io.github.seleniumquery.by.parser.parsetree.condition.attribute;

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

    public SQCssEqualsOrHasAttributeCondition(String attributeName, String wantedValue) {
        super(attributeName, wantedValue);
    }

}