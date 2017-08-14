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

public class CssLtPseudoClassTest {

    private static final String LT_PSEUDO = ":lt";

    @Test
    public void translate() {
        assertQueriesOnSelector(LT_PSEUDO).withAllKindsOfArguments().yieldFunctionalIndexArgPseudoclassWithCorrectlyTranslatedArguments(CssLtPseudoClass.class);
    }

    @Test
    public void toElementFinder__lt_0__only_generates_XPath_regardless_of_native_support() {
        String lt0XPathExpression = "(.//*)[position() < 1]";
        assertLtArgumentGeneratesXPath(0, lt0XPathExpression);
    }

    private void assertLtArgumentGeneratesXPath(int ltArgument, String ltXPathExpression) {
        assertPseudoClass(new CssLtPseudoClass(new AstCssLtPseudoClass(ltArgument))).whenNotNativelySupported().translatesToPureXPath(ltXPathExpression);
    }

    @Test
    public void toElementFinder__lt_1__only_generates_XPath_regardless_of_native_support() {
        String lt1XPathExpression = "(.//*)[position() < 2]";
        assertLtArgumentGeneratesXPath(1, lt1XPathExpression);
    }

    @Test
    public void toElementFinder__lt_2_NEGATIVE__only_generates_XPath_regardless_of_native_support() {
        String ltNegative2XPathExpression = "(.//*)[position() < (last()-1)]";
        assertLtArgumentGeneratesXPath(-2, ltNegative2XPathExpression);
    }

}
