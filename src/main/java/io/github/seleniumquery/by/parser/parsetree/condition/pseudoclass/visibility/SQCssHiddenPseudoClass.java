package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.visibility;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssHiddenPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "hidden";

    public SQCssHiddenPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}