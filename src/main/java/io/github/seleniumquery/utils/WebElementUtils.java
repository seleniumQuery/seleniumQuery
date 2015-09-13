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

package io.github.seleniumquery.utils;

import org.openqa.selenium.WebElement;

/**
 * Several functions for simplified dealing with {@link WebElement}s.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class WebElementUtils {

    public static boolean elementHasAttribute(WebElement element, String attributeName, String attributeValue) {
        return attributeValue.equalsIgnoreCase(element.getAttribute(attributeName));
    }

    public static boolean isTextareaTag(WebElement element) {
        return "textarea".equals(element.getTagName());
    }

    public static boolean isSelectTag(WebElement element) {
        return "select".equals(element.getTagName());
    }

    public static boolean isOptionTag(WebElement element) {
        return "option".equals(element.getTagName());
    }

    public static boolean isInputTag(WebElement element) {
        return "input".equals(element.getTagName());
    }

    public static boolean isInputFileTag(WebElement element) {
        return isInputTagWithType(element, "file");
    }

    private static boolean isInputTagWithType(WebElement element, String file) {
        return isInputTag(element) && elementHasAttribute(element, "type", file);
    }

    public static boolean isInputRadioTag(WebElement element) {
        return isInputTagWithType(element, "radio");
    }

    public static boolean isInputCheckboxTag(WebElement element) {
        return isInputTagWithType(element, "checkbox");
    }

    public static boolean isInputHiddenTag(WebElement element) {
        return isInputTagWithType(element, "hidden");
    }

    public static boolean isInputButtonTag(WebElement element) {
        return isInputTagWithType(element, "button");
    }

    public static boolean isInputSubmitTag(WebElement element) {
        return isInputTagWithType(element, "submit");
    }

    public static boolean isContentEditable(WebElement element) {
        if (element == null) {
            return false;
        }
        String contenteditable = element.getAttribute("contenteditable");
        if ("false".equalsIgnoreCase(contenteditable)) {
            return false;
        }
        if ("".equals(contenteditable) || "true".equalsIgnoreCase(contenteditable)) {
            return true;
        }
        // no contenteditable attribute; or
        // contenteditable == "inherit"; or
        // contenteditable == "anything";
        // then we consider as "inherit" - aka its value is the value of its father's contenteditable
        return isContentEditable(SelectorUtils.parent(element));
    }

}