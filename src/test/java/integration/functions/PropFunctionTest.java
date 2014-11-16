package integration.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class PropFunctionTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(getClass());

	// http://jsbin.com/zofekalo/1/edit
    @Test
    public void prop_function__getting() throws Exception {
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
    public void prop_function__setting() throws Exception {
    	setPropAndVerify(true, true);
//    	setPropAndVerify(1, true);
//    	setPropAndVerify(0, false);
//    	setPropAndVerify("1", true);
//    	setPropAndVerify("0", true);
//    	setPropAndVerify("a", true);
//    	setPropAndVerify("", false);
//    	setPropAndVerify(false, false);
    }
	
	private void setPropAndVerify(Object val, Object expected) {
//		reset();
		$("#c1 .other").prop("selected", val);
		assertThat($("#c1 .other").prop("selected"), is(expected));
	}
	
	private void reset() {
		$("#c1 .initial").prop("selected", true);
	}

}