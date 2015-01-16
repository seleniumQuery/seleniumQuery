package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.childfilter;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssFirstChildPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "first-child";

    public SQCssFirstChildPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}