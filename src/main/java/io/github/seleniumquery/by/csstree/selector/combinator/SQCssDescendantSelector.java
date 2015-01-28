package io.github.seleniumquery.by.csstree.selector.combinator;

import io.github.seleniumquery.by.csstree.selector.SQCssSelector;

public class SQCssDescendantSelector  extends SQCssCombinationSelector {

    public SQCssDescendantSelector(SQCssSelector leftSideSelector, SQCssSelector rightSideSelector) {
        super(" ", "//*[true()]", leftSideSelector, rightSideSelector);
    }

    public SQCssSelector getAncestorSelector() {
        return leftSideSelector;
    }

    public SQCssSelector getDescendantSelector() {
        return rightSideSelector;
    }

}