package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.form;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssCheckedPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "checked";

    public SQCssCheckedPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}