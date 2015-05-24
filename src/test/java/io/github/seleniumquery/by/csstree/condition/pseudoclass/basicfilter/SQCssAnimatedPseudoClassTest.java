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

import io.github.seleniumquery.by.css.pseudoclasses.UnsupportedPseudoClassException;
import org.junit.Test;

import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;
import static io.github.seleniumquery.by.locator.ElementFinderUtilsTest.UNIVERSAL_SELECTOR_LOCATOR;

public class SQCssAnimatedPseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":animated", SQCssAnimatedPseudoClass.class);
    }

    @Test(expected = UnsupportedPseudoClassException.class)
    public void toSQLocator__should_just_throw_exception() {
        new SQCssAnimatedPseudoClass().toElementFinder(UNIVERSAL_SELECTOR_LOCATOR);
    }

}