package io.github.seleniumquery.selector.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class DisabledSelectorTest {

	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void disabled_selector() {
    	assertThat($("*").size(), is(27));
    	assertThat($(":disabled").size(), is(8));
    }
    
    @Test
    public void  disabled_selector_with_not() {
    	assertThat($(":not(:disabled)").size(), is(19));
    }

    @Test
    public void  disabled_selector_with_IS() {
    	assertThat($("#enabledInput").is(":disabled"), is(false));
    	assertThat($("#disabledInput").is(":disabled"), is(true));
    	assertThat($("#enabledTextArea").is(":disabled"), is(false));
    	assertThat($("#disabledTextArea").is(":disabled"), is(true));
    }

}