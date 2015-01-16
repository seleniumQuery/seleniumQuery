package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.childfilter;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssOnlyOfTypePseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "only-of-type";

    public SQCssOnlyOfTypePseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}