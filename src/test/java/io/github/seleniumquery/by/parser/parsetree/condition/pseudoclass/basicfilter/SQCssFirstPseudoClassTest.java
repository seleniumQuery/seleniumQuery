package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.basicfilter;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.form.PseudoClassTestUtils.assertPseudo;

public class SQCssFirstPseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":first", SQCssFirstPseudoClass.class);
    }

}