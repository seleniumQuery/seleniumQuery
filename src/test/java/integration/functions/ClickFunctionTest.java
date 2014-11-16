package integration.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class ClickFunctionTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(getClass());

    @Test
    public void click_function() {
    	assertThat($("div").size(), is(0));
    	
    	$("#i1").click();
    	assertThat($("div").size(), is(3));
    	assertThat($("div.i1").size(), is(2));
    	assertThat($("div.i1.click").size(), is(1));
    	assertThat($("div.i1.focus").size(), is(1));
    	assertThat($("div.body").size(), is(1));
    	assertThat($("div.body.click").size(), is(1));
    	
    	$("#i2").click();
    	assertThat($("div").size(), is(6));
    	assertThat($("div.i1").size(), is(2));
    	assertThat($("div.i1.click").size(), is(1));
    	assertThat($("div.i1.focus").size(), is(1));
    	assertThat($("div.i2").size(), is(2));
    	assertThat($("div.i2.click").size(), is(1));
    	assertThat($("div.i2.focus").size(), is(1));
    	assertThat($("div.body").size(), is(2));
    	assertThat($("div.body.click").size(), is(2));
    	
    	$("body").click();
    	assertThat($("div").size(), is(7));
    	assertThat($("div.i1").size(), is(2));
    	assertThat($("div.i2").size(), is(2));
    	assertThat($("div.body").size(), is(3));
    	assertThat($("div.body.focus").size(), is(0));
    	assertThat($("div.body.click").size(), is(3));
    }
    
}