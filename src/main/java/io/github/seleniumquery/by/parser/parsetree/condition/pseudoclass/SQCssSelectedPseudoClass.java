package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;

public class SQCssSelectedPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "selected";

    public SQCssSelectedPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}