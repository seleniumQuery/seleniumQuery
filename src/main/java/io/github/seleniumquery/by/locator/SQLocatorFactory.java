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

import java.util.ArrayList;
import java.util.List;

/**
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQLocatorFactory {

    // These two methods below may be pointless. They are here since a long time.
    // If you are intending to use them, check them carefully (and write tests for them!)
    public static SQLocator createPureXPathOnly(SQLocator locator, String newXPathExpression) {
        return new SQLocator(locator.getWebDriver(), SQLocatorCss.CSS_NOT_NATIVELY_SUPPORTED,
                new SQLocatorXPath(newXPathExpression, locator.getElementFilterList())
        );
    }

    public static SQLocator createPureFilterLocator(SQLocator locator, ElementFilter newFilter) {
        ElementFilterList elementFilterList = locator.getElementFilterList();
        ElementFilterList newElementFilterList = mergeFilterIntoFilterList(elementFilterList, newFilter);
        return new SQLocator(locator.getWebDriver(), SQLocatorCss.CSS_NOT_NATIVELY_SUPPORTED,
                new SQLocatorXPath(locator.getXPathExpression(), newElementFilterList)
        );
    }

    public static ElementFilterList mergeFilterIntoFilterList(ElementFilterList elementFilterList, ElementFilter newFilter) {
        List<ElementFilter> newFilters = new ArrayList<ElementFilter>(elementFilterList.getElementFilters().size());
        for (ElementFilter elementFilter : elementFilterList.getElementFilters()) {
            if (elementFilter != ElementFilter.FILTER_NOTHING) {
                newFilters.add(elementFilter);
            }
        }
        newFilters.add(newFilter);
        return new ElementFilterList(newFilters);
    }

}