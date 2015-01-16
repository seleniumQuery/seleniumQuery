package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.seleniumquery;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;

public class SQCssPresentPseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":present", SQCssPresentPseudoClass.class);
    }

}