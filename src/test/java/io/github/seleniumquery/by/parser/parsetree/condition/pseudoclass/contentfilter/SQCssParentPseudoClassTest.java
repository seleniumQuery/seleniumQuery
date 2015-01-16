package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.contentfilter;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;

public class SQCssParentPseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":parent", SQCssParentPseudoClass.class);
    }

}