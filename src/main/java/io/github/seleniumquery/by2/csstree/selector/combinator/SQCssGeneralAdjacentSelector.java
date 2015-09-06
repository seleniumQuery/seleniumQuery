package io.github.seleniumquery.by.csstree.selector.combinator;

import io.github.seleniumquery.by.csstree.selector.SQCssSelector;

public class SQCssGeneralAdjacentSelector extends SQCssCombinationSelector {

    public SQCssGeneralAdjacentSelector(SQCssSelector leftSideSelector, SQCssSelector rightSideSelector) {
        super("~", "/following-sibling::*[true()]", leftSideSelector, rightSideSelector);
    }

    public SQCssSelector getLeftSideSelector() {
        return leftSideSelector;
    }

    public SQCssSelector getRightSideSelector() {
        return rightSideSelector;
    }

}