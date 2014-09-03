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

	// http://jsbin.com/mihovu/1/edit
    @Test
    public void val_function__getting() throws Exception {
    	assertThat($("div").val(), is(""));
    	assertThat($("#opt").val(), is("a1"));
    	assertThat($("#s1").val(), is("a1"));
    	// Chrome/PhantomJS/IE10: "    bozo\n  "
    	// HtmlUnit/Firefox: "bozo"
    	assertThat($("#ta").val().trim(), is("bozo"));
    	assertThat($("#i1").val(), is("ii!"));
    	assertThat($("#r1").val(), is("ra!"));
    }
    
    @Test
    public void val_function__setting() throws Exception {
    	// when
    	assertThat($("div").val(), is(""));
    	assertThat($("#opt").val(), is("a1"));
    	assertThat($("#s1").val(), is("a1"));
    	// Chrome/PhantomJS/IE10: "    bozo\n  "
    	// HtmlUnit/Firefox: "bozo"
    	assertThat($("#ta").val().trim(), is("bozo"));
    	assertThat($("#i1").val(), is("ii!"));
    	assertThat($("#r1").val(), is("ra!"));

    	$("div").val("SHOULD HAVE NO EFFECT");
//    	$("#opt").val("NEW-OPTION-VALUE");
//    	$("#s1").val("NEW-SELECT-VALUE"); // throws exception as this element does not exist
    	$("#ta").val("NEW-TEXTAREA-VALUE");
    	$("#i1").val("NEW-INPUT-TEXT-VALUE");
//    	$("#r1").val("NEW-RADIO-VALUE");

    	// then
    	assertThat($("div").val(), is("")); // jquery returns the set
//    	assertThat($("#opt").val(), is("NEW-OPTION-VALUE"));
//    	assertThat($("#s1").val(), is(nullValue())); // the value is not set, so...
    	assertThat($("#ta").val(), is("NEW-TEXTAREA-VALUE"));
    	assertThat($("#i1").val(), is("NEW-INPUT-TEXT-VALUE"));
//    	assertThat($("#r1").val(), is("NEW-RADIO-VALUE"));
    }

}