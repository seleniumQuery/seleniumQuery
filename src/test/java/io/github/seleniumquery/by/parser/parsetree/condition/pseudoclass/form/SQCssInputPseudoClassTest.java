package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.form;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;

public class SQCssInputPseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":input", SQCssInputPseudoClass.class);
    }

}