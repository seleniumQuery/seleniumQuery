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

package io.github.seleniumquery.by.csstree.condition.pseudoclass.childfilter;

import io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils;
import org.junit.Test;

import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils.assertPseudoClassOnlySupportsPureXPathRegardlessOfNativeSupport;
import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;

public class SQCssFirstChildPseudoClassTest {

    public static final String FIRST_CHILD_PSEUDO = ":first-child";
    public static final String FIRST_CHILD_XPATH_EXPRESSION = ".//*[position() = 1]";

    @Test
    public void translate() {
        assertPseudo(FIRST_CHILD_PSEUDO, SQCssFirstChildPseudoClass.class);
    }

    @Test
    public void toElementFinder__when_driver_does_NOT_have_native_support() {
        assertPseudoClassOnlySupportsPureXPathRegardlessOfNativeSupport(
                new SQCssFirstChildPseudoClass(PseudoClassTestUtils.EMPTY),
                FIRST_CHILD_PSEUDO,
                FIRST_CHILD_XPATH_EXPRESSION
        );
    }

}