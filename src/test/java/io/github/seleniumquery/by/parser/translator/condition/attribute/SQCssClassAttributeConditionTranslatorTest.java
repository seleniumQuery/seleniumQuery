package io.github.seleniumquery.by.parser.translator.condition.attribute;

import io.github.seleniumquery.by.parser.SQParseTreeBuilder;
import io.github.seleniumquery.by.parser.parsetree.condition.SQCssCondition;
import io.github.seleniumquery.by.parser.parsetree.condition.attribute.SQCssClassAttributeCondition;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssConditionalSelector;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssSelector;
import io.github.seleniumquery.by.parser.parsetree.selector.SQCssTagNameSelector;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssClassAttributeConditionTranslatorTest {

    @Test
    public void translate__should_translate_regular_classes() {
        assertSelectorIsCompiledToClassSelector(".abc", "abc");
    }

    @Test
    public void translate__should_translate_escaped_classes() {
        assertSelectorIsCompiledToClassSelector(".x\\+y", "x+y");
        assertSelectorIsCompiledToClassSelector(".x\\2b y", "x+y");
        assertSelectorIsCompiledToClassSelector(".x\\00002by", "x+y");
        assertSelectorIsCompiledToClassSelector(".\\0000E9fg", "éfg");
        assertSelectorIsCompiledToClassSelector(".\\3A \\`\\(", ":`(");
        assertSelectorIsCompiledToClassSelector(".\\31 a2b3c", "1a2b3c");

        assertSelectorIsCompiledToClassSelector(".\\\"", "\"");
        assertSelectorIsCompiledToClassSelector(".♥", "♥");
        assertSelectorIsCompiledToClassSelector(".©", "©");
//        assertSelectorIsCompiledToClassSelector(".“‘’”", "“‘’”");
//        assertSelectorIsCompiledToClassSelector(".☺☃", "☺☃");
//        assertSelectorIsCompiledToClassSelector(".⌘⌥", "⌘⌥");
//        assertSelectorIsCompiledToClassSelector(".𝄞♪♩♫♬", "𝄞♪♩♫♬");
//        assertSelectorIsCompiledToClassSelector(".💩", "💩");
        assertSelectorIsCompiledToClassSelector(".\\?", "?");
        assertSelectorIsCompiledToClassSelector(".\\@", "@");
        assertSelectorIsCompiledToClassSelector(".\\.", ".");
        assertSelectorIsCompiledToClassSelector(".\\3A \\)", ":)");
        assertSelectorIsCompiledToClassSelector(".\\3A \\`\\(", ":`(");
        assertSelectorIsCompiledToClassSelector(".\\31 23", "123");
        assertSelectorIsCompiledToClassSelector(".\\31 a2b3c", "1a2b3c");
        assertSelectorIsCompiledToClassSelector(".\\<p\\>", "<p>");
        assertSelectorIsCompiledToClassSelector(".\\<\\>\\<\\<\\<\\>\\>\\<\\>", "<><<<>><>");
        assertSelectorIsCompiledToClassSelector(".\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\[\\>\\+\\+\\+\\+\\+\\+\\+\\>\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\>\\+\\+\\+\\>\\+\\<\\<\\<\\<\\-\\]\\>\\+\\+\\.\\>\\+\\.\\+\\+\\+\\+\\+\\+\\+\\.\\.\\+\\+\\+\\.\\>\\+\\+\\.\\<\\<\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\.\\>\\.\\+\\+\\+\\.\\-\\-\\-\\-\\-\\-\\.\\-\\-\\-\\-\\-\\-\\-\\-\\.\\>\\+\\.\\>\\.", "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.");
        assertSelectorIsCompiledToClassSelector(".\\#", "#");
        assertSelectorIsCompiledToClassSelector(".\\#\\#", "##");
        assertSelectorIsCompiledToClassSelector(".\\#\\.\\#\\.\\#", "#.#.#");
        assertSelectorIsCompiledToClassSelector(".\\_", "_");
        assertSelectorIsCompiledToClassSelector(".\\{\\}", "{}");
        assertSelectorIsCompiledToClassSelector(".\\#fake\\-id", "#fake-id");
        assertSelectorIsCompiledToClassSelector(".foo\\.bar", "foo.bar");
        assertSelectorIsCompiledToClassSelector(".\\3A hover", ":hover");
        assertSelectorIsCompiledToClassSelector(".\\3A hover\\3A focus\\3A active", ":hover:focus:active");
        assertSelectorIsCompiledToClassSelector(".\\[attr\\=value\\]", "[attr=value]");
        assertSelectorIsCompiledToClassSelector(".f\\/o\\/o", "f/o/o");
        assertSelectorIsCompiledToClassSelector(".f\\\\o\\\\o", "f\\o\\o");
        assertSelectorIsCompiledToClassSelector(".f\\*o\\*o", "f*o*o");
        assertSelectorIsCompiledToClassSelector(".f\\!o\\!o", "f!o!o");
        assertSelectorIsCompiledToClassSelector(".f\\'o\\'o", "f'o'o");
        assertSelectorIsCompiledToClassSelector(".f\\~o\\~o", "f~o~o");
        assertSelectorIsCompiledToClassSelector(".f\\+o\\+o", "f+o+o");
    }

    private void assertSelectorIsCompiledToClassSelector(String actualSelector, String expectedClassName) {
        // given
        SQCssSelector cssSelector = SQParseTreeBuilder.parse(actualSelector).firstSelector();
        assertThat(cssSelector, instanceOf(SQCssConditionalSelector.class));
        // when
        SQCssSelector sqCssSelector = ((SQCssConditionalSelector) cssSelector).getSqCssSelector();
        SQCssCondition sqCssCondition = ((SQCssConditionalSelector) cssSelector).getSqCssCondition();
        // then
        assertThat(sqCssSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(sqCssCondition, instanceOf(SQCssClassAttributeCondition.class));
        assertThat(((SQCssTagNameSelector) sqCssSelector).getTagName(), is("*"));
        assertThat(((SQCssClassAttributeCondition) sqCssCondition).getClassName(), is(expectedClassName));
    }

}