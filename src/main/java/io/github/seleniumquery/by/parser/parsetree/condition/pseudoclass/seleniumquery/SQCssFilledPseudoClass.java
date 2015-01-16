package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.seleniumquery;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssFilledPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "filled";

    public SQCssFilledPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}