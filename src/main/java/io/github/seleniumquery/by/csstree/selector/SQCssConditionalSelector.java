package io.github.seleniumquery.by.csstree.selector;

import io.github.seleniumquery.by.csstree.condition.SQCssCondition;
import io.github.seleniumquery.by.csstree.condition.attribute.SQCssClassAttributeCondition;
import io.github.seleniumquery.by.locator.SQLocator;

public class SQCssConditionalSelector implements SQCssSelector {

    private final SQCssSelector sqCssSelector;
    private final SQCssCondition sqCssCondition;

    public SQCssConditionalSelector(SQCssSelector sqCssSelector, SQCssCondition sqCssCondition) {
        this.sqCssSelector = sqCssSelector;
        this.sqCssCondition = sqCssCondition;
    }

    public SQCssSelector getSqCssSelector() {
        return sqCssSelector;
    }

    public SQCssCondition getSqCssCondition() {
        return sqCssCondition;
    }

    public SQLocator toSQLocator() {
        SQLocator sqLocator = ((SQCssTagNameSelector) sqCssSelector).toSQLocator();
        return ((SQCssClassAttributeCondition) sqCssCondition).toSQLocator(sqLocator);
    }

}