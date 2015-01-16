package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.childfilter;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssFirstOfTypePseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "first-of-type";

    public SQCssFirstOfTypePseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}