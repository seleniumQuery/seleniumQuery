package endtoend.selectors.pseudoclasses.form;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

public class DisabledSelectorTest {

    @ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
    @Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;

    @Test
    public void disabled_selector() {
    	assertThat($("*").size(), is(28));
    	assertThat($(":disabled").size(), is(8));
    }
    
    @Test
    public void  disabled_selector_with_not() {
    	assertThat($(":not(:disabled)").size(), is(20));
    }

    @Test
    public void  disabled_selector_with_IS() {
    	assertThat($("#enabledInput").is(":disabled"), is(false));
    	assertThat($("#disabledInput").is(":disabled"), is(true));
    	assertThat($("#enabledTextArea").is(":disabled"), is(false));
    	assertThat($("#disabledTextArea").is(":disabled"), is(true));
    }

}