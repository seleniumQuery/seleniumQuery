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

package io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter;

import static io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils.AssertPseudoClass
    .assertPseudoClass;
import static io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassTestUtils.assertQueriesOnSelector;

import org.junit.Test;

/**
 * :nth() selector is just an alias to :eq(), so this test class is equal to the eq test class.
 * Edit there and then edit here. (Of course, the best would be to refactor them to eliminate code
 * duplication, but since it is this class only and :nth has so little relevance we'll keep it this way).
 */
public class CssNthPseudoClassTest {

    private static final String NTH_PSEUDO = ":nth";

    @Test
    public void translate() {
        assertQueriesOnSelector(NTH_PSEUDO).withAllKindsOfArguments().yieldFunctionalIndexArgPseudoclassWithCorrectlyTranslatedArguments(CssNthPseudoClass.class);
    }

    @Test
    public void toElementFinder__nth_0__only_generates_XPath_regardless_of_native_support() {
        String nth0XPathExpression = "(.//*)[position() = 1]";
        assertNthArgumentGeneratesXPath(0, nth0XPathExpression);
    }

    private void assertNthArgumentGeneratesXPath(int nthArgument, String nthXPathExpression) {
        assertPseudoClass(new CssNthPseudoClass(nthArgument)).whenNotNativelySupported().translatesToPureXPath(nthXPathExpression);
    }

    @Test
    public void toElementFinder__nth_1__only_generates_XPath_regardless_of_native_support() {
        String nth1XPathExpression = "(.//*)[position() = 2]";
        assertNthArgumentGeneratesXPath(1, nth1XPathExpression);
    }

    @Test
    public void toElementFinder__nth_2_NEGATIVE__only_generates_XPath_regardless_of_native_support() {
        String nthNegative2XPathExpression = "(.//*)[position() = (last()-1)]";
        assertNthArgumentGeneratesXPath(-2, nthNegative2XPathExpression);
    }

    @Test
    public void toElementFinder__nth_1_NEGATIVE__does_not_add_MINUS_ZERO_in_the_XPath_expression() {
        assertNthArgumentGeneratesXPath(-1, "(.//*)[position() = last()]");
    }

}
