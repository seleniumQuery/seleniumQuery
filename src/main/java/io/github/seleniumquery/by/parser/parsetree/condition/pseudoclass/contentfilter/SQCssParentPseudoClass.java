package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.contentfilter;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssParentPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "parent";

    public SQCssParentPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}