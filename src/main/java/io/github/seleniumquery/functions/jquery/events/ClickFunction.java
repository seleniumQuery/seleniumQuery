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

package io.github.seleniumquery.functions.jquery.events;

import io.github.seleniumquery.SeleniumQueryException;
import io.github.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Clicks a bunch of elements, complaining when needed.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class ClickFunction {

    public static SeleniumQueryObject click(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements) {
        for (WebElement element : elements) {
            try {
                element.click();
            } catch (ElementNotVisibleException e) {
                throw new SeleniumQueryException("The matched set from " + seleniumQueryObject + " contains at least one element " +
                        "that is not visible and, as such, nor a user, nor seleniumQuery may interact with it." +
                        "\n Referring element: " + toString(element), e);
            }
        }
        return seleniumQueryObject;
    }

    private static String toString(WebElement element) {
        return "\n\t id attribute: \"" + element.getAttribute("id") + "\"" +
               "\n\t class attribute: \"" + element.getAttribute("class") + "\"" +
               "\n\t name attribute: \"" + element.getAttribute("name") + "\"" +
               "\n\t tag: \"" + element.getTagName() + "\"" +
               "\n\t text: \"" + element.getText() + "\"" +
               "\n\t value attribute: " + element.getAttribute("value") + "\"" +
               "\n\t size()/dimension: " + element.getSize() +
               "\n\t isDisplayed(): " + element.isDisplayed() +
               "\n\t isEnabled(): " + element.isEnabled() +
               "\n\t toString(): " + element + "\n";
    }

}