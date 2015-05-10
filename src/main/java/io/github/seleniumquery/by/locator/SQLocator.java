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

import io.github.seleniumquery.by.filter.ElementFilterList;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Cabaple of locating {@link WebElement}s on a given {@link SearchContext}.
 *
 * Attempts to find the elements by CSS if driver/browser supports.
 * If it doesn't, will use XPath.
 *
 * If the XPath expression alone is not able to bring the exact needed elements, then
 * the locator also filters (iteratively) the results.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQLocator {

    /**
     * The driver here is used to test for selector support. This is *not* the {@link SearchContext}
     * the elements will be searched on.
     */
    private WebDriver webDriver;
    private CSSLocator cssLocator;
    private XPathLocator xPathLocator;

    public SQLocator(WebDriver webDriver, CSSLocator cssLocator, XPathLocator xPathLocator) {
        this.webDriver = webDriver;
        this.cssLocator = cssLocator;
        this.xPathLocator = xPathLocator;
    }

    public SQLocator(CSSLocator newCssSelector, String newXPathExpression, SQLocator previous) {
        this(previous.webDriver, newCssSelector, previous.getXPathLocator().newXPathExpressionKeepingEverythingElse(newXPathExpression));
    }

    public List<WebElement> findWebElements(SearchContext context) {
        if (canFetchThroughCssAlone()) {
            return cssLocator.findElements(context);
        }
        return xPathLocator.findElements(context);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public CSSLocator getCSSLocator() {
        return cssLocator;
    }

    public XPathLocator getXPathLocator() {
        return xPathLocator;
    }

    public String getXPathExpression() {
        return xPathLocator.getXPathExpression();
    }

    public boolean canFetchThroughCssAlone() {
        return cssLocator.canFetchAllElementsOfTheQueryByItself();
    }

    public ElementFilterList getElementFilterList() {
        return xPathLocator.getElementFilterList();
    }

}