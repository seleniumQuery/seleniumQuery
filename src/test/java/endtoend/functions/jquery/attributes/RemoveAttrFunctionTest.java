package endtoend.functions.jquery.attributes;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.junitrule.annotation.JavaScriptEnabledOnly;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class RemoveAttrFunctionTest {

	@ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	@Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;

	// http://jsbin.com/lexuyesu/1/edit
    @Test @JavaScriptEnabledOnly
    public void removeAttr_function() throws Exception {
		assertThat($("#chk1").attr("data-ball"), is("yo"));
    	$("#chk1").attr("data-ball", "");
    	assertThat($("#chk1").attr("data-ball"), is(""));
    	$("#chk1").removeAttr("data-ball");
    	assertThat($("#chk1").attr("data-ball"), is(nullValue()));
    }

}
