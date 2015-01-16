package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.contentfilter;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertFunctionalPseudo;

public class SQCssHasPseudoClassTest {

    @Test
    public void translate() {
        assertFunctionalPseudo(":has", SQCssHasPseudoClass.class);
    }

}