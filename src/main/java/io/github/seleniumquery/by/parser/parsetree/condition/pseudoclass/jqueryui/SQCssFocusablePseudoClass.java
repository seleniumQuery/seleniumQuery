package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.jqueryui;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssFocusablePseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "focusable";

    public SQCssFocusablePseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}