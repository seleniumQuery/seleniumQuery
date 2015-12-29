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

package io.github.seleniumquery.functions.jquery.traversing.filtering;

import com.google.common.base.Predicate;
import io.github.seleniumquery.SeleniumQueryObject;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import testinfrastructure.testdouble.Dummies;
import testinfrastructure.testdouble.SeleniumQueryObjectMother;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class FilterFunctionTest {

    private static final Predicate<WebElement> NULL_PREDICATE = null;

    FilterFunction filterFunction = new FilterFunction();

    @Test
    public void filter_null_should_return_same_elements() {
        // given
        WebElement dummyWebElement = Dummies.createDummyWebElement();
        WebElement dummyWebElement2 = Dummies.createDummyWebElement();
        SeleniumQueryObject sqo = SeleniumQueryObjectMother.createStubSeleniumQueryObjectWithElements(dummyWebElement, dummyWebElement2);
        // when
        SeleniumQueryObject filteredObject = filterFunction.filter(sqo, NULL_PREDICATE);
        // then
        assertThat(filteredObject.get(), containsInAnyOrder(dummyWebElement, dummyWebElement2));
    }

}