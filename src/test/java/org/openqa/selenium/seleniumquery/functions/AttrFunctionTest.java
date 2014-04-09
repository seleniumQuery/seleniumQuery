package org.openqa.selenium.seleniumquery.functions;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.seleniumquery.TestInfrastructure;

public class AttrFunctionTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());

		$.location.href(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

	// http://jsbin.com/lexuyesu/1/edit
    @Test
    public void prop() throws Exception {
    	assertThat($("#chk1").attr("checked"), is("")); // jQuery: "checked"
    	assertThat($("#chk1").prop("checked"), is("true"));
    	$("#chk1").prop("checked", true);
    	assertThat($("#chk1").attr("checked"), is("checked"));
    	assertThat($("#chk1").prop("checked"), is("true"));
    	$("#chk1").prop("checked", false);
    	assertThat($("#chk1").attr("checked"), is(nullValue())); // jQuery: "checked"
    	assertThat($("#chk1").prop("checked"), is("false"));

    	assertThat($("#chk1").attr("data-ball"), is("yo"));
    	assertThat($("#chk1").prop("data-ball"), is("yo")); // jQuery: nullValue()/undefined
    	$("#chk1").attr("data-ball", "abc");
    	assertThat($("#chk1").attr("data-ball"), is("abc"));
    }

}