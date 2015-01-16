package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.seleniumquery;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssUncheckedPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "unchecked";

    public SQCssUncheckedPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}