package io.github.seleniumquery.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;
import io.github.seleniumquery.selector.DriverSupportService;

import org.junit.Rule;
import org.junit.Test;

public class TextFunctionTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void text_function() {
    	if (DriverSupportService.isHtmlUnitDriverEmulatingIE($.browser.getDefaultDriver())) {
    		assertThat($("div.demo-container").text().replaceAll("\\s+", " "), is("Demonstration Box list item 1list item 2"));
    	} else if (DriverSupportService.isHtmlUnitDriver($.browser.getDefaultDriver())) {
    			assertThat($("div.demo-container").text(), is("Demonstration Box\nlist item 1 list item 2"));
    	} else {
    		assertThat($("div.demo-container").text(), is("Demonstration Box\nlist item 1\nlist item 2"));
    	}
    }
    
    @Test
    public void text_function__another_div() {
    	assertThat($("div.d").text(), is("Batman Spider Man yo Hulk"));
    }

}