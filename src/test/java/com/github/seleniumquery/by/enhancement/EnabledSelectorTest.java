package org.openqa.selenium.seleniumquery.by.enhancement;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.seleniumquery.TestInfrastructure;

public class EnabledSelectorTest {

	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.location.href(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

    @Test
    public void enabled_selector() {
    	assertThat($("*").size(), is(27));
    	assertThat($(":enabled").size(), is(14));
    }
    
    @Test
    public void enabled_selector_with_not() {
    	assertThat($(":not(:enabled)").size(), is(13));
    }

    @Test
    public void enabled_selector_with_IS() {
    	assertThat($("#enabledInput").is(":enabled"), is(true));
    	assertThat($("#disabledInput").is(":enabled"), is(false));
    	assertThat($("#enabledTextArea").is(":enabled"), is(true));
    	assertThat($("#disabledTextArea").is(":enabled"), is(false));
    }

}