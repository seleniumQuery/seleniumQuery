package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.basicfilter;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.SQCssPseudoClassCondition;

public class SQCssFirstPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "first";

    public SQCssFirstPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

}