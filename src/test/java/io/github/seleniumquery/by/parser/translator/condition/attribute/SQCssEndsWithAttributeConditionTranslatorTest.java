package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.parser.SQParseTreeBuilder;
import io.github.seleniumquery.by.parser.parsetree.condition.SQCssCondition;
import io.github.seleniumquery.by.parser.parsetree.condition.attribute.SQCssEndsWithAttributeCondition;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssConditionalSelector;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssSelector;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssTagNameSelector;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssEndsWithAttributeConditionTranslatorTest {

    @Test
    public void translate() throws Exception {
        // given
        SQCssSelector cssSelector = SQParseTreeBuilder.parse("[attr$=\"end\"]").firstSelector();
        assertThat(cssSelector, instanceOf(SQCssConditionalSelector.class));
        // when
        SQCssSelector sqCssSelector = ((SQCssConditionalSelector) cssSelector).getSqCssSelector();
        SQCssCondition sqCssCondition = ((SQCssConditionalSelector) cssSelector).getSqCssCondition();
        // then
        assertThat(sqCssSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(sqCssCondition, instanceOf(SQCssEndsWithAttributeCondition.class));
        assertThat(((SQCssTagNameSelector) sqCssSelector).getTagName(), is("*"));
        assertThat(((SQCssEndsWithAttributeCondition) sqCssCondition).getAttributeName(), is("attr"));
        assertThat(((SQCssEndsWithAttributeCondition) sqCssCondition).getWantedValue(), is("end"));
    }

}