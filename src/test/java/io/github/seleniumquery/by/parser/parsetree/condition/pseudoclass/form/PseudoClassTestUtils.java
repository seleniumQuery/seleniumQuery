package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.form;

import io.github.seleniumquery.by.parser.parsetree.condition.SQCssCondition;

import static io.github.seleniumquery.by.parser.translator.condition.attribute.TranslatorsTestUtils.parseFirstCssCondition;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class PseudoClassTestUtils {

    public static <T extends SQCssCondition> void assertPseudo(String selector, Class<T> pseudoClassClass) {
        // given
        // selector
        // when
        T cssCondition = parseFirstCssCondition(selector, pseudoClassClass);
        // then
        assertThat(cssCondition, is(notNullValue()));
    }

}