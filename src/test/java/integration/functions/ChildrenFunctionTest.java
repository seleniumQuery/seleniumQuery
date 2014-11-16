package integration.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class ChildrenFunctionTest {

	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(getClass());
	
    @Test
    public void children_function() {
        assertThat($("#combo").children().size(), is(6));
        assertThat($("option").children().size(), is(0));
        
        assertThat($("select").children().size(), is(8));
    }
    
    @Test
    public void childrenSelector_function() {
        assertThat($("select").children("option").size(), is(8));
        assertThat($("select").children(":selected").size(), is(3));
        assertThat($("select").children("option:selected").size(), is(3));
        
        assertThat($("#combo").children("option").size(), is(6));
        assertThat($("#combo").children(":selected").size(), is(2));
        assertThat($("#combo").children("option:selected").size(), is(2));
        
        assertThat($("#combo").children(".non-existant-class").size(), is(0));
        assertThat($("select").children(".non-existant-class").size(), is(0));
        assertThat($("option").children(".non-existant-class").size(), is(0));
    }

}