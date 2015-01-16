package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.childfilter;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;

public class SQCssLastChildPseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":last-child", SQCssLastChildPseudoClass.class);
    }

}