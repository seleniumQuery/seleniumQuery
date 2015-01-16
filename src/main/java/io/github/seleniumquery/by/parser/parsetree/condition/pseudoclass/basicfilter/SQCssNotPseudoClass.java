package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.basicfilter;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssFunctionalPseudoClassCondition;

public class SQCssNotPseudoClass extends SQCssFunctionalPseudoClassCondition {

    // :not() are translated into :not-sq() by the pre-parser
    public static final String PSEUDO = "not-sq";

    /* when used without args, such as "div:not", the pre-parser does not translate it. it is invalid,
       but we still match it, so we can return a proper error message */
    public static final String PSEUDO_PURE_NOT = "not";

    public SQCssNotPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}