package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;

public class SQCssFunctionalPseudoClassCondition extends SQCssPseudoClassCondition {

    protected String argument;

    public SQCssFunctionalPseudoClassCondition(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
        this.argument = pseudoClassSelector.getPseudoClassContent();
    }

    public String getArgument() {
        return argument;
    }

}