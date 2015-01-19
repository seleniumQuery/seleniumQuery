package io.github.seleniumquery.by.parser.translator.selector.combinator;

import io.github.seleniumquery.by.parser.SQParseTreeBuilder;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssSelector;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssTagNameSelector;
import io.github.seleniumquery.by.parser.parsetree.selector.combinator.SQCssGeneralAdjacentSelector;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssGeneralAdjacentSelectorTranslatorTest {

    @Test
    public void translate() {
        // given
        SQCssSelector cssSelector = SQParseTreeBuilder.parse("a ~ b").firstSelector();
        assertThat(cssSelector, instanceOf(SQCssGeneralAdjacentSelector.class));
        // when
        SQCssSelector ancestorSelector = ((SQCssGeneralAdjacentSelector) cssSelector).getAncestorSelector();
        SQCssSelector descendantSelector = ((SQCssGeneralAdjacentSelector) cssSelector).getDescendantSelector();
        // then
        assertThat(ancestorSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) ancestorSelector).getTagName(), is("a"));

        assertThat(descendantSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) descendantSelector).getTagName(), is("b"));
    }

}