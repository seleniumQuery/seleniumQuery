package io.github.seleniumquery.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class HasClassFunctionTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void hasClass_function() {
    	assertThat($("#d1").hasClass("uglyClass"), is(false));
    	assertThat($("#d1").hasClass("c1"), is(true));
    	assertThat($("#d1").hasClass("c2"), is(true));
    	assertThat($("#d1").hasClass("c3"), is(true));
    	assertThat($("#d1").hasClass("c4"), is(true));
    	
    	assertThat($("#d2").hasClass("uglyClass"), is(false));
    }    	

    @Test
    public void hasClass_function__corner_cases() {
    	assertThat($("#d1").hasClass(""), is(false));
    	assertThat($("#d1").hasClass(" "), is(false));
    	assertThat($("#d1").hasClass(null), is(false));
    	
    	assertThat($("#d2").hasClass(""), is(true));
    	assertThat($("#d2").hasClass(" "), is(false));
    	assertThat($("#d2").hasClass(null), is(false));
    	
    	assertThat($("#emptySet").hasClass(""), is(false));
    }

}