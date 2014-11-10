package integration.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import org.junit.Rule;
import org.junit.Test;

public class FindFunctionTest {

	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());
	
    @Test
    public void find_function() {
        assertThat($("#combo").find("option").size(), is(6));
        assertThat($("#combo").find("option:selected").size(), is(2));
        assertThat($("#combo").find("option:selected").get(0).getText(), is("Shrubs"));
        assertThat($("#combo").find(".non-existant-class").size(), is(0));
    }

}