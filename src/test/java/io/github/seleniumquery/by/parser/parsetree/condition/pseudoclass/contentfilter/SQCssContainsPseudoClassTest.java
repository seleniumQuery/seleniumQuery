package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.contentfilter;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertFunctionalPseudo;

public class SQCssContainsPseudoClassTest {

    @Test
    public void translate() {
        assertFunctionalPseudo(":contains", SQCssContainsPseudoClass.class);
    }

}