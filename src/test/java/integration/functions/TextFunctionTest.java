package integration.functions;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import io.github.seleniumquery.selector.DriverSupportService;
import org.junit.ClassRule;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.selector.DriverSupportService.isHtmlUnitDriverEmulatingIEBelow11;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TextFunctionTest {
	
	@ClassRule
	public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(TextFunctionTest.class);

    @Test
    public void text_function() {
    	if (isHtmlUnitDriverEmulatingIEBelow11($.browser.getDefaultDriver())) {
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