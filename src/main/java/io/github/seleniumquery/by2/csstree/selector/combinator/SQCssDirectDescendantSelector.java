package io.github.seleniumquery.by.csstree.selector.combinator;

import io.github.seleniumquery.by.csstree.selector.SQCssSelector;

public class SQCssDirectDescendantSelector extends SQCssCombinationSelector {

    public SQCssDirectDescendantSelector(SQCssSelector leftSideSelector, SQCssSelector rightSideSelector) {
        super(">", "/*[true()]", leftSideSelector, rightSideSelector);
    }

    public SQCssSelector getAncestorSelector() {
        return leftSideSelector;
    }

    public SQCssSelector getDescendantSelector() {
        return rightSideSelector;
    }

}