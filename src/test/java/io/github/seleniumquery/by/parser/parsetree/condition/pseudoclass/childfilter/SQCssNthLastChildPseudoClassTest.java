package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.childfilter;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertFunctionalPseudo;

public class SQCssNthLastChildPseudoClassTest {

    @Test
    public void translate() {
        assertFunctionalPseudo(":nth-last-child", SQCssNthLastChildPseudoClass.class);
    }

}