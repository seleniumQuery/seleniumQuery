package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.parser.parsetree.condition.attribute.SQCssStartsWithAttributeCondition;
import org.junit.Test;

import static io.github.seleniumquery.by.parser.translator.condition.attribute.TranslatorsTestUtils.parseFirstCssCondition;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssStartsWithAttributeConditionTranslatorTest {

    @Test
    public void translate() {
        // given
        String selector = "[abc^=\"def\"]";
        // when
        SQCssStartsWithAttributeCondition cssCondition = parseFirstCssCondition(selector, SQCssStartsWithAttributeCondition.class);
        // then
        assertThat(cssCondition.getAttributeName(), is("abc"));
        assertThat(cssCondition.getWantedValue(), is("def"));
    }

}