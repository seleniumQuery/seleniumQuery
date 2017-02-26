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
import io.github.seleniumquery.by.firstgen.css.pseudoclasses.SelectedPseudoClass;
import io.github.seleniumquery.by.secondgen.finder.ElementFinder;
import io.github.seleniumquery.by.secondgen.finder.ElementFinderUtilsTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils.*;
import static io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassTestUtils.assertQueriesOnSelector;
import static io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.SQCssInputTypeAttributePseudoClassTest.TYPE_ATTR_LOWER_CASE;
import static io.github.seleniumquery.by.secondgen.finder.ElementFinderUtilsTest.createWebDriverEmulatingPhantomJSAndWithNativeSupporForPseudo;
import static io.github.seleniumquery.by.secondgen.finder.ElementFinderUtilsTest.universalSelectorFinder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class SQCssSelectedPseudoClassTest {

    private static final String SELECTED_PSEUDO = ":selected";
    private static final String SELECTED_XPATH_EXPRESSION = ".//*[self::option]";

    private static final String CHECKED_AND_SELECTED_XPATH_EXPRESSION = ".//*[" +
            "((self::input and ("+ TYPE_ATTR_LOWER_CASE +" = 'radio' or "+ TYPE_ATTR_LOWER_CASE +" = 'checkbox')) or self::option) and self::option" +
            "]";

    private static final String CHECKED_PSEUDO = ":checked";

    @Test
    public void translate() {
        assertQueriesOnSelector(SELECTED_PSEUDO).yieldPseudoClass(CssSelectedPseudoClass.class);
    }

    @Test
    public void toElementFinder__when_driver_has_native_support() {
        // supports pure CSS, but it is a translated one
        ElementFinder previousFinder = universalSelectorFinder(ElementFinderUtilsTest.createWebDriverWithNativeSupportForPseudo(CHECKED_PSEUDO));
        assertPseudoClassHasFinder(
                new CssSelectedPseudoClass(),
                previousFinder,
                "option:checked",
                PURE_CSS_IS_SUPPORTED,
                SELECTED_XPATH_EXPRESSION,
                contains(SelectedPseudoClass.SELECTED_FILTER)
        );
    }

    @Test
    public void toElementFinder__when_driver_does_NOT_have_native_support() {
        assertPseudoClassDoesNotSupportAnythingPurelyWhenNotNativelySupported(
                new CssSelectedPseudoClass(),
                SELECTED_XPATH_EXPRESSION,
                SelectedPseudoClass.SELECTED_FILTER
        );
    }

    @Test
    public void toElementFinder__when_driver_has_native_supportx() {
        // supports pure CSS, but it is a translated one
        WebDriver mockDriverWithNativeSupportForChecked = ElementFinderUtilsTest.createWebDriverWithNativeSupportForPseudo(CHECKED_PSEUDO);
        ElementFinder finderAfterChecked = new CssCheckedPseudoClass().toElementFinder(universalSelectorFinder(mockDriverWithNativeSupportForChecked));

        assertPseudoClassHasFinder(
                new CssSelectedPseudoClass(),
                finderAfterChecked,
                "option:checked:checked",
                PURE_CSS_IS_SUPPORTED,
                CHECKED_AND_SELECTED_XPATH_EXPRESSION,
                contains(CheckedPseudoClass.CHECKED_FILTER, SelectedPseudoClass.SELECTED_FILTER)
        );
    }

    /**
     * #Cross-Driver
     * PhantomJSDriver's :checked has bugs!
     * See: {@link PhantomJSCheckedSelectorBugTest}
     */
    @Test
    public void toElementFinder__when_driver_is_PHANTOMJSDRIVER_it_behaves_like_it_does_NOT_have_native_support() {
        WebDriver driver = createWebDriverEmulatingPhantomJSAndWithNativeSupporForPseudo(CHECKED_PSEUDO);
        ElementFinder previousFinder = universalSelectorFinder(driver);
        assertPseudoClassHasFinder(
                new CssSelectedPseudoClass(),
                previousFinder,
                CSS_UNIVERSAL_SELECTOR,
                PURE_CSS_IS_NOT_SUPPORTED,
                SELECTED_XPATH_EXPRESSION,
                contains(SelectedPseudoClass.SELECTED_FILTER)
        );
    }

}
