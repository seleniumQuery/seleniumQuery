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

public class CssGtPseudoClassTest {

    private static final String GT_PSEUDO = ":gt";

    @Test
    public void translate() {
        assertQueriesOnSelector(GT_PSEUDO).withAllKindsOfArguments().yieldFunctionalIndexArgPseudoclassWithCorrectlyTranslatedArguments(CssGtPseudoClass.class);
    }

    @Test
    public void toElementFinder__gt_0__only_generates_XPath_regardless_of_native_support() {
        String gt0XPathExpression = "(.//*)[position() > 1]";
        assertGtArgumentGeneratesXPath(0, gt0XPathExpression);
    }

    private void assertGtArgumentGeneratesXPath(int gtArgument, String gtXPathExpression) {
        assertPseudoClass(new CssGtPseudoClass(new AstCssGtPseudoClass(gtArgument))).whenNotNativelySupported().translatesToPureXPath(gtXPathExpression);
    }

    @Test
    public void toElementFinder__gt_1__only_generates_XPath_regardless_of_native_support() {
        String gt1XPathExpression = "(.//*)[position() > 2]";
        assertGtArgumentGeneratesXPath(1, gt1XPathExpression);
    }

    @Test
    public void toElementFinder__gt_2_NEGATIVE__only_generates_XPath_regardless_of_native_support() {
        String gtNegative2XPathExpression = "(.//*)[position() > (last()-1)]";
        assertGtArgumentGeneratesXPath(-2, gtNegative2XPathExpression);
    }

}
