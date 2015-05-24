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

package io.github.seleniumquery.by.csstree.condition.pseudoclass.basicfilter;

import org.junit.Test;
import org.openqa.selenium.InvalidSelectorException;

import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils.assertPseudoClassOnlySupportsPureXPathRegardlessOfNativeSupport;
import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.assertFunctionalPseudo;
import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.createPseudoClassSelectorAppliedToUniversalSelector;
import static io.github.seleniumquery.by.locator.ElementFinderUtilsTest.UNIVERSAL_SELECTOR_LOCATOR;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class SQCssGtPseudoClassTest {

    public static final String GT_PSEUDO = ":gt";

    @Test
    public void translate() {
        assertFunctionalPseudo(GT_PSEUDO, SQCssGtPseudoClass.class);
    }

    @Test
    public void toElementFinder__gt_should_throw_exception_if_argument_is_not_an_integer() {
        assertGtArgumentIsNotValid("a");
        assertGtArgumentIsNotValid("");
        assertGtArgumentIsNotValid("+");
        assertGtArgumentIsNotValid("-");
        assertGtArgumentIsNotValid("+ 1");
        assertGtArgumentIsNotValid(" ");
    }

    private void assertGtArgumentIsNotValid(String gtArgument) {
        try {
            gt(gtArgument).toElementFinder(UNIVERSAL_SELECTOR_LOCATOR);
            fail("Should consider *:gt("+gtArgument+") to be invalid.");
        } catch (InvalidSelectorException e) {
            assertThat(e.getMessage(), containsString(":gt()"));
            assertThat(e.getMessage(), containsString(gtArgument));
        }
    }

    private SQCssGtPseudoClass gt(String gtArgument) {
        return new SQCssGtPseudoClass(createPseudoClassSelectorAppliedToUniversalSelector(gtArgument));
    }

    @Test
    public void toElementFinder__gt_0__only_generates_XPath_regardless_of_native_support() {
        String gt0XPathExpression = "(.//*)[position() > 1]";
        assertGtArgumentGeneratesXPath("0", gt0XPathExpression);
        assertGtArgumentGeneratesXPath("+0", gt0XPathExpression);
        assertGtArgumentGeneratesXPath("-0", gt0XPathExpression);
        assertGtArgumentGeneratesXPath(" +0", gt0XPathExpression);
        assertGtArgumentGeneratesXPath(" -0", gt0XPathExpression);
        assertGtArgumentGeneratesXPath("+0 ", gt0XPathExpression);
        assertGtArgumentGeneratesXPath("-0 ", gt0XPathExpression);
        assertGtArgumentGeneratesXPath("  +0   ", gt0XPathExpression);
        assertGtArgumentGeneratesXPath("  -0   ", gt0XPathExpression);
    }

    private void assertGtArgumentGeneratesXPath(String gtArgument, String gtXPathExpression) {
        assertPseudoClassOnlySupportsPureXPathRegardlessOfNativeSupport(gt(gtArgument), GT_PSEUDO, gtXPathExpression);
    }

    @Test
    public void toElementFinder__gt_1__only_generates_XPath_regardless_of_native_support() {
        String gt1XPathExpression = "(.//*)[position() > 2]";
        assertGtArgumentGeneratesXPath("1", gt1XPathExpression);
        assertGtArgumentGeneratesXPath("+1", gt1XPathExpression);
        assertGtArgumentGeneratesXPath("  +1", gt1XPathExpression);
        assertGtArgumentGeneratesXPath("+1  ", gt1XPathExpression);
        assertGtArgumentGeneratesXPath("      +1     ", gt1XPathExpression);
    }

    @Test
    public void toElementFinder__gt_2_NEGATIVE__only_generates_XPath_regardless_of_native_support() {
        String gtNegative2XPathExpression = "(.//*)[position() > (last()-1)]";
        assertGtArgumentGeneratesXPath("-2", gtNegative2XPathExpression);
        assertGtArgumentGeneratesXPath("-2", gtNegative2XPathExpression);
        assertGtArgumentGeneratesXPath("  -2", gtNegative2XPathExpression);
        assertGtArgumentGeneratesXPath("-2  ", gtNegative2XPathExpression);
        assertGtArgumentGeneratesXPath("      -2     ", gtNegative2XPathExpression);
    }

}