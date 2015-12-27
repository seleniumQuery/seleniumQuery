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

import io.github.seleniumquery.SeleniumQueryObject;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class Dummies {

    private static class NoMethodShouldBeCalledAnswer implements Answer<Object> {
        private String className;
        public NoMethodShouldBeCalledAnswer(String className) { this.className = className; }
        @Override
        public Object answer(InvocationOnMock invocation) throws Throwable {
            throw new RuntimeException("This "+className+" is a dummy test double. None of its methods should be called.\n" +
                    "Method called: "+invocation.getMethod());
        }
    }

    private static <D> D createDummy(Class<D> classToDummy) {
        return mock(classToDummy, new NoMethodShouldBeCalledAnswer(classToDummy.getSimpleName()));
    }

    public static WebDriver createDummyWebDriver() {
        return createDummy(WebDriver.class);
    }

    /**
     * Creates a "smart" dummy {@link SeleniumQueryObject}. It's goal is to be a mere placeholder in a test
     * fixture. It is "smart" because any method call to it will throw an exception (which indicates the dummy
     * is actually not a simple placeholder - since it is being used by the SUT when it shouldn't).
     */
    public static SeleniumQueryObject createDummySeleniumQueryObject() {
        return createDummy(SeleniumQueryObject.class);
    }

    public static SeleniumQueryObject createDummyToStringableSeleniumQueryObject() {
        SeleniumQueryObject dummyToStringableSeleniumQueryObject = createDummySeleniumQueryObject();
        //noinspection ResultOfMethodCallIgnored
        doReturn("Dummy SeleniumQueryObject").when(dummyToStringableSeleniumQueryObject).toString();
        return dummyToStringableSeleniumQueryObject;
    }

    public static By createDummyBy() {
        return createDummy(By.class);
    }

    public static WebElement createDummyWebElement() {
        return createDummy(WebElement.class);
    }

}