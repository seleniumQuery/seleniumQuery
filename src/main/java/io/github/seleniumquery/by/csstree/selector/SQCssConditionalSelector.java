package io.github.seleniumquery.by.csstree.selector;

import io.github.seleniumquery.by.csstree.condition.SQCssCondition;

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

}