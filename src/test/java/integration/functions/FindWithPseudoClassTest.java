package integration.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class FindWithPseudoClassTest {

	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(getClass());
	
    @Test
    public void find_function_with_pseudo_optionChecked() {
        assertThat($("#ano").size(), is(1));
        assertThat($("#ano").find("option:checked").text().trim(), is("(XYZ)"));
    }
    
}