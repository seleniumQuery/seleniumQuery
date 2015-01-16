package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.seleniumquery;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;

public class SQCssBlankPseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":blank", SQCssBlankPseudoClass.class);
    }

}