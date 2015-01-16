package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.seleniumquery;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;

public class SQCssUncheckedPseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":unchecked", SQCssUncheckedPseudoClass.class);
    }

}