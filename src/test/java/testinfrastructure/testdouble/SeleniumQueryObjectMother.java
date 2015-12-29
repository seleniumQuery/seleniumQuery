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

package testinfrastructure.testdouble;

import io.github.seleniumquery.InternalSeleniumQueryObjectFactory;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.functions.SeleniumQueryFunctions;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.Arrays.asList;
import static testinfrastructure.testdouble.Dummies.*;

public class SeleniumQueryObjectMother {

    public static SeleniumQueryObject createStubSeleniumQueryObjectWithSeleniumQueryFunctions(SeleniumQueryFunctions seleniumQueryFunctions) {
        List<WebElement> dummyWebElements = asList(createDummyWebElement(), createDummyWebElement());
        return createStubSeleniumQueryObject(seleniumQueryFunctions, dummyWebElements);
    }

    private static SeleniumQueryObject createStubSeleniumQueryObject(SeleniumQueryFunctions seleniumQueryFunctions, List<WebElement> dummyWebElements) {
        SeleniumQueryObject DUMMY_PREVIOUS = createDummySeleniumQueryObject();
        return InternalSeleniumQueryObjectFactory.instance().create(seleniumQueryFunctions, createDummyWebDriver(), createToStringableDummyBy(), dummyWebElements, DUMMY_PREVIOUS);
    }

    public static SeleniumQueryObject createStubSeleniumQueryObjectWithElements(WebElement... elements) {
        return createStubSeleniumQueryObject(new SeleniumQueryFunctions(), asList(elements));
    }

    public static SeleniumQueryObject createStubSeleniumQueryObject() {
        return createStubSeleniumQueryObjectWithElements();
    }

    /**
     * Creates a "smart" dummy {@link SeleniumQueryObject}. It's goal is to be a mere placeholder in a test
     * fixture. It is "smart" because any method call to it will throw an exception (which indicates the dummy
     * is actually not a simple placeholder - since it is being used by the SUT when it shouldn't).
     */
    public static SeleniumQueryObject createDummySeleniumQueryObject() {
        return createDummy(SeleniumQueryObject.class);
    }

}