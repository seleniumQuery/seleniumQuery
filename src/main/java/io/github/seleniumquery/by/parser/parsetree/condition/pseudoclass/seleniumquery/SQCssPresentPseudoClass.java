package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.seleniumquery;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssPresentPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "present";

    public SQCssPresentPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}