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

package io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass;

import io.github.seleniumquery.by.firstgen.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.secondgen.csstree.condition.SQCssConditionImplementedFinders;
import io.github.seleniumquery.by.secondgen.finder.ElementFinder;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssFunctionalPseudoClassConditionTest {

    @Test
    public void getArgument() throws Exception {
        PseudoClassSelector pseudoClassSelector = PseudoClassTestUtils.createPseudoClassSelectorAppliedToUniversalSelector("1");
        SQCssFunctionalPseudoClassCondition functionalPseudoClassCondition = new SQCssFunctionalPseudoClassCondition(pseudoClassSelector);
        String argument = functionalPseudoClassCondition.getArgument();
        assertThat(argument, is("1"));
    }

    @Test
    public void toElementFinder__should_call_toElementFinder_in_object_returned_from_strategy_method() throws Exception {
        final ElementFinder argFinder = new ElementFinder((WebDriver) null, null, null);
        final ElementFinder returningFinder = new ElementFinder((WebDriver) null, null, null);
        final SQCssConditionImplementedFinders finderGen = new SQCssConditionImplementedFinders() {
            @Override
            public ElementFinder toElementFinder(ElementFinder leftFinder) {
                if (argFinder.equals(leftFinder)) {
                    return returningFinder;
                }
                return null;
            }
        };
        PseudoClassSelector pseudoClassSelector = PseudoClassTestUtils.createPseudoClassSelectorAppliedToUniversalSelector("1");
        SQCssFunctionalPseudoClassCondition functionalPseudoClassCondition = new SQCssFunctionalPseudoClassCondition(pseudoClassSelector) {
            @Override
            public SQCssConditionImplementedFinders getElementFinderFactoryStrategy() {
                return finderGen;
            }
        };

        ElementFinder returnedFinder = functionalPseudoClassCondition.toElementFinder(argFinder);
        assertThat(returnedFinder, is(returningFinder));
    }

}