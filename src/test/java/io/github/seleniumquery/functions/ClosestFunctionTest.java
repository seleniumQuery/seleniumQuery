package io.github.seleniumquery.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.TestInfrastructure;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class ClosestFunctionTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

    @Test
    public void is_test() throws Exception {
    	WebElement d1 = $("#d1").get(0);
    	WebElement d2 = $("#d2").get(0);
    	
    	assertThat($("#o1").closest("div").size(), is(1));
    	assertThat($("#o1").closest("div").get(0), is(d1));
    	
    	assertThat($("#s1").closest("div").size(), is(1));
    	assertThat($("#s1").closest("div").get(0), is(d1));
    	
    	assertThat($("#d1").closest("div").size(), is(1));
    	assertThat($("#d1").closest("div").get(0), is(d1));
    	
    	assertThat($("#o2").closest("div").size(), is(1));
    	assertThat($("#o2").closest("div").get(0), is(d2));
    	
    	assertThat($("#s2").closest("div").size(), is(1));
    	assertThat($("#s2").closest("div").get(0), is(d2));
    	
    	assertThat($("#d2").closest("div").size(), is(1));
    	assertThat($("#d2").closest("div").get(0), is(d2));
    	
    	assertThat($(".opt").closest("div").size(), is(2));
    	assertThat($(".opt").closest("div").get(0), is(d1));
    	assertThat($(".opt").closest("div").get(1), is(d2));
    	
    	assertThat($(".opt").closest("span").size(), is(0));
    	
    	assertThat($("select").closest("div").size(), is(2));
    	assertThat($("select").closest("div").get(0), is(d1));
    	assertThat($("select").closest("div").get(1), is(d2));
    	
    	assertThat($("select").closest("span").size(), is(0));
    }
    
}