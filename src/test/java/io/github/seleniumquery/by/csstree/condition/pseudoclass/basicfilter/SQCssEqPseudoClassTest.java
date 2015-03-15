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

import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassAssertLocatorUtils.assertPseudoClassOnlySupportsPureXPathRegardlessOfNativeSupport;
import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.assertFunctionalPseudo;
import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.createPseudoClassSelectorAppliedToUniversalSelector;

public class SQCssEqPseudoClassTest {

    public static final String EQ_PSEUDO = ":eq";

    @Test
    public void translate() {
        assertFunctionalPseudo(EQ_PSEUDO, SQCssEqPseudoClass.class);
    }

    @Test
    public void toSQLocator__when_driver_does_NOT_have_native_support() {
        // *:eq(0)
        final SQCssEqPseudoClass eq0 = new SQCssEqPseudoClass(createPseudoClassSelectorAppliedToUniversalSelector("0"));
        final String eq0XPathExpression = "(.//*)[position() = 0]";

        assertPseudoClassOnlySupportsPureXPathRegardlessOfNativeSupport(
                eq0,
                EQ_PSEUDO,
                eq0XPathExpression
        );
    }

}