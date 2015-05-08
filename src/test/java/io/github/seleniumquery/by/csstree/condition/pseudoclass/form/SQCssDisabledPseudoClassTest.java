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

package io.github.seleniumquery.by.csstree.condition.pseudoclass.form;

import org.junit.Test;

import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils.assertPseudoSupportsBothPureCssAndPureXPathWhenNativelySupported;
import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils.assertPseudoSupportsPureXPathWhenNotNativelySupported;
import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.assertQueriesOnSelector;

public class SQCssDisabledPseudoClassTest {

    public static final String DISABLED_PSEUDO = ":disabled";
    public static final String DISABLED_XPATH_EXPRESSION = ".//*[(" +
            "@disabled and (self::input or self::button or self::optgroup or self::option or self::select or self::textarea)" +
            ")]";

    @Test
    public void translate() {
        assertQueriesOnSelector(DISABLED_PSEUDO).yieldPseudoClass(SQCssDisabledPseudoClass.class);
    }

    @Test
    public void toElementFinder__when_driver_has_native_support() {
        assertPseudoSupportsBothPureCssAndPureXPathWhenNativelySupported(
                new SQCssDisabledPseudoClass(),
                DISABLED_PSEUDO,
                DISABLED_XPATH_EXPRESSION
        );
    }

    @Test
    public void toElementFinder__when_driver_does_NOT_have_native_support() {
        assertPseudoSupportsPureXPathWhenNotNativelySupported(
                new SQCssDisabledPseudoClass(),
                DISABLED_XPATH_EXPRESSION
        );
    }

}