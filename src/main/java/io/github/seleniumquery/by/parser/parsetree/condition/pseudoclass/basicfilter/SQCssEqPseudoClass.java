package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.basicfilter;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssFunctionalPseudoClassCondition;

public class SQCssEqPseudoClass extends SQCssFunctionalPseudoClassCondition {

    public static final String PSEUDO = "eq";

    public SQCssEqPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}