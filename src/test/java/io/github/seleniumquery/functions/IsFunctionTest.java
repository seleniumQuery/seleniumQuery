package io.github.seleniumquery.functions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import io.github.seleniumquery.TestInfrastructure;

public class IsFunctionTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.location.href(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

    @Test
    public void is_test() throws Exception {
    	assertThat($("#myDiv").is("div"), is(true));
        assertThat($("#myDiv").is(".classOne"), is(true));
        assertThat($("#myDiv").is(".classTwo"), is(true));
        assertThat($("#myDiv").is(":not(.classThree)"), is(true));
        assertThat($("#myDiv").is(".classOne.classTwo"), is(true));
        assertThat($("#myDiv").is(".classOne.classTwo:not(.classThree)"), is(true));
        
        assertThat($("#otherDiv").is(".classThree"), is(true));
        assertThat($("#otherDiv").is(":not(.classOne)"), is(true));
        
        assertThat($("#o1").is("option"), is(true));
        assertThat($("#o2").is("option"), is(true));
        assertThat($("#o1").is(":not(:selected)"), is(true));
        assertThat($("#o2").is(":selected"), is(true));
    }
    
	@Test
	public void is_with_no_matched_sets() {
		assertThat($("#mothafocka").is("#moo"), is(false));
	}
	
	@Test
	public void is_with_very_permissive_sets() {
		assertThat($("*").is("*"), is(true));
		assertThat($("div").is("div"), is(true)); 
		assertThat($("div").is("*"), is(true)); 
		assertThat($("*").is("div"), is(false));
	}
    
}