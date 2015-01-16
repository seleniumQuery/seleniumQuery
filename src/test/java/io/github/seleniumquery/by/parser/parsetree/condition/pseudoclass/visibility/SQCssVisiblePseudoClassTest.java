package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.visibility;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;

public class SQCssVisiblePseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":visible", SQCssVisiblePseudoClass.class);
    }

}