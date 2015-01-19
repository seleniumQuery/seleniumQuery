package io.github.seleniumquery.by.parser.parsetree.selector.combinator;

import io.github.seleniumquery.by.parser.parsetree.selector.SQCssSelector;

public class SQCssDirectAdjacentSelector implements SQCssSelector {

    private SQCssSelector ancestorSelector;
    private SQCssSelector descendantSelector;

    public SQCssDirectAdjacentSelector(SQCssSelector ancestorSelector, SQCssSelector descendantSelector) {
        this.ancestorSelector = ancestorSelector;
        this.descendantSelector = descendantSelector;
    }

    public SQCssSelector getAncestorSelector() {
        return ancestorSelector;
    }

    public SQCssSelector getDescendantSelector() {
        return descendantSelector;
    }

}