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

package io.github.seleniumquery.functions.jquery.events;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.utils.DriverVersionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static io.github.seleniumquery.functions.jquery.events.ClickFunctionUtils.reportIfThereWasAnyElementNotClicked;

/**
 * Double-clicks on a bunch of elements, complaining when needed.
 *
 * $("#element").dblclick(); internals
 *
 * @author acdcjunior
 * @since 0.15.0
 */
public class DoubleClickFunction {

    private static final Log LOGGER = LogFactory.getLog(DoubleClickFunction.class);

    public SeleniumQueryObject dblclick(SeleniumQueryObject seleniumQueryObject) {
        LOGGER.debug("Double-Clicking " + seleniumQueryObject);
        List<WebElement> elements = seleniumQueryObject.get();

        int numberOfNotClickedElements = 0;
        Exception lastCaughtException = null;
        WebElement elementThatThrewLastCaughtException = null;

        for (WebElement element : elements) {
            try {
                doubleClick(element, seleniumQueryObject.getWebDriver());
            } catch (ElementNotVisibleException e) {
                numberOfNotClickedElements++;
                lastCaughtException = e;
                elementThatThrewLastCaughtException = element;
            }
        }

        reportIfThereWasAnyElementNotClicked(LOGGER, seleniumQueryObject, elements, numberOfNotClickedElements, lastCaughtException, elementThatThrewLastCaughtException);
        return seleniumQueryObject;
    }

    private void doubleClick(WebElement element, WebDriver webDriver) {
        /* #Cross-Driver
         *
         * SAFARI does not implement the "mouseMoveTo" action!
         * org.openqa.selenium.WebDriverException: Unknown command: {"id":"e4rhawdodv9u","name":"mouseMoveTo","parameters":{"element":":wdc:1467681082384"}}
         *
         * EDGE never fires the dblclick event using actions (and is silentl about it :|).
         */
        if (DriverVersionUtils.isSafariDriver(webDriver) || DriverVersionUtils.isEdgeDriver(webDriver)) {
            element.click();
            element.click();
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript("arguments[0].dispatchEvent(new MouseEvent('dblclick',{'view':window,'bubbles':true,'cancelable':true}));", element);
        } else {
            Actions actions = new Actions(webDriver);
            actions.doubleClick(element).perform();
        }
    }

}
