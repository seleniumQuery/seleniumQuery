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

package io.github.seleniumquery;

import io.github.seleniumquery.by.SeleniumQueryBy;
import io.github.seleniumquery.functions.SeleniumQueryFunctions;
import io.github.seleniumquery.internal.SqObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * This factory builds {@link io.github.seleniumquery.SeleniumQueryObject}s. Necessary because all
 * constructors in that class have protected visibility (we don't want them public).
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class InternalSeleniumQueryObjectFactory {

    private static InternalSeleniumQueryObjectFactory instance;

    public static InternalSeleniumQueryObjectFactory instance() {
        if (instance == null) {
            instance = new InternalSeleniumQueryObjectFactory(new SeleniumQueryFunctions());
        }
        return instance;
    }

    public InternalSeleniumQueryObjectFactory(SeleniumQueryFunctions seleniumQueryFunctions) {
        this.seleniumQueryFunctions = seleniumQueryFunctions;
    }

    private final SeleniumQueryFunctions seleniumQueryFunctions;

    public SeleniumQueryObject create(WebDriver driver, By by, List<WebElement> elements, SeleniumQueryObject previous) {
        return create(this.seleniumQueryFunctions, driver, by, elements, previous);
    }

    public SeleniumQueryObject create(SeleniumQueryFunctions seleniumQueryFunctions, WebDriver driver, By by, List<WebElement> elements, SeleniumQueryObject previous) {
        return new SqObject(seleniumQueryFunctions, driver, by, elements, previous);
    }

    public SeleniumQueryObject createWithInvalidSelector(WebDriver driver, List<WebElement> elements, SeleniumQueryObject previous) {
        return create(driver, getNoSelectorInvalidBy(), elements, previous);
    }

    SeleniumQueryObject createWithInvalidSelectorAndNoPrevious(WebDriver driver, List<WebElement> elements) {
        return createWithInvalidSelector(driver, elements, SqObject.NOT_BUILT_BASED_ON_A_PREVIOUS_OBJECT);
    }

    public SeleniumQueryObject createWithInvalidSelectorAndNoPrevious(WebDriver driver, WebElement... elements) {
        return createWithInvalidSelectorAndNoPrevious(driver, asList(elements));
    }

    SeleniumQueryObject createWithValidSelectorAndNoPrevious(WebDriver driver, String selector) {
        return new SqObject(seleniumQueryFunctions, driver, getBy(selector));
    }

    private By getBy(String selector) {
        return SeleniumQueryBy.byEnhancedSelector(selector);
    }

    private By getNoSelectorInvalidBy() {
        return SeleniumQueryBy.NO_SELECTOR_INVALID_BY;
    }

}