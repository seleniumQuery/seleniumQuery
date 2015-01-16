package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.childfilter;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;

public class SQCssFirstOfTypePseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":first-of-type", SQCssFirstOfTypePseudoClass.class);
    }

}