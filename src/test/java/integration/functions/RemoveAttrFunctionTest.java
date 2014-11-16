package integration.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class RemoveAttrFunctionTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(getClass());

	// http://jsbin.com/lexuyesu/1/edit
    @Test
    public void removeAttr_function() throws Exception {
    	assertThat($("#chk1").attr("data-ball"), is("yo"));
    	$("#chk1").attr("data-ball", "");
    	assertThat($("#chk1").attr("data-ball"), is(""));
    	$("#chk1").removeAttr("data-ball");
    	assertThat($("#chk1").attr("data-ball"), is(nullValue()));
    }

}