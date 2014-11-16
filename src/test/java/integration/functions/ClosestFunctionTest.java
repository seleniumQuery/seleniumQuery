package integration.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class ClosestFunctionTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(getClass());

    @Test
    public void closest_function() throws Exception {
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