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

package io.github.seleniumquery.by.locator;

import io.github.seleniumquery.by.csstree.condition.pseudoclass.locatorgenerationstrategy.XPathMergeStrategy;
import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.filter.ElementFilterList;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Represents the necessary details (XPath expression + Filter) to find elements by not using CSS.
 *
 * We say "by not using CSS" and not "by XPath" because the XPath expression here may be very simple, with
 * all the selection being made by the filter.
 *
 * Important: If the ElementFilterList is empty, then it can fetch everything it needs through XPath alone (as
 * the filter will filter nothing).
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class XPathLocator {

    public static XPathLocator pureXPath(String xPathExpression) {
        return new XPathLocator(xPathExpression, ElementFilterList.FILTER_NOTHING_LIST);
    }

    public static XPathLocator filterOnly(ElementFilter elementFilter) {
        return new XPathLocator("true()", elementFilter);
    }

    private String xPathExpression;

    private ElementFilterList elementFilterList;

    public XPathLocator(String xPathExpression, ElementFilter elementFilter) {
        this.xPathExpression = xPathExpression;
        if (elementFilter == ElementFilter.FILTER_NOTHING) {
            this.elementFilterList = ElementFilterList.FILTER_NOTHING_LIST;
        } else {
            this.elementFilterList = ElementFilterList.asFilterList(elementFilter);
        }
    }

    public XPathLocator(String xPathExpression, ElementFilterList elementFilterList) {
        this.xPathExpression = xPathExpression;
        this.elementFilterList = elementFilterList;
    }

    public String getXPathExpression() {
        return xPathExpression;
    }

    public ElementFilterList getElementFilterList() {
        return elementFilterList;
    }

    public XPathLocator newXPathExpressionKeepingEverythingElse(String newXPathExpression) {
        return new XPathLocator(newXPathExpression, this.getElementFilterList());
    }

    // TODO dont know if this has unit tests
    public List<WebElement> findElements(SearchContext context) {
        List<WebElement> elementsByXPath = findElementsByXPath(context);
        return this.getElementFilterList().filter(context, elementsByXPath);
    }

    private List<WebElement> findElementsByXPath(SearchContext context) {
        String finalXPathExpression = this.getFinalXPathExpression();
        return new By.ByXPath(finalXPathExpression).findElements(context);
    }

    public String getFinalXPathExpression() {
        return "(" + this.xPathExpression + ")";
    }

    public XPathLocator merge(XPathLocator rightXPath, XPathMergeStrategy xPathMergeStrategy) {
        return new XPathLocator(
            xPathMergeStrategy.mergeXPath(this.getXPathExpression(), rightXPath.getXPathExpression()),
            this.getElementFilterList().merge(rightXPath.getElementFilterList())
        );
    }

}