package org.openqa.selenium.seleniumquery.by.enhancement;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.seleniumquery.TestInfrastructure;

public class DisabledSelectorTest {

	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.location.href(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

    @Test
    public void enabled_selector() {
    	assertThat($("*").size(), is(27));
    	assertThat($(":disabled").size(), is(8));
    }
    
    @Test
    public void enabled_selector_with_not() {
    	assertThat($(":not(:disabled)").size(), is(19));
    }

    @Test
    public void enabled_selector_with_others() {
    	assertThat($("#enabledInput").is(":disabled"), is(false));
    	assertThat($("#disabledInput").is(":disabled"), is(true));
    	assertThat($("#enabledTextArea").is(":disabled"), is(false));
    	assertThat($("#disabledTextArea").is(":disabled"), is(true));
    }

}