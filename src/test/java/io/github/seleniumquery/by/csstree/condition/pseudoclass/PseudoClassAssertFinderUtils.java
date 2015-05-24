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

import io.github.seleniumquery.by.csstree.condition.SQCssConditionImplementedFinders;
import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.finder.ElementFinder;
import io.github.seleniumquery.by.finder.ElementFinderUtilsTest;
import org.hamcrest.Matcher;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

/**
 * @author acdcjunior
 * @since 0.10.0
 */
public class PseudoClassAssertFinderUtils {

    public static final boolean PURE_CSS_IS_SUPPORTED = true;
    public static final boolean PURE_CSS_IS_NOT_SUPPORTED = false;
    public static final String CSS_UNIVERSAL_SELECTOR = "*";

    public static void assertPseudoClassHasElementFinderWhenNativelySupported(String pseudoExpressionThatShouldPassNativeSupportCheck,
                                                                              SQCssConditionImplementedFinders pseudoClassObject,
                                                                              String expectedCss,
                                                                              boolean canPureCss,
                                                                              String expectedXPath,
                                                                              Matcher<? super List<ElementFilter>> elementFilterMatcher) {
        ElementFinder previousFinder = ElementFinderUtilsTest.universalSelectorFinder(
                ElementFinderUtilsTest.createMockDriverWithNativeSupportFor(pseudoExpressionThatShouldPassNativeSupportCheck)
        );
        assertPseudoClassHasFinder(
                pseudoClassObject,
                previousFinder,
                expectedCss,
                canPureCss,
                expectedXPath,
                elementFilterMatcher
        );
    }

    public static void assertPseudoClassHasFinderWhenNotNativelySupported(String pseudoExpressionThatShouldNotPassNativeSupportCheck,
                                                                          SQCssConditionImplementedFinders pseudoClassObject,
                                                                          String expectedCss,
                                                                          boolean canPureCss,
                                                                          String expectedXPath,
                                                                          Matcher<? super List<ElementFilter>> elementFilterMatcher) {
        ElementFinder previousFinder = ElementFinderUtilsTest.universalSelectorFinder(
                ElementFinderUtilsTest.createMockDriverWithoutNativeSupportFor(pseudoExpressionThatShouldNotPassNativeSupportCheck)
        );
        assertPseudoClassHasFinder(
                pseudoClassObject,
                previousFinder,
                expectedCss, canPureCss,
                expectedXPath,
                elementFilterMatcher
        );
    }

    public static void assertPseudoSupportsOnlyPureCssAndNotPureXPathWhenNativelySupported(SQCssConditionImplementedFinders pseudoClassObject,
                                                                                           String pseudoClass,
                                                                                           String expectedXPath,
                                                                                           ElementFilter filter) {
        assertPseudoClassHasElementFinderWhenNativelySupported(
                pseudoClass,
                pseudoClassObject,
                pseudoClass,
                PURE_CSS_IS_SUPPORTED,
                expectedXPath,
                contains(filter)
        );
    }

    public static void assertPseudoSupportsBothPureCssAndPureXPathWhenNativelySupported(SQCssConditionImplementedFinders pseudoClassObject,
                                                                                        String pseudoClass,
                                                                                        String expectedXPath) {
        assertPseudoClassHasElementFinderWhenNativelySupported(
                pseudoClass,
                pseudoClassObject,
                pseudoClass,
                PURE_CSS_IS_SUPPORTED,
                expectedXPath,
                empty()
        );
    }

    public static void assertPseudoSupportsBothPureCssAndPureXPathWhenNativelySupported(
            String pseudoClassThatShouldBeNativelySupported,
            SQCssConditionImplementedFinders pseudoClassObject,
            String expectedCSS,
            String expectedXPath) {
        assertPseudoClassHasElementFinderWhenNativelySupported(
                pseudoClassThatShouldBeNativelySupported,
                pseudoClassObject,
                expectedCSS,
                PURE_CSS_IS_SUPPORTED,
                expectedXPath,
                empty()
        );
    }

    public static void assertPseudoClassDoesNotSupportAnythingPurelyWhenNotNativelySupported(SQCssConditionImplementedFinders pseudoClassObject,
                                                                                             String pseudoClass,
                                                                                             String expectedXPath,
                                                                                             ElementFilter filter) {
        assertPseudoClassHasFinderWhenNotNativelySupported(
                pseudoClass,
                pseudoClassObject,
                CSS_UNIVERSAL_SELECTOR,
                PURE_CSS_IS_NOT_SUPPORTED,
                expectedXPath,
                contains(filter)
        );
    }

    /**
     * The resulting CSS expected is the universal selector (*).
     */
    public static void assertPseudoClassOnlySupportsPureXPathWhenNotNativelySupported(SQCssConditionImplementedFinders pseudoClassObject,
                                                                                      String pseudoClass, String expectedXPath) {
        assertPseudoClassHasFinderWhenNotNativelySupported(
                pseudoClass,
                pseudoClassObject,
                CSS_UNIVERSAL_SELECTOR,
                PURE_CSS_IS_NOT_SUPPORTED,
                expectedXPath,
                empty()
        );
    }

    /**
     * Pseudos that are tested using this method are not expected to be supported by browsers in any time soon.
     * so this assert function makes it clear that they wont even check for browser support!!!
     *
     * In other words, the not testing for browser support is INTENTIONAL, not an accident!
     * IF they begin to be supported natively, we will enable the check, but until then, checking is just
     * silly (and an indicator that we were not really CERTAIN of what we were doing).
     */
    public static void assertPseudoClassOnlySupportsPureXPathRegardlessOfNativeSupport(SQCssConditionImplementedFinders pseudoClassObject,
                                                                                       String pseudoClass, String expectedXPath) {
        assertPseudoOnlySupportsPureXPathWhenNativelySupported(pseudoClassObject, pseudoClass, expectedXPath);
        assertPseudoClassOnlySupportsPureXPathWhenNotNativelySupported(pseudoClassObject, pseudoClass, expectedXPath);
    }

    public static void assertPseudoSupportsDifferentButPureCssAndPureXPathRegardlessOfNativeSupport(SQCssConditionImplementedFinders pseudoClassObject,
                                                                                                    String pseudoClass,
                                                                                                    String expectedCss,
                                                                                                    String expectedXPath) {
        assertPseudoClassHasElementFinderWhenNativelySupported(
                pseudoClass, pseudoClassObject,
                expectedCss, PURE_CSS_IS_SUPPORTED,
                expectedXPath,
                empty()
        );
        assertPseudoClassHasFinderWhenNotNativelySupported(
                pseudoClass, pseudoClassObject,
                expectedCss, PURE_CSS_IS_SUPPORTED,
                expectedXPath,
                empty()
        );
    }

    public static void assertPseudoOnlySupportsPureXPathWhenNativelySupported(SQCssConditionImplementedFinders pseudoClassObject, String pseudoClass,
                                                                              String expectedXPath) {
        assertPseudoClassHasElementFinderWhenNativelySupported(
                pseudoClass, pseudoClassObject,
                CSS_UNIVERSAL_SELECTOR, PURE_CSS_IS_NOT_SUPPORTED,
                expectedXPath,
                empty()
        );
    }

    public static void assertPseudoClassHasFinder(SQCssConditionImplementedFinders pseudoClassObject,
                                                  ElementFinder previous,
                                                  String expectedCss, boolean canPureCss,
                                                  String expectedXPath,
                                                  Matcher<? super List<ElementFilter>> elementFilterMatcher) {
        // given
        // args
        // when
        ElementFinder elementFinder = pseudoClassObject.toElementFinder(previous);
        // then
        assertThat("CSS selector", elementFinder.getCssFinder().toString(), is(expectedCss));
        assertThat("Can pure CSS?", elementFinder.canFetchThroughCssAlone(), is(canPureCss));
        assertThat("XPath Expression", elementFinder.getXPathExpression(), is(expectedXPath));
        assertThat("ElementFilterList", elementFinder.getElementFilterList().getElementFilters(), elementFilterMatcher);
    }

}