package io.github.seleniumquery.by.enhancement;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.TestInfrastructure;

import org.junit.Test;

public class EqSelectorTest {

    @Test
    public void testEqSelector() throws Exception {
        $.browser.setDefaultDriver(TestInfrastructure.getDriver());
        
        $.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
        
        assertThat($("div").size(), is(3));
        assertThat($("div.c1:eq(0)").text(), is("Batman"));
        assertThat($("div.c2:eq(0)").text(), is("Spider Man"));
        assertThat($("div.c3:eq(0)").text(), is("Hulk"));
        
        assertThat($("div.c1:eq(1)").text(), is(""));

        assertThat($("div:eq(0)").text(), is("Batman"));
        assertThat($("div:eq(1)").text(), is("Spider Man"));
        assertThat($("div:eq(2)").text(), is("Hulk"));
    }

}