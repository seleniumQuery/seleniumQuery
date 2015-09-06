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

package io.github.seleniumquery.by2.csstree.condition.pseudoclass.seleniumquery;

import org.junit.Test;

import static io.github.seleniumquery.by2.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils.assertPseudoSupportsBothPureCssAndPureXPathWhenNativelySupported;
import static io.github.seleniumquery.by2.csstree.condition.pseudoclass.PseudoClassTestUtils.assertQueriesOnSelector;

public class SQCssPresentPseudoClassTest {

    private static final String PRESENT_PSEUDO = ":present";
    private static final String PRESENT_CSS_SELECTOR = "*";
    private static final String PRESENT_XPATH_EXPRESSION = ".//*[true()]";

    @Test
    public void translate() {
        assertQueriesOnSelector(PRESENT_PSEUDO).yieldPseudoClass(SQCssPresentPseudoClass.class);
    }

    @Test
    public void toElementFinder__when_driver_has_native_support() {
        assertPseudoSupportsBothPureCssAndPureXPathWhenNativelySupported(
                new SQCssPresentPseudoClass(),
                PRESENT_CSS_SELECTOR,
                PRESENT_XPATH_EXPRESSION
        );
    }

}