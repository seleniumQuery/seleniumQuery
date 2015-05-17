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

package io.github.seleniumquery.by.csstree.condition.attribute;

import io.github.seleniumquery.by.csstree.condition.SQCssCondition;
import io.github.seleniumquery.by.csstree.condition.SQCssConditionImplementedLocators;
import io.github.seleniumquery.by.locator.CSSLocator;
import io.github.seleniumquery.by.locator.ElementFinder;
import io.github.seleniumquery.by.locator.SQLocatorUtils;

/**
 * A class that holds an attribute name and a wanted value.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public abstract class SQCssAttributeCondition implements SQCssCondition, SQCssConditionImplementedLocators {

    protected String attributeName;
    protected String wantedValue;

    protected SQCssAttributeCondition(String attributeName, String wantedValue) {
        this.attributeName = attributeName;
        this.wantedValue = wantedValue;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getWantedValue() {
        return wantedValue;
    }

    @Override
    public ElementFinder toSQLocator(ElementFinder leftLocator) {
        CSSLocator newCssSelector = leftLocator.getCSSLocator().merge(toCSS());
        String newXPathExpression = SQLocatorUtils.conditionalSimpleXPathMerge(leftLocator.getXPathExpression(), toXPath());
        return new ElementFinder(newCssSelector, newXPathExpression, leftLocator);
    }

    protected abstract CSSLocator toCSS();

    protected abstract String toXPath();

}