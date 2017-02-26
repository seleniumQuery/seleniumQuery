/*
 * Copyright (c) 2016 seleniumQuery authors
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

import io.github.seleniumquery.by.secondgen.csstree.condition.CssCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.CssConditionImplementedFinders;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssConditionalSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssTagNameSelector;
import io.github.seleniumquery.by.secondgen.finder.ElementFinderUtilsTest;
import io.github.seleniumquery.by.secondgen.parser.ParseTreeBuilder;

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
    public static <T extends CssCondition> T parseAndAssertFirstCssCondition(String selector, Class<T> conditionClass) {
        CssSelector cssSelector = ParseTreeBuilder.parse(selector).firstSelector();
        assertThat(cssSelector, instanceOf(CssConditionalSelector.class));
        // when
        CssSelector sqCssSelector = ((CssConditionalSelector) cssSelector).getCssSelector();
        CssCondition cssCondition = ((CssConditionalSelector) cssSelector).getCssCondition();
        // then
        assertThat(sqCssSelector, instanceOf(CssTagNameSelector.class));
        assertThat(cssCondition, instanceOf(conditionClass));
        assertThat(((CssTagNameSelector) sqCssSelector).getTagName(), is("*"));
        return (T) cssCondition;
    }

    @SuppressWarnings("deprecation")
    static String getCssStringGeneratedByCondition(CssConditionImplementedFinders condition) {
        return condition.toElementFinder(ElementFinderUtilsTest.UNIVERSAL_SELECTOR_FINDER).toCssString();
    }

}
