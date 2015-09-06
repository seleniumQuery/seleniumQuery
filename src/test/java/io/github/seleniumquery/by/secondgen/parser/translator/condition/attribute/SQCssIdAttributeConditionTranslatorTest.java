/*
 * Copyright (c) 2015 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.seleniumquery.by.secondgen.parser.translator.condition.attribute;

import io.github.seleniumquery.by.secondgen.csstree.condition.SQCssCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.SQCssIdAttributeCondition;
import io.github.seleniumquery.by.secondgen.csstree.selector.SQCssConditionalSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.SQCssSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.SQCssTagNameSelector;
import io.github.seleniumquery.by.secondgen.parser.SQParseTreeBuilder;
import org.junit.Test;

import static io.github.seleniumquery.by.secondgen.parser.translator.condition.attribute.TranslatorsTestUtils.parseAndAssertFirstCssCondition;
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
        SQCssIdAttributeCondition cssCondition = parseAndAssertFirstCssCondition(actualSelector, SQCssIdAttributeCondition.class);
        // then
        assertThat(cssCondition.getId(), is(expectedId));
    }

}