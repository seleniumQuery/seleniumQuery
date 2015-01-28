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

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.css.attributes.AttributeEvaluatorUtils;
import io.github.seleniumquery.by.csstree.condition.SQCssConditionImplementedLocators;
import io.github.seleniumquery.by.locator.SQLocator;
import io.github.seleniumquery.by.locator.SQLocatorUtils;

/**
 * [simple]
 * [restart="never"]
 *
 * Case INsensitive!
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssEqualsOrHasAttributeCondition extends SQCssAttributeCondition implements SQCssConditionImplementedLocators {

    /**
     * [simple]
     * Attribute value is null in this case.
     */
    public SQCssEqualsOrHasAttributeCondition(String attributeName) {
        super(attributeName, null);
    }

    /**
     * [restart="never"]
     */
    public SQCssEqualsOrHasAttributeCondition(String attributeName, String wantedValue) {
        super(attributeName, wantedValue);
    }

    @Override
    public SQLocator toSQLocator(SQLocator leftLocator) {
        String newCssSelector = SQLocatorUtils.cssMerge(leftLocator.getCssSelector(), toCSS());
        String newXPathExpression = SQLocatorUtils.conditionalSimpleXPathMerge(leftLocator.getXPathExpression(), toXPath());
        return new SQLocator(newCssSelector, newXPathExpression, leftLocator);
    }

    private String toXPath() {
        if (this.wantedValue != null) {
            String escapedWantedValue = SelectorUtils.intoEscapedXPathString(this.wantedValue);
            return AttributeEvaluatorUtils.toXPathAttribute(this.attributeName) + "=" + escapedWantedValue;
        }
        return AttributeEvaluatorUtils.toXPathAttribute(this.attributeName);
    }

    private String toCSS() {
        if (this.wantedValue != null) {
            return "[" + this.attributeName + "=" + this.wantedValue + "]";
        }
        return "[" + this.attributeName + "]";
    }

}