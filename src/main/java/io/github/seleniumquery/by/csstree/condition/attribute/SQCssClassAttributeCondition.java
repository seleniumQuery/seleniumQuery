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
import io.github.seleniumquery.by.locator.SQLocator;
import io.github.seleniumquery.by.locator.SQLocatorCss;
import io.github.seleniumquery.by.locator.SQLocatorUtils;

/**
 * .class
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssClassAttributeCondition implements SQCssCondition, SQCssConditionImplementedLocators {

    private String unescapedClassName;

    public SQCssClassAttributeCondition(String unescapedClassName) {
        this.unescapedClassName = unescapedClassName;
    }

    public String getClassName() {
        return unescapedClassName;
    }

    @Override
    public SQLocator toSQLocator(SQLocator leftLocator) {
        SQLocatorCss newCssSelector = leftLocator.getSQCssSelector().mergeUsingCurrentNativeness(toCSS());
        String newXPathExpression = SQLocatorUtils.conditionalSimpleXPathMerge(leftLocator.getXPathExpression(), toXPath());
        return new SQLocator(newCssSelector, newXPathExpression, leftLocator);
    }

    private SQLocatorCss toCSS() {
        return new SQLocatorCss("." + this.unescapedClassName, SQLocatorCss.CanFetchAllElementsOfTheQueryByItself.YES);
    }

    private String toXPath() {
        return "contains(concat(' ', normalize-space(@class), ' '), ' " + unescapedClassName + " ')";
    }

}