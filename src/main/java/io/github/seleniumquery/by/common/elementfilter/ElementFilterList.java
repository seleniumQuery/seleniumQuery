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

package io.github.seleniumquery.by.common.elementfilter;

import io.github.seleniumquery.utils.SelectorUtils;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * This class should be IMMUTABLE.
 *
 * @author acdcjunior
 * @author ricardo-sc
 * @since 0.10.0
 */
public class ElementFilterList {

    public static ElementFilterList asFilterList(ElementFilter... filters) {
        return new ElementFilterList(asList(filters));
    }

    public static final ElementFilterList FILTER_NOTHING_LIST = new ElementFilterList(Collections.<ElementFilter>emptyList()) {
        @Override
        public List<WebElement> filter(WebDriver driver, List<WebElement> elements) {
            return elements;
        }
        @Override
        public ElementFilterList merge(ElementFilterList elementFilterList) {
            return elementFilterList;
        }
    };

	private List<ElementFilter> elementFilters;
	
	public ElementFilterList(ElementFilter... filters) {
		this(asList(filters));
	}

	public ElementFilterList(List<ElementFilter> elementFilters) {
		this.elementFilters = Collections.unmodifiableList(elementFilters);
	}

	public List<WebElement> filter(SearchContext context, List<WebElement> elements) {
		WebDriver driver = SelectorUtils.getWebDriver(context);
		return filter(driver, elements);
	}

	public List<WebElement> filter(WebDriver driver, List<WebElement> elements) {
		if (this.elementFilters.size() > 0) {
            // TODO we are currently disabling the filter support -- we will only take it back when the system is stable
			throw new UnsupportedOperationException("The current selector is not yet supported. Please try a simpler one.");
		}
        List<WebElement> filteredElements = new ArrayList<>(elements);
		for (ElementFilter elementFilter : elementFilters) {
            filteredElements = elementFilter.filterElements(driver, filteredElements);
		}
		return filteredElements;
	}

    public boolean isEmpty() {
        return elementFilters.isEmpty();
    }

	public List<ElementFilter> getElementFilters() {
		return elementFilters;
	}

    public ElementFilterList merge(ElementFilterList elementFilterList) {
        if (FILTER_NOTHING_LIST.equals(elementFilterList)) {
            return this;
        }
        List<ElementFilter> myFilters = this.getElementFilters();
        List<ElementFilter> theirFilters = elementFilterList.getElementFilters();
        List<ElementFilter> ourFilters = new ArrayList<>(myFilters.size()+theirFilters.size());
        ourFilters.addAll(myFilters);
        ourFilters.addAll(theirFilters);
        return new ElementFilterList(ourFilters);
    }

}