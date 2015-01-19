package io.github.seleniumquery.by.parser.translator.selector;

import io.github.seleniumquery.by.csstree.condition.SQCssAndCondition;
import io.github.seleniumquery.by.csstree.condition.attribute.SQCssClassAttributeCondition;
import io.github.seleniumquery.by.csstree.selector.SQCssConditionalSelector;
import io.github.seleniumquery.by.csstree.selector.SQCssTagNameSelector;
import io.github.seleniumquery.by.parser.SQParseTreeBuilder;
import io.github.seleniumquery.by.parser.parsetree.condition.SQCssCondition;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssSelector;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssConditionalSelectorTranslatorTest {

    @Test
    public void translate__simple_condition() {
        // given
        String simpleConditionSelector = "a.condition";
        // when
        SQCssSelector cssSelector = SQParseTreeBuilder.parse(simpleConditionSelector).firstSelector();
        // then
        assertThat(cssSelector, instanceOf(SQCssConditionalSelector.class));
        SQCssSelector sqCssSelector = ((SQCssConditionalSelector) cssSelector).getSqCssSelector();
        SQCssCondition sqCssCondition = ((SQCssConditionalSelector) cssSelector).getSqCssCondition();

        assertThat(sqCssSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) sqCssSelector).getTagName(), is("a"));

        assertThat(sqCssCondition, instanceOf(SQCssClassAttributeCondition.class));
        assertThat(((SQCssClassAttributeCondition) sqCssCondition).getClassName(), is("condition"));
    }

    @Test
    public void translate__compound_condition() {
        // given
        String compoundConditionSelector = "a.conditionA.conditionB";
        // when
        SQCssSelector cssSelector = SQParseTreeBuilder.parse(compoundConditionSelector).firstSelector();
        // then
        assertThat(cssSelector, instanceOf(SQCssConditionalSelector.class));

        SQCssSelector sqCssSelector = ((SQCssConditionalSelector) cssSelector).getSqCssSelector();
        SQCssCondition sqCssCondition = ((SQCssConditionalSelector) cssSelector).getSqCssCondition();

        assertThat(sqCssSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) sqCssSelector).getTagName(), is("a"));

        assertThat(sqCssCondition, instanceOf(SQCssAndCondition.class));
        SQCssCondition sqCssFirstCondition = ((SQCssAndCondition) sqCssCondition).getFirstCondition();
        SQCssCondition sqCssSecondCondition = ((SQCssAndCondition) sqCssCondition).getSecondCondition();

        assertThat(sqCssFirstCondition, instanceOf(SQCssClassAttributeCondition.class));
        assertThat(((SQCssClassAttributeCondition) sqCssFirstCondition).getClassName(), is("conditionA"));

        assertThat(sqCssSecondCondition, instanceOf(SQCssClassAttributeCondition.class));
        assertThat(((SQCssClassAttributeCondition) sqCssSecondCondition).getClassName(), is("conditionB"));
    }

}