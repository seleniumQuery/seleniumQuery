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

    public static SQLocator createPureXPathOnly(SQLocator locator, String newXPathExpression) {
        return new SQLocator(locator.getWebDriver(), sameCssLocatorButWithoutPureCss(locator),
                new SQLocatorXPath(newXPathExpression, locator.canPureXPath(), locator.getElementFilterList())
        );
    }

    private static SQLocatorCss sameCssLocatorButWithoutPureCss(SQLocator locator) {
        SQLocatorCss current = locator.getSQCssSelector();
        return new SQLocatorCss(current.getLeftPart(), current.getTag(), current.getRightPart(), false);
    }

    public static SQLocator createPureFilterLocator(SQLocator locator, ElementFilter newFilter) {
        ElementFilterList elementFilterList = locator.getElementFilterList();
        List<ElementFilter> newFilters = new ArrayList<ElementFilter>(elementFilterList.getElementFilters().size());
        for (ElementFilter elementFilter : elementFilterList.getElementFilters()) {
            if (elementFilter != ElementFilter.FILTER_NOTHING) {
                newFilters.add(elementFilter);
            }
        }
        newFilters.add(newFilter);
        return new SQLocator(locator.getWebDriver(), sameCssLocatorButWithoutPureCss(locator),
                new SQLocatorXPath(locator.getXPathExpression(), false, new ElementFilterList(newFilters))
        );
    }

}