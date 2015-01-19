package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.csstree.condition.SQCssCondition;

public abstract class SQCssPseudoClassCondition implements SQCssCondition {

    protected PseudoClassSelector pseudoClassSelector;

    public SQCssPseudoClassCondition(PseudoClassSelector pseudoClassSelector) {
        this.pseudoClassSelector = pseudoClassSelector;
    }

}