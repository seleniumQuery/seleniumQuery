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

package io.github.seleniumquery.by.finder;

import io.github.seleniumquery.by.csstree.condition.pseudoclass.findergenerationstrategy.XPathMergeStrategy;
import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.filter.ElementFilterList;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Represents the necessary details (XPath expression AND Filter) to find elements by not using CSS.
 *
 * We say "by not using CSS" and not "by XPath" because the XPath expression here may be very simple,
 * with all the selection being made by the filter.
 *
 * Important: If the ElementFilterList is empty, then it can fetch everything it needs through
 * XPath alone (as the filter will filter nothing).
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class XPathAndFilterFinder {

    public static XPathAndFilterFinder pureXPath(String xPathExpression) {
        return new XPathAndFilterFinder(xPathExpression, ElementFilterList.FILTER_NOTHING_LIST);
    }

    public static XPathAndFilterFinder filterOnly(ElementFilter elementFilter) {
        return new XPathAndFilterFinder("true()", elementFilter);
    }

    private final String xPathExpression;
    private final ElementFilterList elementFilterList;

    public XPathAndFilterFinder(String xPathExpression, ElementFilter elementFilter) {
        this.xPathExpression = xPathExpression;
        this.elementFilterList = toElementFilterList(elementFilter);
    }

    private static ElementFilterList toElementFilterList(ElementFilter elementFilter) {
        if (elementFilter == ElementFilter.FILTER_NOTHING) {
            return ElementFilterList.FILTER_NOTHING_LIST;
        } else {
            return ElementFilterList.asFilterList(elementFilter);
        }
    }

    public XPathAndFilterFinder(String xPathExpression, ElementFilterList elementFilterList) {
        this.xPathExpression = xPathExpression;
        this.elementFilterList = elementFilterList;
    }

    public String getXPathExpression() {
        return getRawXPathExpression();
    }

    public String getRawXPathExpression() {
        return xPathExpression;
    }

    public ElementFilterList getElementFilterList() {
        return elementFilterList;
    }

    public XPathAndFilterFinder newXPathExpressionKeepingEverythingElse(String newXPathExpression) {
        return new XPathAndFilterFinder(newXPathExpression, this.getElementFilterList());
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

    public XPathAndFilterFinder merge(XPathAndFilterFinder rightXPath, XPathMergeStrategy xPathMergeStrategy) {
        return new XPathAndFilterFinder(
            xPathMergeStrategy.mergeXPath(this.getXPathExpression(), rightXPath.getXPathExpression()),
            this.getElementFilterList().merge(rightXPath.getElementFilterList())
        );
    }

}