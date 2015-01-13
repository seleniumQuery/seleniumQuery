package io.github.seleniumquery.by.parser.parsetree.condition.attribute;

/**
 * [languages|="pt"]
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssContainsPrefixAttributeCondition extends SQCssAttributeCondition {

    public SQCssContainsPrefixAttributeCondition(String attributeName, String wantedValue) {
        super(attributeName, wantedValue);
    }

}