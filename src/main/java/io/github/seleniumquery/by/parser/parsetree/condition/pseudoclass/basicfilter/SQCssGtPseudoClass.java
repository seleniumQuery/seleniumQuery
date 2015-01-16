package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.basicfilter;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssFunctionalPseudoClassCondition;

public class SQCssGtPseudoClass extends SQCssFunctionalPseudoClassCondition {

    public static final String PSEUDO = "gt";

    public SQCssGtPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}