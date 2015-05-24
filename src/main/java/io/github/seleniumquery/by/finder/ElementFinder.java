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

import io.github.seleniumquery.by.filter.ElementFilterList;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Cabaple of finding {@link WebElement}s on a given {@link SearchContext}.<br><br>
 *
 * Attempts to find the elements by CSS if driver/browser supports.<br>
 * If it doesn't, will use XPath.<br><br>
 *
 * If the XPath expression alone is not able to bring the exact wanted elements, then
 * the finder also filters (though iteration) the results brought by the XPath.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class ElementFinder {

    /**
     * The driver here is used to test for selector support. This is *not* the {@link SearchContext}
     * the elements will be searched on.
     */
    private WebDriver webDriver;
    private CSSFinder cssFinder;
    private XPathAndFilterFinder xPathAndFilterFinder;

    public ElementFinder(WebDriver webDriver, CSSFinder cssFinder, XPathAndFilterFinder xPathAndFilterFinder) {
        this.webDriver = webDriver;
        this.cssFinder = cssFinder;
        this.xPathAndFilterFinder = xPathAndFilterFinder;
    }

    public ElementFinder(CSSFinder newCssSelector, String newXPathExpression, ElementFinder previous) {
        this(previous.webDriver, newCssSelector, previous.getXPathAndFilterFinder().newXPathExpressionKeepingEverythingElse(newXPathExpression));
    }

    public List<WebElement> findWebElements(SearchContext context) {
        if (canFetchThroughCssAlone()) {
            return cssFinder.findElements(context);
        }
        return xPathAndFilterFinder.findElements(context);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public CSSFinder getCssFinder() {
        return cssFinder;
    }

    public XPathAndFilterFinder getXPathAndFilterFinder() {
        return xPathAndFilterFinder;
    }

    public String getXPathExpression() {
        return xPathAndFilterFinder.getXPathExpression();
    }

    public boolean canFetchThroughCssAlone() {
        return cssFinder.canFetchAllElementsOfTheQueryByItself();
    }

    public ElementFilterList getElementFilterList() {
        return xPathAndFilterFinder.getElementFilterList();
    }

}