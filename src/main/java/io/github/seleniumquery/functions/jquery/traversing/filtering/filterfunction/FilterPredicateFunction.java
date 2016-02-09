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

package io.github.seleniumquery.functions.jquery.traversing.filtering.filterfunction;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import io.github.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.WebElement;

import java.util.List;

import static io.github.seleniumquery.InternalSeleniumQueryObjectFactory.instance;
import static java.util.Collections.emptyList;

/**
 * $("selector").filter(Predicate function)
 *
 * @author acdcjunior
 * @since 0.11.0
 */
public class FilterPredicateFunction {

    public SeleniumQueryObject filter(SeleniumQueryObject seleniumQueryObject, Predicate<WebElement> filterFunction) {
        List<WebElement> filteredWebElements = filterWebElements(seleniumQueryObject.get(), filterFunction);
        return instance().create(seleniumQueryObject.getSeleniumQueryFunctions(),
                                 seleniumQueryObject.getWebDriver(),
                                 null,
                                 filteredWebElements,
                                 seleniumQueryObject);
    }

    private List<WebElement> filterWebElements(List<WebElement> unfiltered, Predicate<WebElement> filterFunction) {
        if (filterFunction == null) {
            return emptyList();
        }
        Iterable<WebElement> filter = Iterables.filter(unfiltered, filterFunction);
        return Lists.newArrayList(filter);
    }

}