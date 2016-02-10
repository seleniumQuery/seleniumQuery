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

package testinfrastructure.testdouble.io.github.seleniumquery;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.functions.SeleniumQueryFunctions;
import io.github.seleniumquery.internal.SqObjectFactory;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.Arrays.asList;
import static testinfrastructure.testdouble.io.github.seleniumquery.SeleniumQueryObjectDummy.createSeleniumQueryObjectDummy;
import static testinfrastructure.testdouble.org.openqa.selenium.ByToStringableDummy.createByToStringableDummy;
import static testinfrastructure.testdouble.org.openqa.selenium.WebDriverDummy.createWebDriverDummy;
import static testinfrastructure.testdouble.org.openqa.selenium.WebElementDummy.createWebElementDummy;

public class SeleniumQueryObjectMother {

    public static SeleniumQueryObject createStubSeleniumQueryObjectWithSeleniumQueryFunctions(SeleniumQueryFunctions seleniumQueryFunctions) {
        List<WebElement> dummyWebElements = asList(createWebElementDummy(), createWebElementDummy());
        return createStubSeleniumQueryObject(seleniumQueryFunctions, dummyWebElements);
    }

    private static SeleniumQueryObject createStubSeleniumQueryObject(SeleniumQueryFunctions seleniumQueryFunctions, List<WebElement> webElements) {
        SeleniumQueryObject DUMMY_PREVIOUS = createSeleniumQueryObjectDummy();
        return SqObjectFactory.instance().create(seleniumQueryFunctions, createWebDriverDummy(), createByToStringableDummy(), webElements, DUMMY_PREVIOUS);
    }

    public static SeleniumQueryObject createStubSeleniumQueryObjectWithElements(WebElement... elements) {
        return createStubSeleniumQueryObject(new SeleniumQueryFunctions(), asList(elements));
    }

    public static SeleniumQueryObject createStubSeleniumQueryObjectWithAtLeastOneElement() {
        return createStubSeleniumQueryObjectWithElements(createWebElementDummy(), createWebElementDummy());
    }

    public static SeleniumQueryObject createStubSeleniumQueryObject() {
        return createStubSeleniumQueryObjectWithElements();
    }

    public static SeleniumQueryObject.EachFunction createDummyEachFunction() {
        return new SeleniumQueryObject.EachFunction() {
            @Override public boolean apply(int index, WebElement element) {
                return false;
            }
        };
    }

}