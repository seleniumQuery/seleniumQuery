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

package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.xpath.TagComponentList;
import io.github.seleniumquery.by.xpath.XPathComponentCompilerService;
import org.junit.Test;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsById;
import org.openqa.selenium.internal.FindsByXPath;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class TagComponentTest {

    final WebElement firstWebElementMock = mock(WebElement.class);
    final List<WebElement> fakeElements = Arrays.asList(firstWebElementMock, mock(WebElement.class), mock(WebElement.class));

    @Test
    public void findWebElements_should_call_findElementsByXPath() {
        // given
        TagComponentList tagComponentList = XPathComponentCompilerService.compileSelectorList("span");

        SearchContext searchContext = mock(SearchContext.class, withSettings().extraInterfaces(FindsByXPath.class, WebDriver.class));
        when(((FindsByXPath)searchContext).findElementsByXPath("(.//*[self::span])")).thenReturn(fakeElements);
        // when
        List<WebElement> webElements = tagComponentList.findWebElements(searchContext);
        // then
        assertThat(webElements, is(fakeElements));
    }

    @Test
    public void findWebElements_should_call_findElementsByXPath__testing_directly() {
        // given
        TagComponent tagComponent = new TagComponent("span");

        SearchContext searchContext = mock(SearchContext.class, withSettings().extraInterfaces(FindsByXPath.class, WebDriver.class));
        when(((FindsByXPath)searchContext).findElementsByXPath("(.//*[self::span])")).thenReturn(fakeElements);
        // when
        List<WebElement> webElements = tagComponent.findWebElements(searchContext);
        // then
        assertThat(webElements, is(fakeElements));
    }

    @Test
    public void findWebElements_if_selector_is_just_an_id_it_should_call_findElementById() {
        // given
        TagComponentList tagComponentList = XPathComponentCompilerService.compileSelectorList("#idz");

        SearchContext searchContext = mock(SearchContext.class, withSettings().extraInterfaces(FindsById.class, WebDriver.class));
        when(((FindsById)searchContext).findElementById("idz")).thenReturn(firstWebElementMock);
        // when
        List<WebElement> webElements = tagComponentList.findWebElements(searchContext);
        // then
        assertThat(webElements, contains(firstWebElementMock));
    }

}