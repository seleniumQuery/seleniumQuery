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
 * If not, will use XPath. If the XPath expression is known NOT able of bringing all elements, then
 * the locator also filters the results found by XPath.
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
    private SQLocatorCss sqLocatorCss;
    private SQLocatorXPath sqLocatorXPath;

    public SQLocator(WebDriver webDriver, SQLocatorCss sqLocatorCss, SQLocatorXPath sqLocatorXPath) {
        this.webDriver = webDriver;
        this.sqLocatorCss = sqLocatorCss;
        this.sqLocatorXPath = sqLocatorXPath;
    }

    public SQLocator(SQLocatorCss newCssSelector, String newXPathExpression, SQLocator previous) {
        this(previous.webDriver, newCssSelector, previous.getSQLocatorXPath().newXPathExpressionKeepingEverythingElse(newXPathExpression));
    }

    public List<WebElement> findWebElements(SearchContext context) {
        if (canFetchThroughCssAlone()) {
            return sqLocatorCss.findElements(context);
        }
        return sqLocatorXPath.findElements(context);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public SQLocatorCss getSqLocatorCss() {
        return sqLocatorCss;
    }

    public SQLocatorXPath getSQLocatorXPath() {
        return sqLocatorXPath;
    }

    public String getXPathExpression() {
        return sqLocatorXPath.getXPathExpression();
    }

    public boolean canFetchThroughCssAlone() {
        return sqLocatorCss.canFetchAllElementsOfTheQueryByItself();
    }

    public ElementFilterList getElementFilterList() {
        return sqLocatorXPath.getElementFilterList();
    }

}