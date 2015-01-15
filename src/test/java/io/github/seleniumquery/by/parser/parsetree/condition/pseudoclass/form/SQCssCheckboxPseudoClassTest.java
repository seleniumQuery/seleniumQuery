package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.form;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.form.PseudoClassTestUtils.assertPseudo;

public class SQCssCheckboxPseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":checkbox", SQCssCheckboxPseudoClass.class);
    }

}