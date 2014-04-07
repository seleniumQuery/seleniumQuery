package org.openqa.selenium.seleniumquery.by.enhancement;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.seleniumquery.SeleniumQuery.$;

import org.junit.Test;
import org.openqa.selenium.seleniumquery.TestInfrastructure;

public class ContainsSelectorTest {

    @Test
    public void test() throws Exception {
        $.browser.setDefaultDriver(TestInfrastructure.getDriver());
        
        $.location.href(TestInfrastructure.getHtmlTestFileUrl(getClass()));
        
        assertThat($("div").size(), is(4));
        assertThat($("div:contains(abc)").size(), is(4));
        assertThat($("div:contains(\"abc\")").size(), is(4));
        assertThat($("div:contains('abc')").size(), is(4));
        assertThat($("div:contains('\"abc\"')").size(), is(0));
    }

}