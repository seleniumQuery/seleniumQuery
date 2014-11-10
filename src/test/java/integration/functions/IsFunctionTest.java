package integration.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import org.junit.Rule;
import org.junit.Test;

public class IsFunctionTest {
	
	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());

    @Test
    public void is_function() {
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
        
        assertThat($("#o1").is(":not(:not(:not(:selected)))"), is(true));
        assertThat($("#o2").is(":not(:not(:selected))"), is(true));
    }
    
	@Test
	public void is_function__with_no_matched_sets() {
		assertThat($("#mothafocka").is("#moo"), is(false));
	}
	
	@Test
	public void is_function__with_very_permissive_sets() {
		assertThat($("*").is("*"), is(true));
		assertThat($("div").is("div"), is(true)); 
		assertThat($("div").is("*"), is(true)); 
		assertThat($("*").is("div"), is(true));
	}
    
}