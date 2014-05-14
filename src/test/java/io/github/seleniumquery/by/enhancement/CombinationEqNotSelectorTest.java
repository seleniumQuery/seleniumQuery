package io.github.seleniumquery.by.enhancement;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.TestInfrastructure;

import org.junit.Test;

public class CombinationEqNotSelectorTest {

    @Test
    public void testEqSelector() throws Exception {
        $.browser.setDefaultDriver(TestInfrastructure.getDriver());
        
        $.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(EqSelectorTest.class));
        
        assertThat($("div:eq(0):not(span)").text(), is("Batman"));
        assertThat($("div:eq(1):not(div)").text(), is(""));
        assertThat($("div:eq(1):not(.w00t)").text(), is("Spider Man"));
        assertThat($("div:eq(2):not(.w00t)").text(), is(""));
    }

}