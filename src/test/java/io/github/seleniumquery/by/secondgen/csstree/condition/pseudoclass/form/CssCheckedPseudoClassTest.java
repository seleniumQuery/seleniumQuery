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

package io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form;

import endtoend.crossdriver.driverbugs.PhantomJSCheckedSelectorBugTest;
import io.github.seleniumquery.by.firstgen.css.pseudoclasses.CheckedPseudoClass;
import io.github.seleniumquery.by.secondgen.finder.ElementFinder;
import io.github.seleniumquery.by.secondgen.finder.ElementFinderUtilsTest;
import org.junit.Test;

import static io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils.*;
import static io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassTestUtils.assertQueriesOnSelector;
import static io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssInputTypeAttributePseudoClassTest.TYPE_ATTR_LOWER_CASE;
import static io.github.seleniumquery.by.secondgen.finder.ElementFinderUtilsTest.createWebDriverEmulatingPhantomJSAndWithNativeSupporForPseudo;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class CssCheckedPseudoClassTest {

    private static final String CHECKED_PSEUDO = ":checked";
    private static final String CHECKED_XPATH_EXPRESSION = ".//*[" +
            "((self::input and ("+ TYPE_ATTR_LOWER_CASE+" = 'radio' or "+TYPE_ATTR_LOWER_CASE+" = 'checkbox')) or self::option)" +
            "]";

    @Test
    public void translate() {
        assertQueriesOnSelector(CHECKED_PSEUDO).yieldPseudoClass(CssCheckedPseudoClass.class);
    }

    @Test
    public void toElementFinder__when_driver_has_native_support() {
        assertPseudoSupportsOnlyPureCssAndNotPureXPathWhenNativelySupported(
                new CssCheckedPseudoClass(),
                CHECKED_PSEUDO,
                CHECKED_XPATH_EXPRESSION,
                CheckedPseudoClass.CHECKED_FILTER
        );
    }

    @Test
    public void toElementFinder__when_driver_does_NOT_have_native_support() {
        assertPseudoClassDoesNotSupportAnythingPurelyWhenNotNativelySupported(
                new CssCheckedPseudoClass(),
                CHECKED_XPATH_EXPRESSION,
                CheckedPseudoClass.CHECKED_FILTER
        );
    }

    /**
     * #Cross-Driver
     * PhantomJSDriver's :checked has bugs!
     * See: {@link PhantomJSCheckedSelectorBugTest}
     */
    @Test
    public void toElementFinder__when_driver_is_PHANTOMJSDRIVER_it_behaves_like_it_does_NOT_have_native_support() {
        ElementFinder previousFinder = ElementFinderUtilsTest.universalSelectorFinder(createWebDriverEmulatingPhantomJSAndWithNativeSupporForPseudo(CHECKED_PSEUDO));
        assertPseudoClassHasFinder(
                new CssCheckedPseudoClass(),
                previousFinder,
                CSS_UNIVERSAL_SELECTOR,
                PURE_CSS_IS_NOT_SUPPORTED,
                CHECKED_XPATH_EXPRESSION,
                contains(CheckedPseudoClass.CHECKED_FILTER)
        );
    }

}
