package endtoend.selectors.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

public class NotSelectorTest {

    @ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
    @Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;

    @Test
    public void notPseudoClass() throws Exception {
        assertThat($("div").size(), is(3));
        assertThat($("div.c1").size(), is(1));
        assertThat($("div.c2").size(), is(1));
        assertThat($("div.c3").size(), is(1));
        assertThat($("*:not(*)").size(), is(0));
        
        assertThat($("div:not(.c1)").size(), is(2));
        assertThat($(".c1").is("div:not(.c1)"), is(false));
        assertThat($(".c2").is("div:not(.c1)"), is(true));
        assertThat($(".c2").is("div:not(.c1)"), is(true));
        
        assertThat($("div:not(.c2)").size(), is(2));
        assertThat($("div:not(.c3)").size(), is(2));
        assertThat($("div:not(.w00t)").size(), is(1));
    }
    
    // http://jsbin.com/miludaqe/1/edit
    @Test
    public void nesting_up_to_two_levels() {
        assertThat($("div.c3").size(), is(1));
        assertThat($("div:not(.c3)").size(), is(2));
        assertThat($("div:not(:not(.c3))").size(), is(1));
        assertThat($("div:not(:not(:not(.c3)))").size(), is(2));
    }
    
    @Test
    public void notPseudoClass__with_selector_list() {
    	assertThat($("div:not(.c3,.c1)").size(), is(1));
    	assertThat($("div:not(.c3,.c1)").is(".c2"), is(true));
    }

}