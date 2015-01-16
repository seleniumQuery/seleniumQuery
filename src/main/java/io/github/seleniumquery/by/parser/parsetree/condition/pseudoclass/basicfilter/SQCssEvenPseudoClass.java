package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.basicfilter;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssEvenPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "even";

    public SQCssEvenPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}