package integration.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import org.junit.Rule;
import org.junit.Test;

public class ToArrayFunctionTest {
	
	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());

    @Test
    public void toArray_function() throws Exception {
    	assertThat($("#d1").toArray().length, is(1));
    	assertThat($("#d2").toArray().length, is(1));
    	assertThat($("#d3").toArray().length, is(1));
    	
    	assertThat($("div").toArray().length, is(3));
    	
    	assertThat($("h1").toArray().length, is(0));
    }

}