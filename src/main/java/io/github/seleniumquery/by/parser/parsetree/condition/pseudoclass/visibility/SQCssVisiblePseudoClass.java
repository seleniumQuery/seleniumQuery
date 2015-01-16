package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.visibility;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssVisiblePseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "visible";

    public SQCssVisiblePseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}