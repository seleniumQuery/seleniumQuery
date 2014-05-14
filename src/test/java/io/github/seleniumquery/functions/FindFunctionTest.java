package io.github.seleniumquery.functions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.Test;
import io.github.seleniumquery.TestInfrastructure;

public class FindFunctionTest {

    @Test
    public void test() {
        $.browser.setDefaultDriver(TestInfrastructure.getDriver());
        
        $.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
        
        assertThat($("#combo").find("option").size(), is(6));
        assertThat($("#combo").find("option:selected").size(), is(2));
        assertThat($("#combo").find("option:selected").get(0).getText(), is("Shrubs"));
    }

}