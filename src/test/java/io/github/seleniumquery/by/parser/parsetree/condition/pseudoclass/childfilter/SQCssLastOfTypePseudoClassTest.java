package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.childfilter;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;

public class SQCssLastOfTypePseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":last-of-type", SQCssLastOfTypePseudoClass.class);
    }

}