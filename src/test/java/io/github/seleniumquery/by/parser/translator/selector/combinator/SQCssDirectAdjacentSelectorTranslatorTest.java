package io.github.seleniumquery.by.parser.translator.selector.combinator;

import io.github.seleniumquery.by.parser.SQParseTreeBuilder;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssSelector;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssTagNameSelector;
import io.github.seleniumquery.by.parser.parsetree.selector.combinator.SQCssDirectAdjacentSelector;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssDirectAdjacentSelectorTranslatorTest {

    @Test
    public void translate() {
        // given
        SQCssSelector cssSelector = SQParseTreeBuilder.parse("a + b").firstSelector();
        assertThat(cssSelector, instanceOf(SQCssDirectAdjacentSelector.class));
        // when
        SQCssSelector ancestorSelector = ((SQCssDirectAdjacentSelector) cssSelector).getAncestorSelector();
        SQCssSelector descendantSelector = ((SQCssDirectAdjacentSelector) cssSelector).getDescendantSelector();
        // then
        assertThat(ancestorSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) ancestorSelector).getTagName(), is("a"));

        assertThat(descendantSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) descendantSelector).getTagName(), is("b"));
    }

}