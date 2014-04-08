package org.openqa.selenium.seleniumquery.functions;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.seleniumquery.TestInfrastructure;

public class PropFunctionTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());

		$.location.href(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

	// http://jsbin.com/zofekalo/1/edit
    @Test
    public void prop() throws Exception {
        assertThat($("#chk_checked").prop("checked"), is("true"));
        assertThat($("#chk_not_checked").prop("checked"), is("false"));
        
        assertThat($("#rad_checked").prop("checked"), is("true"));
        assertThat($("#rad_not_checked").prop("checked"), is("false"));
        
        assertThat($("#opt_selected").prop("checked"), is(nullValue()));
        assertThat($("#opt_not_selected").prop("checked"), is(nullValue()));
        
        assertThat($("#chk_checked").prop("selected"), is(nullValue()));
        assertThat($("#chk_not_checked").prop("selected"), is(nullValue()));
        
        assertThat($("#rad_checked").prop("selected"), is(nullValue()));
        assertThat($("#rad_not_checked").prop("selected"), is(nullValue()));
        
        assertThat($("#opt_selected").prop("selected"), is("true"));
        assertThat($("#opt_not_selected").prop("selected"), is("false"));
    }

}