package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.form;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssEnabledPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "enabled";

    public SQCssEnabledPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}