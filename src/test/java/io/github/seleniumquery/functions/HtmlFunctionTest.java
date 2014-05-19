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
    public void html_function() {
        assertThat($("body").html(),
        is("\n"+
		"    <div>Batman</div>\n"+
		"    <div>Spider Man</div>\n"+
		"    <div>Hulk</div>\n"+
		"    <span>yo</span>\n"+
		""));
    }

}