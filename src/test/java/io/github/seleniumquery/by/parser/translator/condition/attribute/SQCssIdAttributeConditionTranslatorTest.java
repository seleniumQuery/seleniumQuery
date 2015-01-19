package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.csstree.condition.attribute.SQCssIdAttributeCondition;
import io.github.seleniumquery.by.csstree.selector.SQCssConditionalSelector;
import io.github.seleniumquery.by.csstree.selector.SQCssTagNameSelector;
import io.github.seleniumquery.by.parser.SQParseTreeBuilder;
import io.github.seleniumquery.by.parser.parsetree.condition.SQCssCondition;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssSelector;
import org.junit.Test;

import static io.github.seleniumquery.by.parser.translator.condition.attribute.TranslatorsTestUtils.parseFirstCssCondition;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssIdAttributeConditionTranslatorTest {

    @Test
    public void translate() throws Exception {
        // given
        SQCssSelector cssSelector = SQParseTreeBuilder.parse("#ball").firstSelector();
        assertThat(cssSelector, instanceOf(SQCssConditionalSelector.class));
        // when
        SQCssSelector sqCssSelector = ((SQCssConditionalSelector) cssSelector).getSqCssSelector();
        SQCssCondition sqCssCondition = ((SQCssConditionalSelector) cssSelector).getSqCssCondition();
        // then
        assertThat(sqCssSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) sqCssSelector).getTagName(), is("*"));

        assertThat(sqCssCondition, instanceOf(SQCssIdAttributeCondition.class));
        assertThat(((SQCssIdAttributeCondition) sqCssCondition).getId(), is("ball"));
    }

    @Test
    public void translate__should_translate_regular_ids() {
        assertSelectorIsCompiledToId("#abc", "abc");
    }

    @Test
    public void translate__should_translate_escaped_ids() {
        assertSelectorIsCompiledToId("#x\\+y", "x+y");
        assertSelectorIsCompiledToId("#x\\2b y", "x+y");
        assertSelectorIsCompiledToId("#x\\00002by", "x+y");
//        assertSelectorIsCompiledToClassSelector("#\\0000E9fg", "éfg"); // I guess this is wrong anyway
        assertSelectorIsCompiledToId("#\\E9 fg", "éfg");
        assertSelectorIsCompiledToId("#\\3A \\`\\(", ":`(");
        assertSelectorIsCompiledToId("#\\31 a2b3c", "1a2b3c");

        assertSelectorIsCompiledToId("#\\\"", "\"");
        assertSelectorIsCompiledToId("#\\\"a\\\"b\\\"c\\\"", "\"a\"b\"c\"");
        assertSelectorIsCompiledToId("#♥", "♥");
        assertSelectorIsCompiledToId("#©", "©");
//        assertSelectorIsCompiledToId("#“‘’”", "“‘’”");
//        assertSelectorIsCompiledToId("#☺☃", "☺☃");
//        assertSelectorIsCompiledToId("#⌘⌥", "⌘⌥");
//        assertSelectorIsCompiledToId("#𝄞♪♩♫♬", "𝄞♪♩♫♬");
//        assertSelectorIsCompiledToId("#💩", "💩");
        assertSelectorIsCompiledToId("#\\?", "?");
        assertSelectorIsCompiledToId("#\\@", "@");
        assertSelectorIsCompiledToId("#\\.", ".");
        assertSelectorIsCompiledToId("#\\3A \\)", ":)");
        assertSelectorIsCompiledToId("#\\3A \\`\\(", ":`(");
        assertSelectorIsCompiledToId("#\\31 23", "123");
        assertSelectorIsCompiledToId("#\\31 a2b3c", "1a2b3c");
        assertSelectorIsCompiledToId("#\\<p\\>", "<p>");
        assertSelectorIsCompiledToId("#\\<\\>\\<\\<\\<\\>\\>\\<\\>", "<><<<>><>");
        assertSelectorIsCompiledToId("#\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\[\\>\\+\\+\\+\\+\\+\\+\\+\\>\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\>\\+\\+\\+\\>\\+\\<\\<\\<\\<\\-\\]\\>\\+\\+\\.\\>\\+\\.\\+\\+\\+\\+\\+\\+\\+\\.\\.\\+\\+\\+\\.\\>\\+\\+\\.\\<\\<\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\.\\>\\.\\+\\+\\+\\.\\-\\-\\-\\-\\-\\-\\.\\-\\-\\-\\-\\-\\-\\-\\-\\.\\>\\+\\.\\>\\.", "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.");
        assertSelectorIsCompiledToId("#\\#", "#");
        assertSelectorIsCompiledToId("#\\#\\#", "##");
        assertSelectorIsCompiledToId("#\\#\\.\\#\\.\\#", "#.#.#");
        assertSelectorIsCompiledToId("#\\_", "_");
        assertSelectorIsCompiledToId("#\\{\\}", "{}");
        assertSelectorIsCompiledToId("#\\#fake\\-id", "#fake-id");
        assertSelectorIsCompiledToId("#foo\\.bar", "foo.bar");
        assertSelectorIsCompiledToId("#\\3A hover", ":hover");
        assertSelectorIsCompiledToId("#\\3A hover\\3A focus\\3A active", ":hover:focus:active");
        assertSelectorIsCompiledToId("#\\[attr\\=value\\]", "[attr=value]");
        assertSelectorIsCompiledToId("#f\\/o\\/o", "f/o/o");
        assertSelectorIsCompiledToId("#f\\\\o\\\\o", "f\\o\\o");
        assertSelectorIsCompiledToId("#f\\*o\\*o", "f*o*o");
        assertSelectorIsCompiledToId("#f\\!o\\!o", "f!o!o");
        assertSelectorIsCompiledToId("#f\\'o\\'o", "f'o'o");
        assertSelectorIsCompiledToId("#f\\~o\\~o", "f~o~o");
        assertSelectorIsCompiledToId("#f\\+o\\+o", "f+o+o");
    }

    private void assertSelectorIsCompiledToId(String actualSelector, String expectedId) {
        // given
        // selector arg
        // when
        SQCssIdAttributeCondition cssCondition = parseFirstCssCondition(actualSelector, SQCssIdAttributeCondition.class);
        // then
        assertThat(cssCondition.getId(), is(expectedId));
    }

}