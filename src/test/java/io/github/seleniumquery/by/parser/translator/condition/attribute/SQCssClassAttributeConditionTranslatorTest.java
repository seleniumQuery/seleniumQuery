package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.parser.parsetree.condition.attribute.SQCssClassAttributeCondition;
import org.junit.Test;

import static io.github.seleniumquery.by.parser.translator.condition.attribute.TranslatorsTestUtils.parseFirstCssCondition;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssClassAttributeConditionTranslatorTest {

    @Test
    public void translate__should_translate_regular_classes() {
        assertSelectorIsCompiledToClassName(".abc", "abc");
    }

    @Test
    public void translate__should_translate_escaped_classes() {
        assertSelectorIsCompiledToClassName(".x\\+y", "x+y");
        assertSelectorIsCompiledToClassName(".x\\2b y", "x+y");
        assertSelectorIsCompiledToClassName(".x\\00002by", "x+y");
        assertSelectorIsCompiledToClassName(".\\0000E9fg", "éfg");
        assertSelectorIsCompiledToClassName(".\\3A \\`\\(", ":`(");
        assertSelectorIsCompiledToClassName(".\\31 a2b3c", "1a2b3c");

        assertSelectorIsCompiledToClassName(".\\\"a\\\"b\\\"c\\\"", "\"a\"b\"c\"");
        assertSelectorIsCompiledToClassName(".\\\"", "\"");
        assertSelectorIsCompiledToClassName(".♥", "♥");
        assertSelectorIsCompiledToClassName(".©", "©");
//        assertSelectorIsCompiledToClassName(".“‘’”", "“‘’”");
//        assertSelectorIsCompiledToClassName(".☺☃", "☺☃");
//        assertSelectorIsCompiledToClassName(".⌘⌥", "⌘⌥");
//        assertSelectorIsCompiledToClassName(".𝄞♪♩♫♬", "𝄞♪♩♫♬");
//        assertSelectorIsCompiledToClassName(".💩", "💩");
        assertSelectorIsCompiledToClassName(".\\?", "?");
        assertSelectorIsCompiledToClassName(".\\@", "@");
        assertSelectorIsCompiledToClassName(".\\.", ".");
        assertSelectorIsCompiledToClassName(".\\3A \\)", ":)");
        assertSelectorIsCompiledToClassName(".\\3A \\`\\(", ":`(");
        assertSelectorIsCompiledToClassName(".\\31 23", "123");
        assertSelectorIsCompiledToClassName(".\\31 a2b3c", "1a2b3c");
        assertSelectorIsCompiledToClassName(".\\<p\\>", "<p>");
        assertSelectorIsCompiledToClassName(".\\<\\>\\<\\<\\<\\>\\>\\<\\>", "<><<<>><>");
        assertSelectorIsCompiledToClassName(".\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\[\\>\\+\\+\\+\\+\\+\\+\\+\\>\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\>\\+\\+\\+\\>\\+\\<\\<\\<\\<\\-\\]\\>\\+\\+\\.\\>\\+\\.\\+\\+\\+\\+\\+\\+\\+\\.\\.\\+\\+\\+\\.\\>\\+\\+\\.\\<\\<\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\.\\>\\.\\+\\+\\+\\.\\-\\-\\-\\-\\-\\-\\.\\-\\-\\-\\-\\-\\-\\-\\-\\.\\>\\+\\.\\>\\.", "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.");
        assertSelectorIsCompiledToClassName(".\\#", "#");
        assertSelectorIsCompiledToClassName(".\\#\\#", "##");
        assertSelectorIsCompiledToClassName(".\\#\\.\\#\\.\\#", "#.#.#");
        assertSelectorIsCompiledToClassName(".\\_", "_");
        assertSelectorIsCompiledToClassName(".\\{\\}", "{}");
        assertSelectorIsCompiledToClassName(".\\#fake\\-id", "#fake-id");
        assertSelectorIsCompiledToClassName(".foo\\.bar", "foo.bar");
        assertSelectorIsCompiledToClassName(".\\3A hover", ":hover");
        assertSelectorIsCompiledToClassName(".\\3A hover\\3A focus\\3A active", ":hover:focus:active");
        assertSelectorIsCompiledToClassName(".\\[attr\\=value\\]", "[attr=value]");
        assertSelectorIsCompiledToClassName(".f\\/o\\/o", "f/o/o");
        assertSelectorIsCompiledToClassName(".f\\\\o\\\\o", "f\\o\\o");
        assertSelectorIsCompiledToClassName(".f\\*o\\*o", "f*o*o");
        assertSelectorIsCompiledToClassName(".f\\!o\\!o", "f!o!o");
        assertSelectorIsCompiledToClassName(".f\\'o\\'o", "f'o'o");
        assertSelectorIsCompiledToClassName(".f\\~o\\~o", "f~o~o");
        assertSelectorIsCompiledToClassName(".f\\+o\\+o", "f+o+o");
    }

    private void assertSelectorIsCompiledToClassName(String actualSelector, String expectedClassName) {
        // given
        // selector arg
        // when
        SQCssClassAttributeCondition cssCondition = parseFirstCssCondition(actualSelector, SQCssClassAttributeCondition.class);
        // then
        assertThat(cssCondition.getClassName(), is(expectedClassName));
    }

}