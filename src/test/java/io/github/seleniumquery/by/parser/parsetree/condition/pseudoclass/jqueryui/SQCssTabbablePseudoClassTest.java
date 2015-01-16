package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.jqueryui;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;

public class SQCssTabbablePseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":tabbable", SQCssTabbablePseudoClass.class);
    }

}