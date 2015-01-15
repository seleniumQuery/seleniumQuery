package io.github.seleniumquery.by.parser.parsetree.condition.pseudoclass.form;

import org.junit.Test;

import static io.github.seleniumquery.by.parser.translator.condition.attribute.TranslatorsTestUtils.parseFirstCssCondition;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class SQCssCheckedPseudoClassTest {

    @Test
    public void translate() {
        // given
        String selector = ":checked";
        // when
        SQCssCheckedPseudoClass cssCondition = parseFirstCssCondition(selector, SQCssCheckedPseudoClass.class);
        // then
        assertThat(cssCondition, is(notNullValue()));
    }

}