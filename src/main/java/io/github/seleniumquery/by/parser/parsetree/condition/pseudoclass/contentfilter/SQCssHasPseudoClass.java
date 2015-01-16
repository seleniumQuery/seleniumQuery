package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.contentfilter;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssFunctionalPseudoClassCondition;

public class SQCssHasPseudoClass extends SQCssFunctionalPseudoClassCondition {

    public static final String PSEUDO = "has";

    public SQCssHasPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}