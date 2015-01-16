package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.visibility;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;

public class SQCssHiddenPseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":hidden", SQCssHiddenPseudoClass.class);
    }

}