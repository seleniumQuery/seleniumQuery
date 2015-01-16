package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.childfilter;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertFunctionalPseudo;

public class SQCssNthLastOfTypePseudoClassTest {

    @Test
    public void translate() {
        assertFunctionalPseudo(":nth-last-of-type", SQCssNthLastOfTypePseudoClass.class);
    }

}