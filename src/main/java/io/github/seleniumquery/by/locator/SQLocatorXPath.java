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

import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.filter.ElementFilterList;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * If the ElementFilterList is empty, then it can fetch everything it needs through XPath alone.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQLocatorXPath {

    public static SQLocatorXPath pureXPath(String xPathExpression) {
        return new SQLocatorXPath(xPathExpression, ElementFilterList.FILTER_NOTHING_LIST);
    }

    public static SQLocatorXPath filterOnly(ElementFilter elementFilter) {
        return new SQLocatorXPath("true()", elementFilter);
    }

    private String xPathExpression;

    private ElementFilterList elementFilterList;

    public SQLocatorXPath(String xPathExpression, ElementFilter elementFilter) {
        this.xPathExpression = xPathExpression;
        if (elementFilter == ElementFilter.FILTER_NOTHING) {
            this.elementFilterList = ElementFilterList.FILTER_NOTHING_LIST;
        } else {
            this.elementFilterList = ElementFilterList.asFilterList(elementFilter);
        }
    }

    public SQLocatorXPath(String xPathExpression, ElementFilterList elementFilterList) {
        this.xPathExpression = xPathExpression;
        this.elementFilterList = elementFilterList;
    }

    public String getXPathExpression() {
        return xPathExpression;
    }

    public ElementFilterList getElementFilterList() {
        return elementFilterList;
    }

    public SQLocatorXPath newXPathExpressionKeepingEverythingElse(String newXPathExpression) {
        return new SQLocatorXPath(newXPathExpression, this.getElementFilterList());
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

}