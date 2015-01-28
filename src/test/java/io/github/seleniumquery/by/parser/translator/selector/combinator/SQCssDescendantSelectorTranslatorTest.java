package io.github.seleniumquery.by.parser.translator.selector.combinator;

import io.github.seleniumquery.by.csstree.condition.SQCssCondition;
import io.github.seleniumquery.by.csstree.condition.attribute.SQCssClassAttributeCondition;
import io.github.seleniumquery.by.csstree.selector.SQCssConditionalSelector;
import io.github.seleniumquery.by.csstree.selector.SQCssSelector;
import io.github.seleniumquery.by.csstree.selector.SQCssTagNameSelector;
import io.github.seleniumquery.by.csstree.selector.combinator.SQCssDescendantSelector;
import io.github.seleniumquery.by.parser.SQParseTreeBuilder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssDescendantSelectorTranslatorTest {

    @Test
    public void translate() {
        // given
        SQCssSelector cssSelector = SQParseTreeBuilder.parse("a b").firstSelector();
        assertThat(cssSelector, instanceOf(SQCssDescendantSelector.class));
        // when
        SQCssSelector ancestorSelector = ((SQCssDescendantSelector) cssSelector).getAncestorSelector();
        SQCssSelector descendantSelector = ((SQCssDescendantSelector) cssSelector).getDescendantSelector();
        // then
        assertThat(ancestorSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) ancestorSelector).getTagName(), is("a"));

        assertThat(descendantSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) descendantSelector).getTagName(), is("b"));
    }

    @Test
    public void translate_with_condition() {
        // given
        SQCssSelector cssSelector = SQParseTreeBuilder.parse("a b.condition").firstSelector();
        assertThat(cssSelector, instanceOf(SQCssDescendantSelector.class));
        // when
        SQCssSelector ancestorSelector = ((SQCssDescendantSelector) cssSelector).getAncestorSelector();
        SQCssSelector descendantSelector = ((SQCssDescendantSelector) cssSelector).getDescendantSelector();
        // then
        assertThat(ancestorSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) ancestorSelector).getTagName(), is("a"));

        assertThat(descendantSelector, instanceOf(SQCssConditionalSelector.class));
        SQCssSelector sqCssSelector = ((SQCssConditionalSelector) descendantSelector).getSqCssSelector();
        SQCssCondition sqCssCondition = ((SQCssConditionalSelector) descendantSelector).getSqCssCondition();

        assertThat(sqCssSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) sqCssSelector).getTagName(), is("b"));

        assertThat(sqCssCondition, instanceOf(SQCssClassAttributeCondition.class));
        assertThat(((SQCssClassAttributeCondition) sqCssCondition).getClassName(), is("condition"));
    }

    /**
     * "a b c" becomes:
     *
     *     /\
     *    /\ \
     *   a  b c
     */
    @Test
    public void translate_with_another_descendant() {
        // given
        SQCssSelector cssSelector = SQParseTreeBuilder.parse("a b c").firstSelector();
        assertThat(cssSelector, instanceOf(SQCssDescendantSelector.class));
        // when
        SQCssSelector ancestorSelector = ((SQCssDescendantSelector) cssSelector).getAncestorSelector();
        SQCssSelector descendantSelector = ((SQCssDescendantSelector) cssSelector).getDescendantSelector();
        // then
        assertThat(ancestorSelector, instanceOf(SQCssDescendantSelector.class));
        SQCssSelector ancestorAncestorSelector = ((SQCssDescendantSelector) ancestorSelector).getAncestorSelector();
        SQCssSelector ancestorDescendantSelector = ((SQCssDescendantSelector) ancestorSelector).getDescendantSelector();

        assertThat(ancestorAncestorSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) ancestorAncestorSelector).getTagName(), is("a"));

        assertThat(ancestorAncestorSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) ancestorDescendantSelector).getTagName(), is("b"));

        assertThat(descendantSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) descendantSelector).getTagName(), is("c"));

    }

}