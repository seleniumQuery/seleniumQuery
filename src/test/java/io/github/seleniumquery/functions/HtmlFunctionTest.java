package io.github.seleniumquery.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class HtmlFunctionTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void html_function__simple_element() {
    	assertThat($("span").html(), is("yo"));
    }
    
    @Test
    public void html_function__element_with_nested_element_with_no_line_breaks() {
    	assertThat($("div:eq(1)").html(), is("Spider <div>\"The Spidey\"</div> Man"));
    }
    
    @Test
    public void html_function__element_with_nested_element_with_line_breaks() {    	
    	assertThat($("#nestedMultiLine").html(), is("ABC\n"+
		"    	<div>DEF</div>\n"+
		"    	<div>GHI</div>\n"+
		"    	JKY\n"+
		"    "));
    }
    
    @Test
    public void html_function__body() {
        assertThat($("body").html(),
        is("\n"+
		"    <div>Batman</div>\n"+
		"    <div>Spider <div>\"The Spidey\"</div> Man</div>\n"+
		"    <div>Hulk</div>\n"+
		"    <span>yo</span>\n"+
		"    <div id=\"nestedMultiLine\">ABC\n"+
		"    	<div>DEF</div>\n"+
		"    	<div>GHI</div>\n"+
		"    	JKY\n"+
		"    </div>\n"));
    }

}