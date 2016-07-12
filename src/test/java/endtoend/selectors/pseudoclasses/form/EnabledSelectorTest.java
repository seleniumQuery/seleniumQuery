package endtoend.selectors.pseudoclasses.form;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class EnabledSelectorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void enabled_selector() {
    	assertThat($("*").size(), is(27));
    	assertThat($(":enabled").size(), is(14));
    }

    @Test
    public void enabled_selector_with_not() {
    	assertThat($(":not(:enabled)").size(), is(13));
    }

    @Test
    public void enabled_selector_with_IS() {
    	assertThat($("#enabledInput").is(":enabled"), is(true));
    	assertThat($("#disabledInput").is(":enabled"), is(false));
    	assertThat($("#enabledTextArea").is(":enabled"), is(true));
    	assertThat($("#disabledTextArea").is(":enabled"), is(false));
    }

    @Test
    public void enabled_selector_with_visible() {
    	assertThat($("#enabledInput").is(":visible:enabled"), is(true));
    }

}
