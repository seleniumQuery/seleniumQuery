package io.github.seleniumquery.by.enhancement;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.Test;
import io.github.seleniumquery.TestInfrastructure;

public class CombinationEqNotSelectorTest {

    @Test
    public void testEqSelector() throws Exception {
        $.browser.setDefaultDriver(TestInfrastructure.getDriver());
        
        $.location.href(TestInfrastructure.getHtmlTestFileUrl(EqSelectorTest.class));
        
        assertThat($("div:eq(0):not(span)").text(), is("Batman"));
        assertThat($("div:eq(1):not(div)").text(), is(nullValue()));
        assertThat($("div:eq(1):not(.w00t)").text(), is("Spider Man"));
        assertThat($("div:eq(2):not(.w00t)").text(), is(nullValue()));
    }

}