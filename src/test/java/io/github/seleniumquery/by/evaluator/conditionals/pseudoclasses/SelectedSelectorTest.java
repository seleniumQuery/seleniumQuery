package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.Test;
import io.github.seleniumquery.TestInfrastructure;

public class SelectedSelectorTest {

    @Test
    public void test() throws Exception {
        $.browser.setDefaultDriver(TestInfrastructure.getDriver());
        
        $.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
        
        assertThat($("option").size(), is(6));
        assertThat($("option:selected").size(), is(2));
        assertThat($("option:selected").get(0).getText(), is("Shrubs"));
    }

}