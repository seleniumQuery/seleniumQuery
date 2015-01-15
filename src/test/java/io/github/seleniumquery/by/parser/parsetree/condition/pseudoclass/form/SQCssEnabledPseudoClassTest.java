package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.form;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.translator.condition.attribute.TranslatorsTestUtils.parseFirstCssCondition;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class SQCssEnabledPseudoClassTest {

    @Test
    public void translate() {
        // given
        String selector = ":enabled";
        // when
        SQCssEnabledPseudoClass cssCondition = parseFirstCssCondition(selector, SQCssEnabledPseudoClass.class);
        // then
        assertThat(cssCondition, is(notNullValue()));
    }

}