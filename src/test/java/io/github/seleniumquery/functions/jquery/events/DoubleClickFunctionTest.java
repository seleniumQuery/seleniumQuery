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

import io.github.seleniumquery.SeleniumQueryException;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.functions.jquery.events.DoubleClickTestDoubles.CoordinatesSpy;
import io.github.seleniumquery.functions.jquery.events.DoubleClickTestDoubles.ThrowElementHiddenExceptionCoordinates;
import io.github.seleniumquery.functions.jquery.events.DoubleClickTestDoubles.WebDriverMouseCoordinatesSpy;
import io.github.seleniumquery.functions.jquery.events.DoubleClickTestDoubles.WebElementLocationSpy;
import org.junit.Test;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.Coordinates;
import testinfrastructure.testdouble.org.apache.commons.logging.LogInjector;
import testinfrastructure.testdouble.org.apache.commons.logging.LogSpy;
import testinfrastructure.testdouble.org.openqa.selenium.WebDriverDummy;
import testinfrastructure.testdouble.org.openqa.selenium.WebElementStub;
import testinfrastructure.testdouble.org.openqa.selenium.interactions.MouseDummy;
import testinfrastructure.testdouble.org.openqa.selenium.interactions.internal.CoordinatesDummy;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static testinfrastructure.testdouble.io.github.seleniumquery.SeleniumQueryObjectMother.createStubSeleniumQueryObjectWithElements;

public class DoubleClickFunctionTest {

    @Test
    public void dblclick__shouldReturnPassedSeleniumQueryObject() {
        // given
        SeleniumQueryObject sqo = createStubSeleniumQueryObjectWithElements();
        // when
        SeleniumQueryObject returnedSqo = new DoubleClickFunction().dblclick(sqo);
        // then
        assertThat(returnedSqo, is(sqo));
    }

    @Test
    public void dblclick__shouldCallDoubleClickOnEveryElementIfNoneThrowsException() {
        // given
        CoordinatesSpy firstElementCoordinatesSpy = new CoordinatesSpy();
        WebElement firstElement = new WebElementLocationSpy(firstElementCoordinatesSpy);
        CoordinatesSpy secondElementCoordinatesSpy = new CoordinatesSpy();
        WebElement secondElement = new WebElementLocationSpy(secondElementCoordinatesSpy);

        SeleniumQueryObject sqo = createStubSeleniumQueryObjectWithElements(new WebDriverMouseCoordinatesSpy(), firstElement, secondElement);
        // when
        new DoubleClickFunction().dblclick(sqo);
        // then
        firstElementCoordinatesSpy.assertDoubleClickedAfterMovedTo();
        secondElementCoordinatesSpy.assertDoubleClickedAfterMovedTo();
    }

    @Test
    public void dblclick__shouldCallDoubleClickOnEveryElementEvenIfSomeThrowExceptions() {
        // given
        WebElement elementThatCantBeDoubleClicked = new WebElementLocationSpy(new ThrowElementHiddenExceptionCoordinates());
        CoordinatesSpy secondElementCoordinatesSpy = new CoordinatesSpy();
        WebElement secondElement = new WebElementLocationSpy(secondElementCoordinatesSpy);

        SeleniumQueryObject sqo = createStubSeleniumQueryObjectWithElements(new WebDriverMouseCoordinatesSpy(), elementThatCantBeDoubleClicked, secondElement);
        // when
        new DoubleClickFunction().dblclick(sqo);
        // then
        secondElementCoordinatesSpy.assertDoubleClickedAfterMovedTo();
    }

    @Test
    public void dblclick__shouldLogInfoIfAnyElementThrewException() {
        // given
        LogSpy logSpy = LogInjector.injectLogSpy(DoubleClickFunction.class);

        WebElement elementThatCantBeDoubleClicked = new WebElementLocationSpy(new ThrowElementHiddenExceptionCoordinates());
        CoordinatesSpy secondElementCoordinatesSpy = new CoordinatesSpy();
        WebElement secondElement = new WebElementLocationSpy(secondElementCoordinatesSpy);

        SeleniumQueryObject sqo = createStubSeleniumQueryObjectWithElements(new WebDriverMouseCoordinatesSpy(), elementThatCantBeDoubleClicked, secondElement);
        // when
        new DoubleClickFunction().dblclick(sqo);
        // then
        logSpy.assertInfoWithExceptionWasLogged(ElementNotVisibleException.class);
    }

    @Test(expected = SeleniumQueryException.class)
    public void dblclick__shouldThrowExceptionIfNoElementIsClickable() {
        // given
        WebElement unclickableHiddenWebElement = new WebElementLocationSpy(new ThrowElementHiddenExceptionCoordinates());
        WebElement unclickableHiddenWebElementTwo = new WebElementLocationSpy(new ThrowElementHiddenExceptionCoordinates());
        SeleniumQueryObject sqo = createStubSeleniumQueryObjectWithElements(new WebDriverMouseCoordinatesSpy(), unclickableHiddenWebElement, unclickableHiddenWebElementTwo);
        // when
        new DoubleClickFunction().dblclick(sqo);
        // then
        // exception is thrown
    }

}

class DoubleClickTestDoubles {
    static class CoordinatesSpy extends CoordinatesDummy {
        private boolean movedTo = false;
        private boolean doubleClicked = false;
        void markMovedTo() {
            this.movedTo  = true;
        }
        void markDoubleClicked() {
            assertThat("mouse should have been moved to these coordinates before double clicking them", movedTo, is(true));
            this.doubleClicked = true;
        }
        void assertDoubleClickedAfterMovedTo() {
            assertThat(doubleClicked, is(true));
        }
    }
    static class WebElementLocationSpy extends WebElementStub {
        private final CoordinatesSpy coordinatesToReturn;
        WebElementLocationSpy(CoordinatesSpy coordinatesToReturn) { this.coordinatesToReturn = coordinatesToReturn; }
        @Override public CoordinatesSpy getCoordinates() { return this.coordinatesToReturn; }
    }
    private static class MouseCoordinatesSpy extends MouseDummy {
        @Override public void mouseMove(Coordinates w) {
            ((CoordinatesSpy) w).markMovedTo();
        }
        @Override public void doubleClick(Coordinates w) {
            ((CoordinatesSpy) w).markDoubleClicked();
        }
    }
    static class WebDriverMouseCoordinatesSpy extends WebDriverDummy {
        @Override public Keyboard getKeyboard() { return null; }
        @Override public Mouse getMouse() { return new MouseCoordinatesSpy(); }
    }

    static class ThrowElementHiddenExceptionCoordinates extends CoordinatesSpy {
        @Override void markMovedTo() {
            throw new ElementNotVisibleException("simulating a hidden element");
        }
    }
}
