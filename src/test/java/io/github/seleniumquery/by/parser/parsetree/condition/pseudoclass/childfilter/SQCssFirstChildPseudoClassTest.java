package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.childfilter;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;

public class SQCssFirstChildPseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":first-child", SQCssFirstChildPseudoClass.class);
    }

}