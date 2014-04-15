package org.openqa.selenium.seleniumquery.functions;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.seleniumquery.TestInfrastructure;

public class RemoveAttrFunctionTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());

		$.location.href(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

	// http://jsbin.com/lexuyesu/1/edit
    @Test
    public void removeAttr() throws Exception {
    	assertThat($("#chk1").attr("data-ball"), is("yo"));
    	$("#chk1").attr("data-ball", "");
    	assertThat($("#chk1").attr("data-ball"), is(""));
    	$("#chk1").removeAttr("data-ball");
    	assertThat($("#chk1").attr("data-ball"), is(nullValue()));
    }

}