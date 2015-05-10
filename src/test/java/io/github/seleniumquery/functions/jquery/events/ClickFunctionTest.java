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
import org.apache.commons.logging.Log;
import org.junit.Test;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import testutils.LogInjector;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static testutils.Dummies.createDummySeleniumQueryObject;
import static testutils.Dummies.createDummyToStringableSeleniumQueryObject;
import static testutils.Mocks.createWebElementMock;

public class ClickFunctionTest {

    @Test
    public void click__shouldReturnPassedSeleniumQueryObject() {
        // given
        SeleniumQueryObject sqo = createDummySeleniumQueryObject();
        // when
        SeleniumQueryObject returnedSqo = ClickFunction.click(sqo, Collections.<WebElement>emptyList());
        // then
        assertThat(returnedSqo, is(sqo));
    }

    @Test
    public void click__shouldCallClickOnEveryElementIfNoneThrowsException() {
        // given
        WebElement webElementClickSpyOne = createWebElementClickSpy();
        WebElement webElementClickSpyTwo = createWebElementClickSpy();
        // when
        ClickFunction.click(createDummySeleniumQueryObject(), asList(webElementClickSpyOne, webElementClickSpyTwo));
        // then
        assertNumberOfTimesClicked(webElementClickSpyOne, 1);
        assertNumberOfTimesClicked(webElementClickSpyTwo, 1);
    }

    @Test
    public void click__shouldCallClickOnEveryElementEvenIfSomeThrowExceptions() {
        // given
        WebElement unclickableHiddenWebElement = createUnclickableHiddenWebElement();
        WebElement webElementClickSpy = createWebElementClickSpy();
        // when
        ClickFunction.click(createDummyToStringableSeleniumQueryObject(), asList(unclickableHiddenWebElement, webElementClickSpy));
        // then
        assertNumberOfTimesClicked(webElementClickSpy, 1);
    }

    @Test
    public void click__shouldLogInfoIfAnyElementThrewException() {
        // given
        Log logSpy = LogInjector.injectLogSpy(ClickFunction.class);
        // when
        ClickFunction.click(createDummyToStringableSeleniumQueryObject(), asList(createUnclickableHiddenWebElement(), createWebElementMock()));
        // then
        verify(logSpy).info(anyString(), any(ElementNotVisibleException.class));
    }

    @Test(expected = SeleniumQueryException.class)
    public void click__shouldThrowExceptionIfNoElementIsClickable() {
        // given
        WebElement unclickableHiddenWebElement = createUnclickableHiddenWebElement();
        WebElement unclickableHiddenWebElementTwo = createUnclickableHiddenWebElement();
        // when
        ClickFunction.click(createDummyToStringableSeleniumQueryObject(), asList(unclickableHiddenWebElement, unclickableHiddenWebElementTwo));
        // then
        // exception is thrown
    }

    private void assertNumberOfTimesClicked(WebElement webElementClickSpy, int numberOfTimesClicked) {
        verify(webElementClickSpy, times(numberOfTimesClicked)).click();
    }

    private WebElement createWebElementClickSpy() {
        return createWebElementMock();
    }

    private WebElement createUnclickableHiddenWebElement() {
        WebElement unclickableHiddenWebElement = createWebElementMock();
        doThrow(new ElementNotVisibleException("This web element is hidden, thus not clickable.")).when(unclickableHiddenWebElement).click();
        return unclickableHiddenWebElement;
    }

}