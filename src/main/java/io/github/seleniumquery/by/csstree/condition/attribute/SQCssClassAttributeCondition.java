package io.github.seleniumquery.by.csstree.condition.attribute;

import io.github.seleniumquery.by.parser.parsetree.condition.SQCssCondition;

/**
 * .class
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssClassAttributeCondition implements SQCssCondition {

    private String unescapedClassName;

    public SQCssClassAttributeCondition(String unescapedClassName) {
        this.unescapedClassName = unescapedClassName;
    }

    public String getClassName() {
        return unescapedClassName;
    }

}