package io.github.seleniumquery.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class ValFunctionTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

	// http://jsbin.com/qoqoqugu/1/edit
    @Test
    public void val_function__getting() throws Exception {
    	assertThat($("div").val(), is(""));
    	assertThat($("#opt").val(), is("a1"));
    	assertThat($("#s1").val(), is("a1"));
    	assertThat($("#ta").val(), is("bozo")); // jQuery is: "    bozo\n  "
    	assertThat($("#i1").val(), is("ii!"));
    	assertThat($("#r1").val(), is("ra!"));
    }

}