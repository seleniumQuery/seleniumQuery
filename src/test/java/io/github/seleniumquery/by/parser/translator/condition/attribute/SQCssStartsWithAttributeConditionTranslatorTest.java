package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.parser.SQParseTreeBuilder;
import io.github.seleniumquery.by.parser.parsetree.condition.SQCssCondition;
import io.github.seleniumquery.by.parser.parsetree.condition.attribute.SQCssStartsWithAttributeCondition;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssConditionalSelector;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssSelector;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssTagNameSelector;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssStartsWithAttributeConditionTranslatorTest {

    @Test
    public void translate() throws Exception {
        // given
        SQCssSelector cssSelector = SQParseTreeBuilder.parse("[abc^=\"def\"]").firstSelector();
        assertThat(cssSelector, instanceOf(SQCssConditionalSelector.class));
        // when
        SQCssSelector sqCssSelector = ((SQCssConditionalSelector) cssSelector).getSqCssSelector();
        SQCssCondition sqCssCondition = ((SQCssConditionalSelector) cssSelector).getSqCssCondition();
        // then
        assertThat(sqCssSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(sqCssCondition, instanceOf(SQCssStartsWithAttributeCondition.class));
        assertThat(((SQCssTagNameSelector) sqCssSelector).getTagName(), is("*"));
        assertThat(((SQCssStartsWithAttributeCondition) sqCssCondition).getAttributeName(), is("abc"));
        assertThat(((SQCssStartsWithAttributeCondition) sqCssCondition).getWantedValue(), is("def"));
    }

}