package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.seleniumquery;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssBlankPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "blank";

    public SQCssBlankPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}