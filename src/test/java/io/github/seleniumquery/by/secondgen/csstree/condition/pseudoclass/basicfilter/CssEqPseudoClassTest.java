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

public class CssEqPseudoClassTest {

    private static final String EQ_PSEUDO = ":eq";

    @Test
    public void translate() {
        assertQueriesOnSelector(EQ_PSEUDO).withAllKindsOfArguments().yieldFunctionalIndexArgPseudoclassWithCorrectlyTranslatedArguments(CssEqPseudoClass.class);
    }

    @Test
    public void toElementFinder__eq_0__only_generates_XPath_regardless_of_native_support() {
        String eq0XPathExpression = "(.//*)[position() = 1]";
        assertEqArgumentGeneratesXPath(0, eq0XPathExpression);
    }

    private void assertEqArgumentGeneratesXPath(int eqArgument, String eqXPathExpression) {
        assertPseudoClass(new CssEqPseudoClass(eqArgument)).whenNotNativelySupported().translatesToPureXPath(eqXPathExpression);
    }

    @Test
    public void toElementFinder__eq_1__only_generates_XPath_regardless_of_native_support() {
        String eq1XPathExpression = "(.//*)[position() = 2]";
        assertEqArgumentGeneratesXPath(1, eq1XPathExpression);
    }

    @Test
    public void toElementFinder__eq_2_NEGATIVE__only_generates_XPath_regardless_of_native_support() {
        String eqNegative2XPathExpression = "(.//*)[position() = (last()-1)]";
        assertEqArgumentGeneratesXPath(-2, eqNegative2XPathExpression);
    }

    @Test
    public void toElementFinder__eq_1_NEGATIVE__does_not_add_MINUS_ZERO_in_the_XPath_expression() {
        assertEqArgumentGeneratesXPath(-1, "(.//*)[position() = last()]");
    }

}
