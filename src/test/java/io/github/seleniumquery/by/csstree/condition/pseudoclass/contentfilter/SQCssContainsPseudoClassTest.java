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

package io.github.seleniumquery.by.csstree.condition.pseudoclass.contentfilter;

import org.junit.Test;

import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils.AssertPseudoClass.assertPseudoClass;
import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.assertQueriesOnSelector;
import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.createPseudoClassSelectorAppliedToUniversalSelector;

public class SQCssContainsPseudoClassTest {

    private static final String CONTAINS_PSEUDO = ":contains";
    public static final String CONTAINS_XPATH_EXPRESSION = ".//*[" +
            "contains(string(.), 'my stuff')" +
        "]";

    @Test
    public void translate() {
        assertQueriesOnSelector(CONTAINS_PSEUDO).withAllKindsOfArguments().yieldFunctionalPseudoclassWithCorrectlyTranslatedArguments(SQCssContainsPseudoClass.class);
    }

    @Test
    public void toElementFinder__REGARDLESS_of_driver_native_support() {
        SQCssContainsPseudoClass containsPseudoClass = new SQCssContainsPseudoClass(createPseudoClassSelectorAppliedToUniversalSelector("my stuff"));
        assertPseudoClass(containsPseudoClass).whenNotNativelySupported().translatesToPureXPath(CONTAINS_XPATH_EXPRESSION);
    }

}