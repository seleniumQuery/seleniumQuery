package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.basicfilter;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;

public class SQCssEvenPseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":even", SQCssEvenPseudoClass.class);
    }

}