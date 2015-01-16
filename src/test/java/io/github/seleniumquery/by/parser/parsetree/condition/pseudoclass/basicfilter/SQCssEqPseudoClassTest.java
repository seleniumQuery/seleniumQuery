package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.basicfilter;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertFunctionalPseudo;

public class SQCssEqPseudoClassTest {

    @Test
    public void translate() {
        assertFunctionalPseudo(":eq", SQCssEqPseudoClass.class);
    }

}