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
    public void prop_get() throws Exception {
        assertThat($("#chk_checked").<Boolean>prop("checked"), is(true));
        assertThat($("#chk_not_checked").<Boolean>prop("checked"), is(false));
        
        assertThat($("#rad_checked").<Boolean>prop("checked"), is(true));
        assertThat($("#rad_not_checked").<Boolean>prop("checked"), is(false));
        
        assertThat($("#opt_selected").prop("checked"), is(nullValue()));
        assertThat($("#opt_not_selected").prop("checked"), is(nullValue()));
        
        assertThat($("#chk_checked").prop("selected"), is(nullValue()));
        assertThat($("#chk_not_checked").prop("selected"), is(nullValue()));
        
        assertThat($("#rad_checked").prop("selected"), is(nullValue()));
        assertThat($("#rad_not_checked").prop("selected"), is(nullValue()));
        
        assertThat($("#opt_selected").<Boolean>prop("selected"), is(true));
        assertThat($("#opt_not_selected").<Boolean>prop("selected"), is(false));
    }
    
    // http://jsbin.com/ceqijima/2/edit
    @Test
    public void prop_set() throws Exception {
    	setAndExpect(true, true);
    	setAndExpect(1, true);
    	setAndExpect(0, false);
    	setAndExpect("1", true);
    	setAndExpect("0", true);
    	setAndExpect("a", true);
    	setAndExpect("", false);
    	setAndExpect(false, false);
    }

	private void reset() {
		$("#c1 .initial").prop("selected", true);
	}
	
	private void setAndExpect(Object val, Object expected) {
		reset();
		$("#c1 .other").prop("selected", val);
		assertThat($("#c1 .other").prop("selected"), is(expected));
	}

}