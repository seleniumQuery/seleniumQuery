package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.basicfilter;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssHeaderPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "header";

    public SQCssHeaderPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}