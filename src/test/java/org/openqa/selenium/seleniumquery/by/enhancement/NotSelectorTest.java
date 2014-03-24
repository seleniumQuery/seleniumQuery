package org.openqa.selenium.seleniumquery.by.enhancement;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.seleniumquery.SeleniumQuery.$;

import org.junit.Test;
import org.openqa.selenium.seleniumquery.TestInfrastructure;

public class NotSelectorTest {

    @Test
    public void test() throws Exception {
        $.browser.setDefaultDriver(TestInfrastructure.getDriver());
        
        $.location.href(TestInfrastructure.getHtmlTestFileUrl(getClass()));
        
        assertThat($("div").length, is(3));
        assertThat($("div.c1").length, is(1));
        assertThat($("div.c2").length, is(1));
        assertThat($("div.c3").length, is(1));
        assertThat($("*:not(*)").length, is(0));
        assertThat($("div:not(.c1)").length, is(2));
        assertThat($("div:not(.c2)").length, is(2));
        assertThat($("div:not(.c3)").length, is(2));
        assertThat($("div:not(.w00t)").length, is(1));
    }

}