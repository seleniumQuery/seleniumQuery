package io.github.seleniumquery.by.enhancement;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import io.github.seleniumquery.TestInfrastructure;

public class EnabledSelectorTest {

	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
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