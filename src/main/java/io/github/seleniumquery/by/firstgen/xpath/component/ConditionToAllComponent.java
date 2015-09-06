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

package io.github.seleniumquery.by.firstgen.xpath.component;

import io.github.seleniumquery.by.common.elementfilter.ElementFilterList;

import java.util.List;

/**
 * Represents a condition that must be applied to all the expression, not just appended (using AND) to it.
 *
 * This must be applied to the whole result of the other, such as:
 * (//*[@other])[@thisSelector]
 */
public class ConditionToAllComponent extends ConditionComponent {

    public ConditionToAllComponent(String xPathExpression) {
        super(xPathExpression, ComponentUtils.emptyElementFilterList());
    }

    ConditionToAllComponent(String xPathExpression, List<XPathComponent> combinatedComponents, ElementFilterList elementFilterList) {
        super(xPathExpression, combinatedComponents, elementFilterList);
    }

    @Override
    public String mergeIntoExpression(String sourceXPathExpression) {
        return "(" + sourceXPathExpression + ")" + this.xPathExpression;
    }

    /**
     * // TODO the code below seems identical to
     * {@link io.github.seleniumquery.by.firstgen.xpath.component.ConditionSimpleComponent#mergeAsCondition(String, String)}.
     * Is it??
     */
    @Override
    public String mergeExpressionAsCondition(String sourceXPathExpression) {
        if (sourceXPathExpression.equals(MATCH_EVERYTHING_XPATH_CONDITIONAL)) {
            return ComponentUtils.removeBraces(this.xPathExpression);
        }
        return sourceXPathExpression + " and " + ComponentUtils.removeBraces(this.xPathExpression);
    }

    @Override
    public ConditionToAllComponent cloneComponent() {
        return new ConditionToAllComponent(this.xPathExpression, this.combinatedComponents, this.elementFilterList);
    }

    @Override
    public ConditionToAllComponent cloneAndCombineTo(Combinable other) {
        XPathComponent otherCopy = other.cloneComponent();
        return new ConditionToAllComponent(this.xPathExpression,
                ComponentUtils.joinComponents(this.combinatedComponents, otherCopy),
                ComponentUtils.joinFilters(this.elementFilterList, otherCopy));
    }

}