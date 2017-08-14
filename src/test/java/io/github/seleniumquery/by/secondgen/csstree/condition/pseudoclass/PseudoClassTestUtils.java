/*
 * Copyright (c) 2017 seleniumQuery authors
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

package io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass;

import static io.github.seleniumquery.by.secondgen.finder.ElementFinderUtilsTest.createWebDriverWithNativeSupportForNoPseudoClass;
import static io.github.seleniumquery.by.secondgen.finder.ElementFinderUtilsTest.universalSelectorFinder;
import static io.github.seleniumquery.by.secondgen.parser.translator.condition.attribute.TranslatorsTestUtils
    .parseAndAssertFirstCssCondition;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;

import io.github.seleniumquery.SeleniumQueryException;
import io.github.seleniumquery.by.common.elementfilter.ElementFilter;
import io.github.seleniumquery.by.secondgen.csstree.condition.CssCondition;
import io.github.seleniumquery.by.secondgen.finder.ElementFinder;

public class PseudoClassTestUtils {

    public static QueriesOnPseudoclassSelectorsTestAssertBuilder assertQueriesOnSelector(String selector) {
        return new QueriesOnPseudoclassSelectorsTestAssertBuilder(selector);
    }

    public static class QueriesOnPseudoclassSelectorsTestAssertBuilder {
        private final String selector;

        private QueriesOnPseudoclassSelectorsTestAssertBuilder(String selector) { this.selector = selector; }

        public <T extends CssCondition> void yieldPseudoClass(Class<T> pseudoClassClass) {
            assertQueryOnSelectorYieldsPseudoClass(this.selector, pseudoClassClass);
        }
        public QueriesOnFunctionalPseudoclassSelectorsTestAssertBuilder withAllKindsOfArguments() {
            return new QueriesOnFunctionalPseudoclassSelectorsTestAssertBuilder(this.selector);
        }
        public QueriesOnFunctionalPseudoclassesThatExpectSelectorsArAsgumentsTestAssertBuilder withSelectorArguments() {
            return new QueriesOnFunctionalPseudoclassesThatExpectSelectorsArAsgumentsTestAssertBuilder(this.selector);
        }
    }

    private static <T extends CssCondition> void assertQueryOnSelectorYieldsPseudoClass(String selector, Class<T> pseudoClassClass) {
        // given
        // selector
        // when
        T cssCondition = parseAndAssertFirstCssCondition(selector, pseudoClassClass);
        // then
        assertThat(cssCondition, is(notNullValue()));
    }

    public static class QueriesOnFunctionalPseudoclassSelectorsTestAssertBuilder {
        private final String selector;
        private QueriesOnFunctionalPseudoclassSelectorsTestAssertBuilder(String selector) { this.selector = selector; }

        public <T extends CssPseudoClassCondition> void yieldFunctionalPseudoclassWithCorrectlyTranslatedArguments(Class<T> pseudoClassClass) {
            assertQueriesOnSelectorWithArgumentsYieldFunctionalPseudoClass(this.selector, pseudoClassClass);
        }
        public <T extends CssPseudoClassCondition> void yieldFunctionalIndexArgPseudoclassWithCorrectlyTranslatedArguments(Class<T> pseudoClassClass) {
            assertQueriesOnSelectorWithArgumentsYieldFunctionalIndexArgPseudoClass(this.selector, pseudoClassClass);
        }
    }

    private static <T extends CssPseudoClassCondition> void assertQueriesOnSelectorWithArgumentsYieldFunctionalIndexArgPseudoClass(String selector,
                                                                                                                                     Class<T> pseudoClassClass) {
        assertSelectorFailsAtTranslatingArgument(selector, pseudoClassClass, "", "Functional pseudo", "(Invalid token \"not\".");

        assertSelectorDoesNotErrorWhenTranslating(selector, pseudoClassClass, "(0)");
        assertSelectorDoesNotErrorWhenTranslating(selector, pseudoClassClass, "(-0)");
        assertSelectorDoesNotErrorWhenTranslating(selector, pseudoClassClass, "(+0)");
        assertSelectorDoesNotErrorWhenTranslating(selector, pseudoClassClass, "(1)");
        assertSelectorDoesNotErrorWhenTranslating(selector, pseudoClassClass, "(-1)");
        assertSelectorDoesNotErrorWhenTranslating(selector, pseudoClassClass, "(+1)");

        assertSelectorFailsAtTranslatingArgument(selector, pseudoClassClass, "()", "pseudo-class requires an integer as argument");
        assertSelectorFailsAtTranslatingArgument(selector, pseudoClassClass, "(     )", "pseudo-class requires an integer as argument");

        assertSelectorFailsAtTranslatingArgument(selector, pseudoClassClass, "(\"a 'c' b\")", "pseudo-class requires an integer as argument");
    }

    private static <T extends CssPseudoClassCondition> void assertQueriesOnSelectorWithArgumentsYieldFunctionalPseudoClass(String selector,
                                                                                                                                     Class<T> pseudoClassClass) {
        assertSelectorFailsAtTranslatingArgument(selector, pseudoClassClass, "", "Functional pseudo", "(Invalid token \"not\".");

        assertSelectorDoesNotErrorWhenTranslating(selector, pseudoClassClass, "(0)");
        assertSelectorDoesNotErrorWhenTranslating(selector, pseudoClassClass, "(-0)");
        assertSelectorDoesNotErrorWhenTranslating(selector, pseudoClassClass, "(+0)");
        assertSelectorDoesNotErrorWhenTranslating(selector, pseudoClassClass, "(1)");
        assertSelectorDoesNotErrorWhenTranslating(selector, pseudoClassClass, "(-1)");
        assertSelectorDoesNotErrorWhenTranslating(selector, pseudoClassClass, "(+1)");
        assertSelectorDoesNotErrorWhenTranslating(selector, pseudoClassClass, "()");
        assertSelectorDoesNotErrorWhenTranslating(selector, pseudoClassClass, "(     )");
        assertSelectorDoesNotErrorWhenTranslating(selector, pseudoClassClass, "(\"a 'c' b\")");
    }

    private static <T extends CssPseudoClassCondition> void assertSelectorFailsAtTranslatingArgument(String selector,
                                                                                                     Class<T> pseudoClassClass,
                                                                                                     String selectorSuffix,
                                                                                                     String... possibleExceptions) {
        try {
            parseAndAssertFirstCssCondition(selector + selectorSuffix, pseudoClassClass);
            fail("Functional Pseudo called like \":"+selector+selectorSuffix+"\" should throw exception.");
        } catch (IllegalArgumentException | org.w3c.css.sac.CSSParseException | SeleniumQueryException e) {
            List<Matcher<? super String>> containsStringsPossibleExceptions = Stream.of(possibleExceptions)
                .map(CoreMatchers::containsString)
                .collect(Collectors.toList());
            assertThat(e.getMessage(), anyOf(containsStringsPossibleExceptions));
        }
    }

    private static <T extends CssPseudoClassCondition> void assertSelectorDoesNotErrorWhenTranslating(String selector, Class<T> pseudoClassClass,
                                                                                                                             String selectorSuffix) {
        // given
        // selector
        // when
        parseAndAssertFirstCssCondition(selector + selectorSuffix, pseudoClassClass);
        // then
        // no exception
    }

    private static <T extends CssFunctionalPseudoClassCondition> void assertSelectorTranslatesArgument(String selector, Class<T> pseudoClassClass,
                                                                                                       String selectorSuffix, String expectedArgument) {
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

    public static void assertFilterOnlyPseudoGeneratesFilter(CssPseudoClassCondition pseudoClassCondition, ElementFilter pseudoClassFilter) {
        ElementFinder previous = universalSelectorFinder(createWebDriverWithNativeSupportForNoPseudoClass());
        // when
        ElementFinder elementFinder = pseudoClassCondition.toElementFinder(previous);
        // then
        assertThat(elementFinder.toCssString(), is(previous.toCssString()));
        assertThat(elementFinder.canFetchThroughCssAlone(), is(false));
        assertThat(elementFinder.getXPathExpression(), is(previous.getXPathExpression()));
        assertThat(elementFinder.getElementFilterList().getElementFilters(), contains(pseudoClassFilter));
    }

    public static class QueriesOnFunctionalPseudoclassesThatExpectSelectorsArAsgumentsTestAssertBuilder {
        private final String selector;
        private QueriesOnFunctionalPseudoclassesThatExpectSelectorsArAsgumentsTestAssertBuilder(String selector) { this.selector = selector; }

        public <T extends CssPseudoClassCondition> void yieldFunctionalPseudoclassWithCorrectlyTranslatedSelectorArguments(Class<T> pseudoClassClass,
                                                                                                                           String... possibleExceptionMessage) {
            assertQueriesOnSelectorWithSelectorArgumentsYieldFunctionalPseudoClass(this.selector, pseudoClassClass, possibleExceptionMessage);
        }
    }

    private static <T extends CssPseudoClassCondition> void assertQueriesOnSelectorWithSelectorArgumentsYieldFunctionalPseudoClass(String selector,
                                                                                                                                   Class<T> pseudoClassClass,
                                                                                                                                   String... possibleExceptionMessage) {
        assertSelectorFailsAtTranslatingArgument(selector, pseudoClassClass, "", possibleExceptionMessage);
        assertSelectorFailsAtTranslatingArgument(selector, pseudoClassClass, "(0)", possibleExceptionMessage);
        assertSelectorFailsAtTranslatingArgument(selector, pseudoClassClass, "(-0)", possibleExceptionMessage);
        assertSelectorFailsAtTranslatingArgument(selector, pseudoClassClass, "(+0)", possibleExceptionMessage);
        assertSelectorFailsAtTranslatingArgument(selector, pseudoClassClass, "(1)", possibleExceptionMessage);
        assertSelectorFailsAtTranslatingArgument(selector, pseudoClassClass, "(-1)", possibleExceptionMessage);
        assertSelectorFailsAtTranslatingArgument(selector, pseudoClassClass, "(+1)", possibleExceptionMessage);
        assertSelectorFailsAtTranslatingArgument(selector, pseudoClassClass, "()", possibleExceptionMessage);
        assertSelectorFailsAtTranslatingArgument(selector, pseudoClassClass, "(     )", possibleExceptionMessage);
        assertSelectorFailsAtTranslatingArgument(selector, pseudoClassClass, "(\"a 'c' b\")", possibleExceptionMessage);

        assertSelectorTrowsNoExceptionWhileTranslatingArgument(selector, pseudoClassClass, "(tag)");
    }

    @SuppressWarnings("SameParameterValue")
    private static <T extends CssPseudoClassCondition> void assertSelectorTrowsNoExceptionWhileTranslatingArgument(String selector,
                                                                                                                   Class<T> pseudoClassClass,
                                                                                                                   String selectorSuffix) {
        // given
        // selector
        // when
        parseAndAssertFirstCssCondition(selector + selectorSuffix, pseudoClassClass);
        // then
        // no exception is thrown
    }

}
