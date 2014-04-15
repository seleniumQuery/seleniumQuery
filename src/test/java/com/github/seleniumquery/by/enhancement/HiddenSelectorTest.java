package org.openqa.selenium.seleniumquery.by.enhancement;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.seleniumquery.TestInfrastructure;

public class HiddenSelectorTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.location.href(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

    @Test
    public void is_test() throws Exception {
    	assertThat($("#visibleDiv").is(":hidden"), is(false));
    	assertThat($("#visibleDiv2").is(":hidden"), is(false));
    	assertThat($("#invisibleDiv").is(":hidden"), is(true));
    	
    	assertThat($("#invisibleParentDiv").is(":hidden"), is(true));
    	assertThat($("#almostVisibleDiv").is(":hidden"), is(true));
    }

}