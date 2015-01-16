package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.childfilter;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertFunctionalPseudo;

public class SQCssNthOfTypePseudoClassTest {

    @Test
    public void translate() {
        assertFunctionalPseudo(":nth-of-type", SQCssNthOfTypePseudoClass.class);
    }

}