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

import io.github.seleniumquery.by.csstree.condition.SQCssConditionImplementedLocators;
import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.locator.SQLocator;
import io.github.seleniumquery.by.locator.SQLocatorUtilsTest;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

/**
 * @author acdcjunior
 * @since 0.10.0
 */
public class PseudoClassAssertLocatorUtils {

    public static final boolean PURE_CSS_IS_SUPPORTED = true;
    public static final boolean PURE_XPATH_IS_SUPPORTED = true;
    public static final boolean PURE_CSS_IS_NOT_SUPPORTED = false;
//    public static final boolean PURE_XPATH_IS_NOT_SUPPORTED = false;

    public static void assertPseudoClassHasLocatorWhenNativelySupported(SQCssConditionImplementedLocators pseudoClassObject, String pseudoClass, boolean canPureCss, String expectedCss, boolean canPureXPath, String expectedXPath, Matcher<Iterable<? extends ElementFilter>> elementFilterMatcher) {
        SQLocator previous = SQLocatorUtilsTest.tagAsterisk(SQLocatorUtilsTest.createMockDriverWithNativeSupportFor(pseudoClass));
        assertPseudoClassHasLocator(pseudoClassObject, previous, expectedCss, expectedXPath, canPureCss, canPureXPath, elementFilterMatcher);
    }

    public static void assertPseudoClassHasLocatorWhenNotNativelySupported(SQCssConditionImplementedLocators pseudoClassObject, String pseudoClass, boolean canPureCss, String expectedCss, boolean canPureXPath, String expectedXPath, Matcher<Iterable<? extends ElementFilter>> elementFilterMatcher) {
        SQLocator previous = SQLocatorUtilsTest.tagAsterisk(SQLocatorUtilsTest.createMockDriverWithoutNativeSupportFor(pseudoClass));
        assertPseudoClassHasLocator(pseudoClassObject, previous, expectedCss, expectedXPath, canPureCss, canPureXPath, elementFilterMatcher);
    }

    public static void assertPseudoSupportsBothPureCssAndPureXPathWhenNativelySupported(SQCssConditionImplementedLocators pseudoClassObject, String pseudoClass, String expectedXPath) {
        assertPseudoClassHasLocatorWhenNativelySupported(
                pseudoClassObject,
                pseudoClass,
                PURE_CSS_IS_SUPPORTED,
                pseudoClass,
                PURE_XPATH_IS_SUPPORTED,
                expectedXPath,
                contains(ElementFilter.FILTER_NOTHING)
        );
    }

    public static void assertPseudoClassOnlySupportsPureXPathWhenNotNativelySupported(SQCssConditionImplementedLocators pseudoClassObject, String pseudoClass, String expectedXPath) {
        assertPseudoClassHasLocatorWhenNotNativelySupported(
                pseudoClassObject,
                pseudoClass,
                PURE_CSS_IS_NOT_SUPPORTED,
                "*",
                PURE_XPATH_IS_SUPPORTED,
                expectedXPath,
                contains(ElementFilter.FILTER_NOTHING)
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
    public static void assertPseudoClassOnlySupportsPureXPathRegardlessOfNativeSupport(SQCssConditionImplementedLocators pseudoClassObject, String pseudoClass, String expectedXPath) {
        assertPseudoOnlySupportsPureXPathWhenNativelySupported(pseudoClassObject, pseudoClass, expectedXPath);
        assertPseudoClassOnlySupportsPureXPathWhenNotNativelySupported(pseudoClassObject, pseudoClass, expectedXPath);
    }

    public static void assertPseudoOnlySupportsPureXPathWhenNativelySupported(SQCssConditionImplementedLocators pseudoClassObject, String pseudoClass, String expectedXPath) {
        assertPseudoClassHasLocatorWhenNativelySupported(
                pseudoClassObject,
                pseudoClass,
                PURE_CSS_IS_NOT_SUPPORTED,
                "*",
                PURE_XPATH_IS_SUPPORTED,
                expectedXPath,
                contains(ElementFilter.FILTER_NOTHING)
        );
    }

    public static void assertPseudoClassHasLocator(SQCssConditionImplementedLocators pseudoClassObject, SQLocator previous, String expectedCss, String expectedXPath, boolean canPureCss, boolean canPureXPath, Matcher<Iterable<? extends ElementFilter>> elementFilterMatcher) {
        // given
        // args
        // when
        SQLocator locator = pseudoClassObject.toSQLocator(previous);
        // then
        assertThat(locator.getCssSelector(), is(expectedCss));
        assertThat(locator.getXPathExpression(), is(expectedXPath));
        assertThat(locator.canPureCss(), is(canPureCss));
        assertThat(locator.canPureXPath(), is(canPureXPath));
        assertThat(locator.getElementFilterList().getElementFilters(), elementFilterMatcher);
    }

}