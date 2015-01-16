package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.contentfilter;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssFunctionalPseudoClassCondition;

public class SQCssContainsPseudoClass extends SQCssFunctionalPseudoClassCondition {

    public static final String PSEUDO = "contains";

    public SQCssContainsPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}