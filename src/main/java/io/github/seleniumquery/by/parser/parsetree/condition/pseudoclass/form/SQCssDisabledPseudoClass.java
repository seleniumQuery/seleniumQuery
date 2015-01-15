package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.form;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssDisabledPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "disabled";

    public SQCssDisabledPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}