package io.github.seleniumquery.by.parser.parsetree.condition.attribute;

import io.github.seleniumquery.by.parser.parsetree.condition.SQCssCondition;

/**
 * A class that holds an attribute name and a wanted value.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public abstract class SQCssAttributeCondition implements SQCssCondition {

    private String attributeName;
    private String wantedValue;

    protected SQCssAttributeCondition(String attributeName, String wantedValue) {
        this.attributeName = attributeName;
        this.wantedValue = wantedValue;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getWantedValue() {
        return wantedValue;
    }

}