package org.openqa.selenium.seleniumquery.by.enhancement;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.TestInfrastructure;

public class PresentSelectorTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.location.href(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

    @Test
    public void present_selector_basics() {
    	assertThat($("*").size(), is(9));
    	assertThat($(":present").size(), is(9));
    }
    
    @Test
    public void present_selector_while_removing_element_from_DOM() {
    	SeleniumQueryObject $presentDiv = $("#presentDiv");
    	SeleniumQueryObject $otherPresentDiv = $("#otherPresentDiv");
		assertThat($presentDiv.is(":present"), is(true));
		assertThat($otherPresentDiv.is(":present"), is(true));
		
    	$("button").click(); // removes the #presentDiv

    	assertThat($("#presentDiv").size(), is(0));
    	assertThat($("#presentDiv").is(":present"), is(false));
    	assertThat($("#presentDiv").is(":not(:present)"), is(true));
    	assertThat($("#otherPresentDiv").is(":present"), is(true));
    	
    	assertThat($presentDiv.is(":present"), is(false));
    	$presentDiv.is(":not(:present)");
    	assertThat($presentDiv.is(":not(:present)"), is(true));
    	assertThat($otherPresentDiv.is(":present"), is(true));
    }
    
    @Test
    public void  present_selector_with_IS1() {
    	assertThat($("#presentDiv").is(":present"), is(true));
    }
    
    @Test
    public void  present_selector_with_IS2() {
    	assertThat($("#otherPresentDiv").is(":present"), is(true));
    }
    
    @Test
    public void  present_selector_with_IS22() {
    	assertThat($("#otherPresentDiv").is(":not(:present)"), is(false));
    }
    
    @Test
    public void  present_selector_with_IS3() {
    	assertThat($("button").is(":present"), is(true));
    }
    
    @Test
    public void  present_selector_with_IS4() {
    	assertThat($("#bozo").is(":present"), is(false));
    }
    
    @Test
    public void  present_selector_with_IS5() {
    	assertThat($("#bozo").is(":not(:present)"), is(true));
    }

}