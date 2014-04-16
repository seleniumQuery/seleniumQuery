package io.github.seleniumquery.by.enhancement;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.Test;
import io.github.seleniumquery.TestInfrastructure;

public class EqSelectorTest {

    @Test
    public void testEqSelector() throws Exception {
        $.browser.setDefaultDriver(TestInfrastructure.getDriver());
        
        $.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
        
        assertThat($("div").size(), is(3));
        assertThat($("div.c1:eq(0)").text(), is("Batman"));
        assertThat($("div.c2:eq(0)").text(), is("Spider Man"));
        assertThat($("div.c3:eq(0)").text(), is("Hulk"));
        
        assertThat($("div.c1:eq(1)").text(), is(nullValue()));

        assertThat($("div:eq(0)").text(), is("Batman"));
        assertThat($("div:eq(1)").text(), is("Spider Man"));
        assertThat($("div:eq(2)").text(), is("Hulk"));
    }

}