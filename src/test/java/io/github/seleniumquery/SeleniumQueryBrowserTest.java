package io.github.seleniumquery;

import org.junit.Test;

import static infrastructure.IntegrationTestUtils.classNameToTestFileUrl;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class SeleniumQueryBrowserTest {

    @Test
    public void multiple_browser_instances_should_work_OK() {
        SeleniumQueryBrowser chrome = new SeleniumQueryBrowser();
        chrome.$.driver().useHtmlUnit().emulatingChrome();
        chrome.$.url(classNameToTestFileUrl(SeleniumQueryBrowserTest.class));

        SeleniumQueryBrowser firefox = new SeleniumQueryBrowser();
        firefox.$.driver().useHtmlUnit().emulatingFirefox();
        firefox.$.url(classNameToTestFileUrl(SeleniumQueryBrowserTest.class));

        assertThat(chrome.$("#agent").text(), containsString("Chrome"));
        assertThat(firefox.$("#agent").text(), containsString("Firefox"));

        chrome.$.quit();
        firefox.$.quit();
    }

}