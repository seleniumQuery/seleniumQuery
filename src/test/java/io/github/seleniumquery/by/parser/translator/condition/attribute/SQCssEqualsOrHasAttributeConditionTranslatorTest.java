package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.parser.parsetree.condition.attribute.SQCssEqualsOrHasAttributeCondition;
import org.junit.Test;

import static io.github.seleniumquery.by.parser.translator.condition.attribute.TranslatorsTestUtils.parseFirstCssCondition;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class SQCssEqualsOrHasAttributeConditionTranslatorTest {

    @Test
    public void translate__should_translate_hasAttribute_selector() {
        // given
        String selector = "[hasAttribute]";
        // when
        SQCssEqualsOrHasAttributeCondition cssCondition = parseFirstCssCondition(selector, SQCssEqualsOrHasAttributeCondition.class);
        // then
        assertThat(cssCondition.getAttributeName(), is("hasAttribute"));
        assertThat(cssCondition.getWantedValue(), is(nullValue()));
    }

    @Test
    public void translate__should_translate_attributeEquals_selector() {
        // given
        String selector = "[attrib=equals]";
        // when
        SQCssEqualsOrHasAttributeCondition cssCondition = parseFirstCssCondition(selector, SQCssEqualsOrHasAttributeCondition.class);
        // then
        assertThat(cssCondition.getAttributeName(), is("attrib"));
        assertThat(cssCondition.getWantedValue(), is("equals"));
    }

}