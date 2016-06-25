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

package io.github.seleniumquery.by.firstgen.xpath.component;

import io.github.seleniumquery.by.common.elementfilter.ElementFilterList;
import io.github.seleniumquery.by.firstgen.xpath.component.special.IdConditionComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TagComponent extends XPathComponent implements Combinable<TagComponent> {

    private static final boolean ID_OPTIMIZATION = true;

    public TagComponent(String xPathExpression) {
        super(xPathExpression, ComponentUtils.emptyElementFilterList());
    }

    TagComponent(String xPathExpression, List<XPathComponent> combinatedComponents, ElementFilterList elementFilterList) {
        super("".equals(xPathExpression) ? "*" : xPathExpression, combinatedComponents, elementFilterList);
    }

    public List<WebElement> findWebElements(SearchContext context) {
        if (canUseById()) {
            return findElementsById(context);
        }
        List<WebElement> elements = findElementsByXPath(context);

        return elementFilterList.filter(context, elements);
    }

    private List<WebElement> findElementsById(SearchContext context) {
        return ((IdConditionComponent) this.combinatedComponents.get(0)).findWebElements(context);
    }

    private List<WebElement> findElementsByXPath(SearchContext context) {
        String finalXPathExpression = this.toXPath();
        return new By.ByXPath(finalXPathExpression).findElements(context);
    }

    private boolean canUseById() {
        //noinspection PointlessBooleanExpression,ConstantConditions
        return ID_OPTIMIZATION
                && "*".equals(this.xPathExpression)
                && this.combinatedComponents.size() == 1
                && this.combinatedComponents.get(0) instanceof IdConditionComponent;
    }

    public String toXPath() {
        String xPathExpression;
        if ("*".equals(this.xPathExpression)) {
            xPathExpression = ".//*";
        } else {
            xPathExpression = ".//*[self::" + this.xPathExpression+"]";
        }
        ElementFilterList elementFilterList = this.elementFilterList; // should be a copy

        for (XPathComponent other : combinatedComponents) {
            elementFilterList = other.mergeIntoFilter(elementFilterList);
            xPathExpression = other.mergeIntoExpression(xPathExpression);
        }
        return "(" + xPathExpression + ")";
    }

    public String toXPathCondition() {
        String xPathExpression;
        if ("*".equals(this.xPathExpression)) {
            xPathExpression = MATCH_EVERYTHING_XPATH_CONDITIONAL;
        } else {
            xPathExpression = "local-name() = '"+this.xPathExpression+"'";
        }
        ElementFilterList elementFilterList = this.elementFilterList; // should be a copy

        for (XPathComponent other : combinatedComponents) {
            elementFilterList = other.mergeFilterAsCondition(elementFilterList);
            xPathExpression = other.mergeExpressionAsCondition(xPathExpression);
        }
        return xPathExpression;
    }

    @Override
    public String mergeIntoExpression(String sourceXPathExpression) {
        if ("*".equals(this.xPathExpression)) {
            return ConditionSimpleComponent.merge(sourceXPathExpression, "["+ MATCH_EVERYTHING_XPATH_CONDITIONAL+"]");
        }
        return ConditionSimpleComponent.merge(sourceXPathExpression, "[self::"+ this.xPathExpression +"]");
    }

    @Override
    public String mergeExpressionAsCondition(String sourceXPathExpression) {
        return mergeIntoExpression(sourceXPathExpression);
    }

    @Override
    public TagComponent cloneComponent() {
        return new TagComponent(this.xPathExpression, this.combinatedComponents, this.elementFilterList);
    }

    @Override
    public TagComponent cloneAndCombineTo(Combinable other) {
        XPathComponent otherCopy = other.cloneComponent();
        return new TagComponent(this.xPathExpression,
                ComponentUtils.joinComponents(this.combinatedComponents, otherCopy),
                ComponentUtils.joinFilters(this.elementFilterList, otherCopy));
    }

}