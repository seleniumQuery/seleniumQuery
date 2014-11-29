package io.github.seleniumquery;

import org.junit.Test;

import static infrastructure.IntegrationTestUtils.htmlTestFileUrl;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class SeleniumQueryBrowserTest {

    @Test
    public void testName() throws Exception {
        SeleniumQueryBrowser chrome = new SeleniumQueryBrowser();
        chrome.$.driver().useHtmlUnit().emulatingChrome();
        chrome.$.url(htmlTestFileUrl(SeleniumQueryBrowserTest.class));

        SeleniumQueryBrowser firefox = new SeleniumQueryBrowser();
        firefox.$.driver().useHtmlUnit().emulatingFirefox();
        firefox.$.url(htmlTestFileUrl(SeleniumQueryBrowserTest.class));

        assertThat(chrome.$("#agent").text(), containsString("Chrome"));
        assertThat(firefox.$("#agent").text(), containsString("Firefox"));

        chrome.$.quit();
        firefox.$.quit();
    }

}