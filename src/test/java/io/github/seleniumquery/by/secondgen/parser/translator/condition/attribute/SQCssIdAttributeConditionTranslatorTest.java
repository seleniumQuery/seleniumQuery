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
import io.github.seleniumquery.by.secondgen.finder.ElementFinderUtilsTest;
import io.github.seleniumquery.by.secondgen.parser.SQParseTreeBuilder;
import org.junit.Test;

import static io.github.seleniumquery.by.secondgen.parser.translator.condition.attribute.TranslatorsTestUtils.parseAndAssertFirstCssCondition;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssIdAttributeConditionTranslatorTest {

    @Test
    public void translate() {
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
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#abc", "abc");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#a1b2c", "a1b2c");
    }

    @Test
    public void translate__should_translate_escaped_ids() {
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#x\\+y", "x+y");
        assertSelectorIsCompiledToId("#x\\2b y", "x+y");
        assertSelectorIsCompiledToId("#x\\00002by", "x+y");
//        assertSelectorIsCompiledToClassSelector("#\\0000E9fg", "éfg"); // I guess this is wrong anyway
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\E9 fg", "éfg");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\\"", "\"");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\\"a\\\"b\\\"c\\\"", "\"a\"b\"c\"");
        assertSelectorIsCompiledToId("#♥", "♥");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\2665", "♥");
        assertSelectorIsCompiledToId("#©", "©");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\A9", "©");
//        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#“‘’”", "“‘’”");
//        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#☺☃", "☺☃");
//        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#⌘⌥", "⌘⌥");
//        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#𝄞♪♩♫♬", "𝄞♪♩♫♬");
//        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#💩", "💩");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\?", "?");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\@", "@");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\.", ".");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\3A\\)", ":)");
        assertSelectorIsCompiledToId("#\\3A \\)", ":)");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\3A\\`\\(", ":`(");
        assertSelectorIsCompiledToId("#\\3A \\`\\(", ":`(");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\31 23", "123");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\31 a2b3c", "1a2b3c");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\<p\\>", "<p>");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\<\\>\\<\\<\\<\\>\\>\\<\\>", "<><<<>><>");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\[\\>\\+\\+\\+\\+\\+\\+\\+\\>\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\>\\+\\+\\+\\>\\+\\<\\<\\<\\<-\\]\\>\\+\\+\\.\\>\\+\\.\\+\\+\\+\\+\\+\\+\\+\\.\\.\\+\\+\\+\\.\\>\\+\\+\\.\\<\\<\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\.\\>\\.\\+\\+\\+\\.------\\.--------\\.\\>\\+\\.\\>\\.", "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.");
        assertSelectorIsCompiledToId("#\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\[\\>\\+\\+\\+\\+\\+\\+\\+\\>\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\>\\+\\+\\+\\>\\+\\<\\<\\<\\<\\-\\]\\>\\+\\+\\.\\>\\+\\.\\+\\+\\+\\+\\+\\+\\+\\.\\.\\+\\+\\+\\.\\>\\+\\+\\.\\<\\<\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\+\\.\\>\\.\\+\\+\\+\\.\\-\\-\\-\\-\\-\\-\\.\\-\\-\\-\\-\\-\\-\\-\\-\\.\\>\\+\\.\\>\\.", "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\#", "#");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\#\\#", "##");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\#\\.\\#\\.\\#", "#.#.#");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\_", "_");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\{\\}", "{}");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\#fake-id", "#fake-id");
        assertSelectorIsCompiledToId("#\\#fake\\-id", "#fake-id");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#foo\\.bar", "foo.bar");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\3Ahover", ":hover");
        assertSelectorIsCompiledToId("#\\3A hover", ":hover");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\3Ahover\\3A focus\\3A active", ":hover:focus:active");
        assertSelectorIsCompiledToId("#\\3A hover\\3A focus\\3A active", ":hover:focus:active");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#\\[attr\\=value\\]", "[attr=value]");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#f\\/o\\/o", "f/o/o");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#f\\\\o\\\\o", "f\\o\\o");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#f\\*o\\*o", "f*o*o");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#f\\!o\\!o", "f!o!o");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#f\\'o\\'o", "f'o'o");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#f\\~o\\~o", "f~o~o");
        assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt("#f\\+o\\+o", "f+o+o");
    }

    private void assertSelectorIsCompiledToIdAndThenTurnedBackIntoIt(String actualSelector, String expectedId) {
        SQCssIdAttributeCondition idAttributeCondition = assertSelectorIsCompiledToId(actualSelector, expectedId);
        String cssStringGeneratedByCondition = getCssStringGeneratedByCondition(idAttributeCondition);
        assertThat(cssStringGeneratedByCondition, is(actualSelector));
    }

    private String getCssStringGeneratedByCondition(SQCssIdAttributeCondition idAttributeCondition) {
        return idAttributeCondition.toElementFinder(ElementFinderUtilsTest.UNIVERSAL_SELECTOR_FINDER).getCssFinder().toString();
    }

    private SQCssIdAttributeCondition assertSelectorIsCompiledToId(String actualSelector, String expectedId) {
        // given
        // selector arg
        // when
        SQCssIdAttributeCondition cssCondition = parseAndAssertFirstCssCondition(actualSelector, SQCssIdAttributeCondition.class);
        // then
        assertThat(cssCondition.getId(), is(expectedId));
        return cssCondition;
    }

}