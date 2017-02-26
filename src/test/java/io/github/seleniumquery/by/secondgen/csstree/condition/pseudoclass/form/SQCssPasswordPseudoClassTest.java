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

import static io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils.assertPseudoSupportsDifferentButPureCssAndPureXPathRegardlessOfNativeSupport;
import static io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassTestUtils.assertQueriesOnSelector;
import static io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.SQCssInputTypeAttributePseudoClassTest.TYPE_ATTR_LOWER_CASE;

public class SQCssPasswordPseudoClassTest {

    private static final String PASSWORD_PSEUDO = ":password";
    private static final String PASSWORD_CSS_SELECTOR = "input[type=\"password\"]";
    private static final String PASSWORD_XPATH_EXPRESSION = ".//*[" + "(self::input and " + TYPE_ATTR_LOWER_CASE + " = 'password')" + "]";

    @Test
    public void translate() {
        assertQueriesOnSelector(PASSWORD_PSEUDO).yieldPseudoClass(CssPasswordPseudoClass.class);
    }

    @Test
    public void toElementFinder__when_driver_does_NOT_have_native_support() {
        assertPseudoSupportsDifferentButPureCssAndPureXPathRegardlessOfNativeSupport(
                new CssPasswordPseudoClass(),
                PASSWORD_PSEUDO,
                PASSWORD_CSS_SELECTOR,
                PASSWORD_XPATH_EXPRESSION
        );
    }

}
