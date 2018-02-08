/*
 * Copyright (c) 2017 seleniumQuery authors
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

package endtoend.browser;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static testinfrastructure.EndToEndTestUtils.classNameToTestFileUrl;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import endtoend.browser.driver.builders.HtmlUnitDriverBuilderTest;
import endtoend.browser.util.BrowserAgentTestUtils;
import io.github.seleniumquery.SeleniumQueryBrowser;
import io.github.seleniumquery.SeleniumQueryObject;

public class SeleniumQueryBrowserTest {



    @Test
    public void multiple_browser_instances_should_work_OK() {
        SeleniumQueryBrowser chrome = new SeleniumQueryBrowser();
        SeleniumQueryBrowser firefox = new SeleniumQueryBrowser();
        // given
        chrome.$.driver().useHtmlUnit().emulatingChrome().autoQuitDriver();
        // when
        BrowserAgentTestUtils.openBrowserAgentTestHelperUrl(chrome);

        // given
        firefox.$.driver().useHtmlUnit().emulatingFirefox().autoQuitDriver();
        // when
        BrowserAgentTestUtils.openBrowserAgentTestHelperUrl(firefox);

        // then
        BrowserAgentTestUtils.assertBrowserAgent(chrome, allOf(containsString("Chrome"), not(containsString("Firefox"))));
        BrowserAgentTestUtils.assertBrowserAgent(firefox, allOf(containsString("Firefox"), not(containsString("Chrome"))));
    }

    @Test
    public void aliases_work() {
        // given
        SeleniumQueryBrowser htmlUnit = new SeleniumQueryBrowser();
        htmlUnit.$.driver().useHtmlUnit().emulatingChrome().autoQuitDriver();

        htmlUnit.$.url(classNameToTestFileUrl(SeleniumQueryBrowserTest.class));
        List<WebElement> elementsAsList = htmlUnit.$.driver().get().findElements(By.tagName("div"));
        WebElement[] elementsAsArray = elementsAsList.toArray(new WebElement[2]);
        // when
        SeleniumQueryObject $_fromSelector = htmlUnit.$("div");
        SeleniumQueryObject $_fromList = htmlUnit.$(elementsAsList);
        SeleniumQueryObject $_fromArray = htmlUnit.$(elementsAsArray);
        SeleniumQueryObject sQ_fromSelector = htmlUnit.sQ("div");
        SeleniumQueryObject sQ_fromList = htmlUnit.sQ(elementsAsList);
        SeleniumQueryObject sQ_fromArray = htmlUnit.sQ(elementsAsArray);
        SeleniumQueryObject jQuery_fromSelector = htmlUnit.jQuery("div");
        SeleniumQueryObject jQuery_fromList = htmlUnit.jQuery(elementsAsList);
        SeleniumQueryObject jQuery_fromArray = htmlUnit.jQuery(elementsAsArray);
        // then
        Assert.assertEquals(elementsAsList, $_fromSelector.get());
        Assert.assertEquals(elementsAsList, $_fromList.get());
        Assert.assertEquals(elementsAsList, $_fromArray.get());
        Assert.assertEquals(elementsAsList, sQ_fromSelector.get());
        Assert.assertEquals(elementsAsList, sQ_fromList.get());
        Assert.assertEquals(elementsAsList, sQ_fromArray.get());
        Assert.assertEquals(elementsAsList, jQuery_fromSelector.get());
        Assert.assertEquals(elementsAsList, jQuery_fromList.get());
        Assert.assertEquals(elementsAsList, jQuery_fromArray.get());
    }

}
