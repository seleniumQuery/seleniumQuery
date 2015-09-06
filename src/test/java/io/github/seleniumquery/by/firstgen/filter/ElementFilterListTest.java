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

package io.github.seleniumquery.by.filter;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ElementFilterListTest {

    @Test
    public void merge__should_return_itself_if_arg_is_emptyList() throws Exception {
        ElementFilter filter = createSomeElementFilter();
        ElementFilterList elementFilterList = new ElementFilterList(asList(filter));

        ElementFilterList mergedList = elementFilterList.merge(ElementFilterList.FILTER_NOTHING_LIST);

        assertThat(mergedList, is(elementFilterList));
    }

    private ElementFilter createSomeElementFilter() {
        return new ElementFilter() {
            @Override
            public List<WebElement> filterElements(WebDriver driver, List<WebElement> elements) {
                return null;
            }
        };
    }

    @Test
    public void merge__should_copy_merge() throws Exception {
        // TODO
    }

    @Test
    public void merger__empty_should_merge_the_given_list_as_arg() throws Exception {
        ElementFilter filter = createSomeElementFilter();
        ElementFilterList elementFilterList = new ElementFilterList(asList(filter));

        ElementFilterList mergedList = ElementFilterList.FILTER_NOTHING_LIST.merge(elementFilterList);

        assertThat(mergedList, is(elementFilterList));
    }

    @Test
    public void filter_should_return_arg() throws Exception {
        // emptyElementFilterList.filter(driver, elementList) === elementList
        // TODO
    }

}