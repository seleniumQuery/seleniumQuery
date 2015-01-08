package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.xpath.TagComponentList;
import io.github.seleniumquery.by.xpath.XPathSelectorCompilerService;
import org.junit.Test;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsByXPath;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class TagComponentTest {

    final List<WebElement> fakeElements = Arrays.asList(mock(WebElement.class), mock(WebElement.class), mock(WebElement.class));

    @Test
    public void testFindWebElements() {
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
    public void testFindWebElements_when_compiled_through() {
        // given
        TagComponentList tagComponentList = XPathSelectorCompilerService.compileSelectorList("span");

        SearchContext searchContext = mock(SearchContext.class, withSettings().extraInterfaces(FindsByXPath.class, WebDriver.class));
        when(((FindsByXPath)searchContext).findElementsByXPath("(.//*[self::span])")).thenReturn(fakeElements);
        // when
        List<WebElement> webElements = tagComponentList.findWebElements(searchContext);
        // then
        assertThat(webElements, is(fakeElements));
    }

}