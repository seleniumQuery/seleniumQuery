/*
 * Copyright (c) 2017 seleniumQuery authors
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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import io.github.seleniumquery.by.secondgen.csstree.condition.CssConditionImplementedFinders;
import io.github.seleniumquery.by.secondgen.finder.ElementFinder;

public class CssFunctionalPseudoClassConditionTest {

    class CssFunctionalPseudoClassConditionSubClass extends CssFunctionalPseudoClassCondition<String> {
        CssFunctionalPseudoClassConditionSubClass(String argument) { super(argument); }
        @Override public void accept(AstCssPseudoClassConditionVisitor visitor) { }
    }

    @Test
    public void getArgument() {
        AstCssFunctionalPseudoClassCondition<String> functionalPseudoClassCondition = new CssFunctionalPseudoClassConditionSubClass("1");
        String argument = functionalPseudoClassCondition.getArgument();
        assertThat(argument, is("1"));
    }

    @Test
    public void toElementFinder__should_call_toElementFinder_in_object_returned_from_strategy_method() {
        final ElementFinder argFinder = new ElementFinder((WebDriver) null, null, null);
        final ElementFinder returningFinder = new ElementFinder((WebDriver) null, null, null);
        final CssConditionImplementedFinders finderGen = leftFinder -> {
            if (argFinder.equals(leftFinder)) {
                return returningFinder;
            }
            return null;
        };
        CssFunctionalPseudoClassCondition<String> functionalPseudoClassCondition = new CssFunctionalPseudoClassConditionSubClass("1") {
            @Override
            public CssConditionImplementedFinders getElementFinderFactoryStrategy() {
                return finderGen;
            }
        };

        ElementFinder returnedFinder = functionalPseudoClassCondition.toElementFinder(argFinder);
        assertThat(returnedFinder, is(returningFinder));
    }

}
