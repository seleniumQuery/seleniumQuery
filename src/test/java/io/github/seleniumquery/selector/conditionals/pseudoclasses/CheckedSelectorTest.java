package io.github.seleniumquery.selector.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class CheckedSelectorTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
    
    @Test
    public void checked_selector() {
    	assertThat($("*").size(), is(14));
    	assertThat($(":checked").size(), is(4));
    }
    
    @Test
    public void  checked_selector_with_not() {
    	assertThat($(":not(:checked)").size(), is(10));
    }

    @Test
    public void  checked_selector_with_IS() {
    	assertThat($("#chk1").is(":checked"), is(true));
    	assertThat($("#chk2").is(":checked"), is(false));
    	
    	assertThat($("#rad1").is(":checked"), is(true));
    	assertThat($("#rad2").is(":checked"), is(false));
    	
    	assertThat($("#opt1").is(":checked"), is(true));
    	assertThat($("#opt2").is(":checked"), is(false));
    }

}