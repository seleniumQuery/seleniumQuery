package io.github.seleniumquery.by.csstree.selector.combinator;

import io.github.seleniumquery.by.csstree.selector.SQCssSelector;

public class SQCssDirectAdjacentSelector extends SQCssCombinationSelector {

    public SQCssDirectAdjacentSelector(SQCssSelector leftSideSelector, SQCssSelector rightSideSelector) {
        super("+", "/following-sibling::*[position() = 1]", leftSideSelector, rightSideSelector);
    }

    public SQCssSelector getLeftSideSelector() {
        return leftSideSelector;
    }

    public SQCssSelector getRightSideSelector() {
        return rightSideSelector;
    }

}