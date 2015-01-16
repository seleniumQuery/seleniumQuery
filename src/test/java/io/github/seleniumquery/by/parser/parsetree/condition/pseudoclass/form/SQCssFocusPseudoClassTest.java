package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.form;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;

public class SQCssFocusPseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":focus", SQCssFocusPseudoClass.class);
    }

}