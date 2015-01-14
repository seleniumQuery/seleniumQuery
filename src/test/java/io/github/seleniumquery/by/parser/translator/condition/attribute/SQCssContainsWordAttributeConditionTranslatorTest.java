package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.parser.parsetree.condition.attribute.SQCssContainsWordAttributeCondition;
import org.junit.Test;

import static io.github.seleniumquery.by.parser.translator.condition.attribute.TranslatorsTestUtils.parseFirstCssCondition;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssContainsWordAttributeConditionTranslatorTest {

    @Test
    public void translate() {
        // given
        String selector = "[values~=\"10\"]";
        // when
        SQCssContainsWordAttributeCondition cssCondition = parseFirstCssCondition(selector, SQCssContainsWordAttributeCondition.class);
        // then
        assertThat(cssCondition.getAttributeName(), is("values"));
        assertThat(cssCondition.getWantedValue(), is("10"));
    }

}