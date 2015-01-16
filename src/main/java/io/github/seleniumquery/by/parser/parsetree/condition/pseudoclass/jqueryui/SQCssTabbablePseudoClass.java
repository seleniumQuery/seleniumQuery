package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.jqueryui;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssTabbablePseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "tabbable";

    public SQCssTabbablePseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}