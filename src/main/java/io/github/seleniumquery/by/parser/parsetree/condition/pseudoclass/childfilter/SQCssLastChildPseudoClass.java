package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.childfilter;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssLastChildPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "last-child";

    public SQCssLastChildPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}