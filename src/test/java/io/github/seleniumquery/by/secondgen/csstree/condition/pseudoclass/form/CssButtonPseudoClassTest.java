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

package io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form;

import org.junit.Test;

import static io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils.AssertPseudoClass.assertPseudoClass;
import static io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassTestUtils.assertQueriesOnSelector;
import static io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssInputTypeAttributePseudoClassTest.TYPE_ATTR_LOWER_CASE;

public class CssButtonPseudoClassTest {

    private static final String BUTTON_PSEUDO = ":button";
    private static final String BUTTON_XPATH_EXPRESSION =
            ".//*[(" +
                    "(self::input and "+TYPE_ATTR_LOWER_CASE+" = 'button') or self::button" +
                ")]";

    @Test
    public void translate() {
        assertQueriesOnSelector(BUTTON_PSEUDO).yieldPseudoClass(CssButtonPseudoClass.class);
    }

    @Test
    public void toElementFinder__when_driver_does_NOT_have_native_support() {
        assertPseudoClass(new CssButtonPseudoClass()).whenNotNativelySupported().translatesToPureXPath(BUTTON_XPATH_EXPRESSION);
    }

}
