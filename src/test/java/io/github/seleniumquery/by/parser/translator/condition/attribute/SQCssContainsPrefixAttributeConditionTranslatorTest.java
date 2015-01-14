package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.parser.parsetree.condition.attribute.SQCssContainsPrefixAttributeCondition;
import org.junit.Test;

import static io.github.seleniumquery.by.parser.translator.condition.attribute.TranslatorsTestUtils.parseFirstCssCondition;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssContainsPrefixAttributeConditionTranslatorTest {

    @Test
    public void translate() {
        // given
        String selector = "[languages|=\"pt\"]";
        // when
        SQCssContainsPrefixAttributeCondition cssCondition = parseFirstCssCondition(selector, SQCssContainsPrefixAttributeCondition.class);
        // then
        assertThat(cssCondition.getAttributeName(), is("languages"));
        assertThat(cssCondition.getWantedValue(), is("pt"));
    }

}