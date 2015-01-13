package io.github.seleniumquery.by.parser.parsetree.condition.attribute;

/**
 * [attribute$=stringToEnd]
 *
 * CASE SENSITIVE! http://api.jquery.com/attribute-ends-with-selector/
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssEndsWithAttributeCondition extends SQCssAttributeCondition {

    public SQCssEndsWithAttributeCondition(String attributeName, String wantedValue) {
        super(attributeName, wantedValue);
    }

}