package io.github.seleniumquery.by.enhancement;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.Test;
import io.github.seleniumquery.TestInfrastructure;

public class SelectedSelectorTest {

    @Test
    public void test() throws Exception {
        $.browser.setDefaultDriver(TestInfrastructure.getDriver());
        
        $.location.href(TestInfrastructure.getHtmlTestFileUrl(getClass()));
        
        assertThat($("option").size(), is(6));
        assertThat($("option:selected").size(), is(2));
        assertThat($("option:selected").text(), is("Shrubs"));
    }

}