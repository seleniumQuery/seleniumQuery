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
import io.github.seleniumquery.by.secondgen.csstree.selector.SQCssConditionalSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.SQCssSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.SQCssTagNameSelector;
import io.github.seleniumquery.by.secondgen.parser.SQParseTreeBuilder;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TranslatorsTestUtils {

    /**
     * Parses {@code selector} and checks if it yields a CSS condition that is an instance of {@code conditionClass}.
     * If so, returns it.
     *
     * @param selector The string selector to be parsed.
     * @param conditionClass The class the condition is expected to be.
     * @param <T> The type of the condition.
     * @return The condition instance, after parse.
     */
    @SuppressWarnings("unchecked")
    public static <T extends SQCssCondition> T parseAndAssertFirstCssCondition(String selector, Class<T> conditionClass) {
        SQCssSelector cssSelector = SQParseTreeBuilder.parse(selector).firstSelector();
        assertThat(cssSelector, instanceOf(SQCssConditionalSelector.class));
        // when
        SQCssSelector sqCssSelector = ((SQCssConditionalSelector) cssSelector).getSqCssSelector();
        SQCssCondition sqCssCondition = ((SQCssConditionalSelector) cssSelector).getSqCssCondition();
        // then
        assertThat(sqCssSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(sqCssCondition, instanceOf(conditionClass));
        assertThat(((SQCssTagNameSelector) sqCssSelector).getTagName(), is("*"));
        return (T) sqCssCondition;
    }

}