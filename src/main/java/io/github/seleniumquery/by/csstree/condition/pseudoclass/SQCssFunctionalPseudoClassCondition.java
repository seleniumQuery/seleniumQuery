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

package io.github.seleniumquery.by.csstree.condition.pseudoclass;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.csstree.condition.SQCssConditionImplementedLocators;
import io.github.seleniumquery.by.locator.SQLocator;

public class SQCssFunctionalPseudoClassCondition extends SQCssPseudoClassCondition implements SQCssConditionImplementedLocators {

    protected PseudoClassSelector pseudoClassSelector;
    protected String argument;

    public SQCssFunctionalPseudoClassCondition(PseudoClassSelector pseudoClassSelector) {
        this.pseudoClassSelector = pseudoClassSelector;
        this.argument = pseudoClassSelector.getPseudoClassContent();
    }

    public String getArgument() {
        return argument;
    }

    @Override
    public SQLocator toSQLocator(SQLocator leftLocator) {
        return getSQCssLocatorGenerationStrategy().toSQLocator(leftLocator);
    }

    public SQCssConditionImplementedLocators getSQCssLocatorGenerationStrategy() {
        throw new RuntimeException("This method will be abstract. It is not yet because I want the project " +
                "to compile while I'm implementing everyone.");
    }

}