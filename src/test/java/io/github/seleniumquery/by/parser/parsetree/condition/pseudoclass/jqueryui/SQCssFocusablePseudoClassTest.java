package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.jqueryui;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;

public class SQCssFocusablePseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":focusable", SQCssFocusablePseudoClass.class);
    }

}