package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.form;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssFocusPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "focus";

    public SQCssFocusPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}