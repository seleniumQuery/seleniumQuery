package io.github.seleniumquery.by.parser.parsetree.condition.attribute;

/**
 * [attribute*=stringToContain]
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssContainsSubstringAttributeCondition extends SQCssAttributeCondition {

    public SQCssContainsSubstringAttributeCondition(String attributeName, String wantedValue) {
        super(attributeName, wantedValue);
    }

}