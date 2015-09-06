package io.github.seleniumquery.by2.parser.translator.condition.attribute;

import io.github.seleniumquery.by.csstree.condition.attribute.SQCssStartsWithAttributeCondition;
import org.junit.Test;

import static io.github.seleniumquery.by2.parser.translator.condition.attribute.TranslatorsTestUtils.parseAndAssertFirstCssCondition;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssStartsWithAttributeConditionTranslatorTest {

    @Test
    public void translate() {
        // given
        String selector = "[abc^=\"def\"]";
        // when
        SQCssStartsWithAttributeCondition cssCondition = parseAndAssertFirstCssCondition(selector, SQCssStartsWithAttributeCondition.class);
        // then
        assertThat(cssCondition.getAttributeName(), is("abc"));
        assertThat(cssCondition.getWantedValue(), is("def"));
    }

}