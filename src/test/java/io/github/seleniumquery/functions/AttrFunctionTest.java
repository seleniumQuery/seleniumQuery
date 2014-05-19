package io.github.seleniumquery.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class AttrFunctionTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	// http://jsbin.com/lexuyesu/1/edit
    @Test
    public void attr_function__getting_and_setting() throws Exception {
    	assertThat($("#chk1").attr("checked"), is("")); // jQuery: "checked"
    	assertThat($("#chk1").<Boolean>prop("checked"), is(true));
    	$("#chk1").prop("checked", true);
    	assertThat($("#chk1").attr("checked"), is("checked"));
    	assertThat($("#chk1").<Boolean>prop("checked"), is(true));
    	$("#chk1").prop("checked", false);
    	assertThat($("#chk1").attr("checked"), is(nullValue())); // jQuery: "checked"
    	assertThat($("#chk1").<Boolean>prop("checked"), is(false));

    	assertThat($("#chk1").attr("data-ball"), is("yo"));
    	assertThat($("#chk1").<String>prop("data-ball"), is("yo")); // jQuery: nullValue()/undefined
    	$("#chk1").attr("data-ball", "abc");
    	assertThat($("#chk1").attr("data-ball"), is("abc"));
    }

}