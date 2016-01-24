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

package testinfrastructure.testdouble.org.openqa.selenium;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;

public class WebElementMother {

    public static WebElement createWebElementUnclickableHidden() {
        return new WebElementStub() {
            // this has to be a STUB and not a DUMMY because when the $().click() function fails, it
            // call a series of other functions on WebElement just to print a more complete error message
            @Override
            public void click() {
                throw new ElementNotVisibleException("This web element is hidden, thus not clickable.");
            }
        };
    }

    public static WebElement createWebElementClickable() {
        return new WebElementDummy() {
            @Override
            public void click() { }
        };
    }

    public static WebElement createWebElementWithTag(final String tagName) {
        return new WebElementStub() {
            @Override
            public String getTagName() {
                return tagName;
            }
        };
    }

}
