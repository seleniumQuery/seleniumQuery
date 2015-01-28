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

package io.github.seleniumquery.by.csstree.condition.pseudoclass;

import io.github.seleniumquery.by.csstree.condition.SQCssCondition;

import static io.github.seleniumquery.by.parser.translator.condition.attribute.TranslatorsTestUtils.parseFirstCssCondition;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class PseudoClassTestUtils {

    public static <T extends SQCssCondition> void assertPseudo(String selector, Class<T> pseudoClassClass) {
        // given
        // selector
        // when
        T cssCondition = parseFirstCssCondition(selector, pseudoClassClass);
        // then
        assertThat(cssCondition, is(notNullValue()));
    }

    public static <T extends SQCssFunctionalPseudoClassCondition> void assertFunctionalPseudo(String selector,
                                                                                              Class<T> pseudoClassClass) {
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(0)", "0");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(-0)", "-0");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(+0)", "+0");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(1)", "1");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(-1)", "-1");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(+1)", "+1");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(999999)", "999999");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(-999999)", "-999999");

        assertSelectorTranslatesArgument(selector, pseudoClassClass, "", null);
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "()", "");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "( )", " ");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(     )", "     ");

        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(a)", "a");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "('a')", "'a'");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(\"a\")", "\"a\"");

        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(a b c d e)", "a b c d e");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(\"a b c d e\")", "\"a b c d e\"");
    }

    private static <T extends SQCssFunctionalPseudoClassCondition> void assertSelectorTranslatesArgument(String selector,
                                                                                                         Class<T> pseudoClassClass,
                                                                                                         String selectorSuffix,
                                                                                                         String expectedArgument) {
        // given
        // selector
        // when
        T cssCondition = parseFirstCssCondition(selector+selectorSuffix, pseudoClassClass);
        // then
        if (expectedArgument != null) {
            assertThat(cssCondition.getArgument(), is(expectedArgument));
        } else {
            assertThat(cssCondition.getArgument(), is(nullValue()));
        }
    }

}