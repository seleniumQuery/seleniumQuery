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

package io.github.seleniumquery.by.secondgen.finder;

import io.github.seleniumquery.by.common.elementfilter.ElementFilterList;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * An {@link ElementFinder} is an object that is cabaple of finding {@link WebElement}s on a
 * given {@link SearchContext}.<br><br>
 *
 * Internally, when trying to find the elements:<br>
 * <ul>
 *     <li>it first attempts to find them by CSS if driver/browser supports.</li>
 *     <li>If it doesn't, will use XPath.
 *         <ul>
 *             <li>If the XPath expression alone is not able to bring the exact wanted elements, then the finder
 *             also filters (though iteration) the results brought by the XPath.</li>
 *         </ul>
 *     </li>
 * </ul>
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
    private CssFinder cssFinder;
    private XPathAndFilterFinder xPathAndFilterFinder;

    public ElementFinder(WebDriver webDriver, CssFinder cssFinder, XPathAndFilterFinder xPathAndFilterFinder) {
        this.webDriver = webDriver;
        this.cssFinder = cssFinder;
        this.xPathAndFilterFinder = xPathAndFilterFinder;
    }

    public ElementFinder(CssFinder newCssSelector, String newXPathExpression, ElementFinder previous) {
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

    public CssFinder getCssFinder() {
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

    public String toCssString() {
        return cssFinder.toString();
    }

}
