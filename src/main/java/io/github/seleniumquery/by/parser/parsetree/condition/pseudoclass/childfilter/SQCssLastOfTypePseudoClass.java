package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.childfilter;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssLastOfTypePseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "last-of-type";

    public SQCssLastOfTypePseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}