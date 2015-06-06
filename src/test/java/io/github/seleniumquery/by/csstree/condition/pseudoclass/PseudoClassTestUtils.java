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

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.csstree.condition.SQCssCondition;
import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.finder.ElementFinder;
import io.github.seleniumquery.by.finder.ElementFinderUtilsTest;
import io.github.seleniumquery.by.preparser.CSSParsedSelectorList;
import io.github.seleniumquery.by.preparser.CSSSelectorParser;
import io.github.seleniumquery.by.preparser.FakeArgumentMap;
import org.w3c.css.sac.Selector;

import static io.github.seleniumquery.by.parser.translator.condition.attribute.TranslatorsTestUtils.parseAndAssertFirstCssCondition;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class PseudoClassTestUtils {

    public static final PseudoClassSelector EMPTY = new PseudoClassSelector(null, null, "") {
        @Override
        public String getPseudoClassContent() {
            return "";
        }
    };

    public static QueriesOnPseudoclassSelectorsTestAssertBuilder assertQueriesOnSelector(String selector) {
        return new QueriesOnPseudoclassSelectorsTestAssertBuilder(selector);
    }

    public static class QueriesOnPseudoclassSelectorsTestAssertBuilder {
        private final String selector;

        public QueriesOnPseudoclassSelectorsTestAssertBuilder(String selector) { this.selector = selector; }

        public <T extends SQCssCondition> void yieldPseudoClass(Class<T> pseudoClassClass) {
            assertQueryOnSelectorYieldsPseudoClass(this.selector, pseudoClassClass);
        }
        public QueriesOnFunctionalPseudoclassSelectorsTestAssertBuilder withAllKindsOfArguments() {
            return new QueriesOnFunctionalPseudoclassSelectorsTestAssertBuilder(this.selector);
        }
    }

    private static <T extends SQCssCondition> void assertQueryOnSelectorYieldsPseudoClass(String selector, Class<T> pseudoClassClass) {
        // given
        // selector
        // when
        T cssCondition = parseAndAssertFirstCssCondition(selector, pseudoClassClass);
        // then
        assertThat(cssCondition, is(notNullValue()));
    }

    public static class QueriesOnFunctionalPseudoclassSelectorsTestAssertBuilder {
        private final String selector;
        public QueriesOnFunctionalPseudoclassSelectorsTestAssertBuilder(String selector) { this.selector = selector; }

        public <T extends SQCssFunctionalPseudoClassCondition> void yieldFunctionalPseudoclassWithCorrectlyTranslatedArguments(Class<T> pseudoClassClass) {
            assertQueriesOnSelectorWithArgumentsYieldFunctionalPseudoClass2(this.selector, pseudoClassClass);
        }
    }

    private static <T extends SQCssFunctionalPseudoClassCondition> void assertQueriesOnSelectorWithArgumentsYieldFunctionalPseudoClass2(String selector,
                                                                                                                                      Class<T> pseudoClassClass) {
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(0)", "0");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(-0)", "-0");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(+0)", "+0");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(1)", "1");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(-1)", "-1");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(+1)", "+1");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(999999)", "999999");
        assertSelectorTranslatesArgument(selector, pseudoClassClass, "(-999999)", "-999999");

        try {
            assertSelectorTranslatesArgument(selector, pseudoClassClass, "", null);
            fail("Functional Pseudo called without () should throw exception.");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), containsString("Functional pseudo"));
        }
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
        T cssCondition = parseAndAssertFirstCssCondition(selector + selectorSuffix, pseudoClassClass);
        // then
        if (expectedArgument != null) {
            assertThat(cssCondition.getArgument(), is(expectedArgument));
        } else {
            assertThat(cssCondition.getArgument(), is(nullValue()));
        }
    }

    public static void assertFilterOnlyPseudoGeneratesFilter(SQCssPseudoClassCondition pseudoClassCondition, ElementFilter pseudoClassFilter) {
        ElementFinder previous = ElementFinderUtilsTest.universalSelectorFinder(ElementFinderUtilsTest.createMockDriverWithoutNativeSupportFor(getSelectorForPseudoClass(pseudoClassCondition)));
        // when
        ElementFinder elementFinder = pseudoClassCondition.toElementFinder(previous);
        // then
        assertThat(elementFinder.getCssFinder().toString(), is(previous.getCssFinder().toString()));
        assertThat(elementFinder.canFetchThroughCssAlone(), is(false));
        assertThat(elementFinder.getXPathExpression(), is(previous.getXPathExpression()));
        assertThat(elementFinder.getElementFilterList().getElementFilters(), contains(pseudoClassFilter));
    }

    private static String getSelectorForPseudoClass(SQCssPseudoClassCondition pseudoClassCondition) {
        try {
            return ":" + pseudoClassCondition.getClass().getDeclaredField("PSEUDO").get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method creates a {@link PseudoClassSelector} equivalent to {@code *:the-pseudo-class(ARGUMENT)}.
     *
     * @param functionalPseudoClassArgument the ARGUMENT.
     */
    public static PseudoClassSelector createPseudoClassSelectorAppliedToUniversalSelector(String functionalPseudoClassArgument) {
        CSSParsedSelectorList cssParsedSelectorList = CSSSelectorParser.parseSelector("*");
        Selector universalSelector = cssParsedSelectorList.getSelectorList().item(0);
        FakeArgumentMap stringMap = new FakeArgumentMap();
        stringMap.put(1, functionalPseudoClassArgument);
        return new PseudoClassSelector(stringMap, universalSelector, "(1)");
    }

}