package io.github.seleniumquery.by.parser.translator.selector.combinator;

import io.github.seleniumquery.by.csstree.selector.SQCssTagNameSelector;
import io.github.seleniumquery.by.csstree.selector.combinator.SQCssDirectDescendantSelector;
import io.github.seleniumquery.by.parser.SQParseTreeBuilder;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssSelector;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssDirectDescendantSelectorTranslatorTest {

    @Test
    public void translate() {
        // given
        SQCssSelector cssSelector = SQParseTreeBuilder.parse("a > b").firstSelector();
        assertThat(cssSelector, instanceOf(SQCssDirectDescendantSelector.class));
        // when
        SQCssSelector ancestorSelector = ((SQCssDirectDescendantSelector) cssSelector).getAncestorSelector();
        SQCssSelector descendantSelector = ((SQCssDirectDescendantSelector) cssSelector).getDescendantSelector();
        // then
        assertThat(ancestorSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) ancestorSelector).getTagName(), is("a"));

        assertThat(descendantSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) descendantSelector).getTagName(), is("b"));
    }

}