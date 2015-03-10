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

package io.github.seleniumquery.by.locator;

import io.github.seleniumquery.by.DriverVersionUtils;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static io.github.seleniumquery.by.locator.SQLocatorCss.universalSelector;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SQLocatorUtilsTest {

    public static final SQLocator TAG_ASTERISK = tagAsterisk(mock(WebDriver.class));
    public static SQLocator tagAsterisk(WebDriver driver) {
        return new SQLocator(driver, universalSelector(), ".//*[true()]");
    }

    public static WebDriver createMockDriverWithNativeSupportFor(String pseudoClass) {
        return createMockDriver(pseudoClass, true);
    }
    public static WebDriver createMockDriverWithoutNativeSupportFor(String pseudoClass) {
        return createMockDriver(pseudoClass, false);
    }
    public static WebDriver createMockDriver(String pseudoClass, boolean support) {
        WebDriver webDriverMock = mock(WebDriver.class);
        DriverVersionUtils driverVersionUtilsMock = mock(DriverVersionUtils.class);
        DriverVersionUtils.setInstance(driverVersionUtilsMock);
        when(driverVersionUtilsMock.hasNativeSupportForPseudo(webDriverMock, pseudoClass)).thenReturn(support);
        return webDriverMock;
    }
    public static WebDriver createMockDriverWithNativeSupporForSelectorAndEmulatingPhantomJS(String checkedPseudo) {
        WebDriver mockDriverWithNativeSupportFor = SQLocatorUtilsTest.createMockDriverWithNativeSupportFor(checkedPseudo);
        DriverVersionUtils driverVersionUtilsMock = DriverVersionUtils.getInstance();
        when(driverVersionUtilsMock.isPhantomJSDriver(mockDriverWithNativeSupportFor)).thenReturn(true);
        return mockDriverWithNativeSupportFor;
    }
    public static WebDriver createMockDriverWithNativeSupporForSelectorAndEmulatingHtmlUnit(String checkedPseudo) {
        WebDriver mockDriverWithNativeSupportFor = SQLocatorUtilsTest.createMockDriverWithNativeSupportFor(checkedPseudo);
        DriverVersionUtils driverVersionUtilsMock = DriverVersionUtils.getInstance();
        when(driverVersionUtilsMock.isHtmlUnitDriver(mockDriverWithNativeSupportFor)).thenReturn(true);
        return mockDriverWithNativeSupportFor;
    }

    @Test
    public void conditionalSimpleXPathMerge__should_merge_XPath_condition_adding_and() {
        String mergedXPath = SQLocatorUtils.conditionalSimpleXPathMerge(".//*[previousStuff]", "newStuff");
        assertThat(mergedXPath, is(".//*[previousStuff and newStuff]"));
    }

    @Test
    public void conditionalSimpleXPathMerge__should_remove_last_previous_condition_if_it_was_just_true() {
        String mergedXPath = SQLocatorUtils.conditionalSimpleXPathMerge(".//*[true()]", "newStuff");
        assertThat(mergedXPath, is(".//*[newStuff]"));
    }

    @Test
    public void conditionalSimpleXPathMerge__should_remove_last_previous_condition_if_it_was_just_true_ALTERNATE() {
        String mergedXPath = SQLocatorUtils.conditionalSimpleXPathMerge(".//*[self::a]/*[true()]", "newStuff");
        assertThat(mergedXPath, is(".//*[self::a]/*[newStuff]"));
    }

    @Test
    public void conditionalSimpleXPathMerge__should_remove_last_previous_condition_if_it_was_just_true_even_if_there_was_something_else() {
        String mergedXPath = SQLocatorUtils.conditionalSimpleXPathMerge(".//*[previousStuff and true()]", "newStuff");
        assertThat(mergedXPath, is(".//*[previousStuff and newStuff]"));
    }

    @Test
    public void conditionalSimpleXPathMerge__should_remove_last_previous_condition_if_it_was_just_true_but_with_care() {
        String mergedXPath = SQLocatorUtils.conditionalSimpleXPathMerge(".//*[xtrue()]", "newStuff");
        assertThat(mergedXPath, is(".//*[xtrue() and newStuff]"));
    }

    @Test
    public void conditionalSimpleXPathMerge__should_remove_last_previous_condition_if_it_was_just_true_but_with_care_even_if_there_was_something_else() {
        String mergedXPath = SQLocatorUtils.conditionalSimpleXPathMerge("[previousStuff and xtrue()]", "newStuff");
        assertThat(mergedXPath, is("[previousStuff and xtrue() and newStuff]"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void conditionalSimpleXPathMerge__should_validate_the_left_expression() {
        SQLocatorUtils.conditionalSimpleXPathMerge("true()", "newStuff");
    }

    @Test(expected = IllegalArgumentException.class)
    public void conditionalSimpleXPathMerge__should_validate_the_left_expression_for_nullity() {
        SQLocatorUtils.conditionalSimpleXPathMerge(null, "newStuff");
    }

}