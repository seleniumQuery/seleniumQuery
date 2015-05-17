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

package io.github.seleniumquery.by.csstree.selector;

import io.github.seleniumquery.by.csstree.condition.SQCssCondition;
import io.github.seleniumquery.by.csstree.condition.SQCssConditionImplementedLocators;
import io.github.seleniumquery.by.locator.ElementFinder;
import org.openqa.selenium.WebDriver;

/**
 * Conditional selector, simply an union of a selector and a condition.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssConditionalSelector implements SQCssSelector {

    private final SQCssSelector sqCssSelector;
    private final SQCssCondition sqCssCondition;

    public SQCssConditionalSelector(SQCssSelector sqCssSelector, SQCssCondition sqCssCondition) {
        this.sqCssSelector = sqCssSelector;
        this.sqCssCondition = sqCssCondition;
    }

    public SQCssSelector getSqCssSelector() {
        return sqCssSelector;
    }

    public SQCssCondition getSqCssCondition() {
        return sqCssCondition;
    }

    @Override
    public ElementFinder toSQLocator(WebDriver webDriver) {
        ElementFinder locator = sqCssSelector.toSQLocator(webDriver);
        return ((SQCssConditionImplementedLocators) sqCssCondition).toSQLocator(locator);
    }

    @Override
    public ElementFinder toSQLocator(ElementFinder leftLocator) {
        ElementFinder locator = sqCssSelector.toSQLocator(leftLocator);
        return ((SQCssConditionImplementedLocators) sqCssCondition).toSQLocator(locator);
    }

}