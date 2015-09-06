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

package io.github.seleniumquery.by2.csstree.condition.pseudoclass.visibility;

import io.github.seleniumquery.by.css.pseudoclasses.HiddenPseudoClass;
import org.junit.Test;

import static io.github.seleniumquery.by2.csstree.condition.pseudoclass.PseudoClassTestUtils.assertFilterOnlyPseudoGeneratesFilter;
import static io.github.seleniumquery.by2.csstree.condition.pseudoclass.PseudoClassTestUtils.assertQueriesOnSelector;

public class SQCssHiddenPseudoClassTest {

    @Test
    public void translate() {
        assertQueriesOnSelector(":hidden").yieldPseudoClass(SQCssHiddenPseudoClass.class);
    }

    @Test
    public void toElementFinder__no_browser_has_native_support() {
        assertFilterOnlyPseudoGeneratesFilter(new SQCssHiddenPseudoClass(), HiddenPseudoClass.HIDDEN_FILTER);
    }

}