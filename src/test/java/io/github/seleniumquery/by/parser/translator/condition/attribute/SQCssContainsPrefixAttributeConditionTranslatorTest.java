package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.parser.SQParseTreeBuilder;
import io.github.seleniumquery.by.parser.parsetree.condition.SQCssCondition;
import io.github.seleniumquery.by.parser.parsetree.condition.attribute.SQCssContainsPrefixAttributeCondition;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssConditionalSelector;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssSelector;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssTagNameSelector;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssContainsPrefixAttributeConditionTranslatorTest {

    @Test
    public void translate() {
        // given
        SQCssSelector cssSelector = SQParseTreeBuilder.parse("[languages|=\"pt\"]").firstSelector();
        assertThat(cssSelector, instanceOf(SQCssConditionalSelector.class));
        // when
        SQCssSelector sqCssSelector = ((SQCssConditionalSelector) cssSelector).getSqCssSelector();
        SQCssCondition sqCssCondition = ((SQCssConditionalSelector) cssSelector).getSqCssCondition();
        // then
        assertThat(sqCssSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(sqCssCondition, instanceOf(SQCssContainsPrefixAttributeCondition.class));
        assertThat(((SQCssTagNameSelector) sqCssSelector).getTagName(), is("*"));
        assertThat(((SQCssContainsPrefixAttributeCondition) sqCssCondition).getAttributeName(), is("languages"));
        assertThat(((SQCssContainsPrefixAttributeCondition) sqCssCondition).getWantedValue(), is("pt"));
    }

}