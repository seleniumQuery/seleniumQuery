package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.contentfilter;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssEmptyPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "empty";

    public SQCssEmptyPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}