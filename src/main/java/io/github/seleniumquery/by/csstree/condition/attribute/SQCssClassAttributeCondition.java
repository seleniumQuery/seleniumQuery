package io.github.seleniumquery.by.csstree.condition.attribute;

import io.github.seleniumquery.by.csstree.condition.SQCssCondition;
import io.github.seleniumquery.by.locator.SQLocator;
import io.github.seleniumquery.by.locator.SQLocatorUtils;

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

    public SQLocator toSQLocator(SQLocator leftLocator) {
        String newCssSelector = SQLocatorUtils.cssMerge(leftLocator.getCssSelector(), toCSS());
        String newXPathExpression = SQLocatorUtils.conditionalSimpleXPathMerge(leftLocator.getXPathExpression(), toXPath());
        return new SQLocator(newCssSelector, newXPathExpression, leftLocator);
    }

    private String toXPath() {
        return "contains(concat(' ', normalize-space(@class), ' '), ' " + unescapedClassName + " ')";
    }

    private String toCSS() {
        return "." + this.unescapedClassName;
    }

}