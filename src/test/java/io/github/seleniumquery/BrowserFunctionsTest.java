package io.github.seleniumquery;

import io.github.seleniumquery.globalfunctions.BrowserFunctions;
import org.junit.Test;

import java.math.BigDecimal;

import static infrastructure.IntegrationTestUtils.htmlTestFileUrl;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class BrowserFunctionsTest {

    BrowserFunctions browserFunctions = new BrowserFunctions();

    @Test
    @SuppressWarnings("deprecation")
    public void pause__should_pause_for_the_given_time_in_millis() {
        // given
        long startingTime = System.currentTimeMillis();
        // when
        browserFunctions.pause(1000);
        // then
        long timeSpent = System.currentTimeMillis() - startingTime;
        assertThat(new BigDecimal(timeSpent), is(closeTo(new BigDecimal(1000), new BigDecimal(100))));
    }

    @Test
    public void testName() throws Exception {
        SeleniumQueryBrowser chrome = new SeleniumQueryBrowser();
        chrome.$.driver().useHtmlUnit().emulatingChrome();
        chrome.$.url(htmlTestFileUrl(BrowserFunctionsTest.class));

        SeleniumQueryBrowser firefox = new SeleniumQueryBrowser();
        firefox.$.driver().useHtmlUnit().emulatingFirefox();
        firefox.$.url(htmlTestFileUrl(BrowserFunctionsTest.class));

        assertThat(chrome.$("#agent").text(), containsString("Chrome"));
        assertThat(firefox.$("#agent").text(), containsString("Firefox"));
    }

}