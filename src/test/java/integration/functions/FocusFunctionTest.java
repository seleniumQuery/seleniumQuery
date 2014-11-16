package integration.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;
import io.github.seleniumquery.by.css.pseudoclasses.UnsupportedXPathPseudoClassException;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class FocusFunctionTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(getClass());

    @Test
    public void focus_function() {
    	$("#i1").focus();
    	assertThat($("#i1").is(":focus"), is(true));
    	
    	$("#bodyID").focus();
    	assertThat($("body").is(":focus"), is(true));
    	assertThat($("#bodyID").is(":focus"), is(true));
    	
    	$("#i0").focus();
    	assertThat($("#i0").is(":focus"), is(true));
    	
    	$("body").focus();
    	assertThat($("body").is(":focus"), is(true));
    	assertThat($("#bodyID").is(":focus"), is(true));
    	
    	$("#i2").focus();
    	assertThat($("#i2").is(":focus"), is(true));
    	
    	// html is not focusable on HtmlUnit, not even with tabindex!
    	// Due to that, there is not even the possibility of a workaround specific for HtmlUnitDriver.
    	// still, someone wanting to focus the html seems to be a highly unlikely operation, even more
    	// since the body IS focusable
    	$("html").focus();
    	if (!($.browser.getDefaultDriver() instanceof HtmlUnitDriver)) {
    		assertThat($("html").is(":focus"), is(true));
    	}

    	$("input").focus();
    	assertThat($("#i2").is(":focus"), is(true));
    	assertThat($("input").is(":focus"), is(true));
    }
    

    @Test
    public void focus_function__should_make_sure_the_elements_are_just_focused_and_NOT_clicked() {
    	// for each click or focus in those elements, there will be a div added to the body
    	// this tests makes sure only a div relative to the focus event is added
    	// (and not two divs, one for focus and one for click, if that happened, the it was a click+focus, not just focus)
    	
    	assertThat($("div").size(), is(0));
    	$("#i1").focus();
    	assertThat($("#i1").is(":focus"), is(true));
    	assertThat($("div").size(), is(1));
    	assertThat($("div.i1").size(), is(1));
    	assertThat($("div.i1.focus").size(), is(1));
    	
    	$("#i2").focus();
    	assertThat($("#i2").is(":focus"), is(true));
    	assertThat($("div").size(), is(2));
    	assertThat($("div.i1").size(), is(1));
    	assertThat($("div.i1.focus").size(), is(1));
    	assertThat($("div.i2").size(), is(1));
    	assertThat($("div.i2.focus").size(), is(1));
    	
    	$("#a1").focus();
    	assertThat($("#a1").is(":focus"), is(true));
    	assertThat($("div").size(), is(3));
    	assertThat($("div.i1").size(), is(1));
    	assertThat($("div.i2").size(), is(1));
    	assertThat($("div.a1").size(), is(1));
    	assertThat($("div.a1.focus").size(), is(1));

		// #failure org.openqa.selenium.ElementNotVisibleException: Element is not currently visible and so may not be interacted with
    	$("#im1").focus();
    	assertThat($("#im1").is(":focus"), is(true));

    	if (!($.browser.getDefaultDriver() instanceof HtmlUnitDriver)) {
	    	assertThat($("div").size(), is(4));
	    	assertThat($("div.i1").size(), is(1));
	    	assertThat($("div.i2").size(), is(1));
	    	assertThat($("div.a1").size(), is(1));
	    	assertThat($("div.im1").size(), is(1));
	    	assertThat($("div.im1.focus").size(), is(1));
    	} else {
    		// HtmlUnitDriver will focus, but won't trigger the focus EVENT for the <img> tag... :/
	    	assertThat($("div").size(), is(3));
	    	assertThat($("div.i1").size(), is(1));
	    	assertThat($("div.i2").size(), is(1));
	    	assertThat($("div.a1").size(), is(1));
    	}
    	
    	$("body").focus();
    	assertThat($("body").is(":focus"), is(true));
    	if (!($.browser.getDefaultDriver() instanceof HtmlUnitDriver)) {
	    	assertThat($("div").size(), is(5));
	    	assertThat($("div.i1").size(), is(1));
	    	assertThat($("div.i2").size(), is(1));
	    	assertThat($("div.a1").size(), is(1));
	    	assertThat($("div.im1").size(), is(1));
	    	assertThat($("div.body.focus").size(), is(1));
	    	assertThat($("div.body.click").size(), is(0));
    	} else {
    		// HtmlUnitDriver will focus, but won't trigger the focus event for the <body> tag either!
	    	assertThat($("div").size(), is(3));
	    	assertThat($("div.i1").size(), is(1));
	    	assertThat($("div.i2").size(), is(1));
	    	assertThat($("div.a1").size(), is(1));
    	}
    }
    
    @Test(expected = UnsupportedXPathPseudoClassException.class)
    public void focus_pseudoClass_selector() {
    	$("#i1").focus();
    	assertThat($("#i1").is(":focus"), is(true));
    	assertThat($(":focus").size(), is(1));
    	assertThat($(":focus").attr("id"), is("i1"));
    }
    
}