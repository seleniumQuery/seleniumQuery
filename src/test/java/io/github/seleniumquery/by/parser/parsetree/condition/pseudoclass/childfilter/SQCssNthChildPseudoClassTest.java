package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.childfilter;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertFunctionalPseudo;

public class SQCssNthChildPseudoClassTest {

    @Test
    public void translate() {
        assertFunctionalPseudo(":nth-child", SQCssNthChildPseudoClass.class);
    }

}