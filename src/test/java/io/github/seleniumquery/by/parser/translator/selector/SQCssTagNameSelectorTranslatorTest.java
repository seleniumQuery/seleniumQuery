package io.github.seleniumquery.by.parser.translator.selector;

import io.github.seleniumquery.by.csstree.selector.SQCssTagNameSelector;
import io.github.seleniumquery.by.parser.SQParseTreeBuilder;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssSelector;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssTagNameSelectorTranslatorTest {

    @Test
    public void translate() {
        // given
        // when
        SQCssSelector cssSelector = SQParseTreeBuilder.parse("tag").firstSelector();
        // then
        assertThat(cssSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) cssSelector).getTagName(), is("tag"));
    }

}