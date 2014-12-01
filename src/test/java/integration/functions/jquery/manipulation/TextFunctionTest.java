package integration.functions.jquery.manipulation;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.by.DriverVersionUtils.isHtmlUnitDriver;
import static io.github.seleniumquery.by.DriverVersionUtils.isHtmlUnitDriverEmulatingIEBelow11;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TextFunctionTest {
	
	@ClassRule
	public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(TextFunctionTest.class);

    @Test
    public void text_function() {
		WebDriver driver = $.driver().get();

		if (isHtmlUnitDriverEmulatingIEBelow11(driver)) {
    		assertThat($("div.demo-container").text().replaceAll("\\s+", " "), is("Demonstration Box list item 1list item 2"));
    	} else if (isHtmlUnitDriver(driver)) {
    			assertThat($("div.demo-container").text(), is("Demonstration Box\nlist item 1 list item 2"));
    	} else {
    		assertThat($("div.demo-container").text(), is("Demonstration Box\nlist item 1\nlist item 2"));
    	}

    	assertThat($("div.d").text(), is("Batman Spider Man yo Hulk"));

    	assertThat($("#myTextArea").text().trim(), is("Initial value for textarea"));
    	assertThat($("#myTextArea").val().trim(), is("Initial value for textarea"));
		$("#myTextArea").val("Typed stuff in textarea");

		// #Cross-Driver
		// this is no fix, just documenting the difference
		// IE (tested IE11) will make .text() follow the typed text. Other browsers will keep the original one.
		// This is OK, as it is the SAME behavior presented by jQuery!
		//
		// Well, HtmlUnit follows IE...
		if (driver instanceof InternetExplorerDriver || isHtmlUnitDriver(driver)) {
    		assertThat($("#myTextArea").text().trim(), is("Typed stuff in textarea"));
		} else {
			assertThat($("#myTextArea").text().trim(), is("Initial value for textarea"));
		}
    	assertThat($("#myTextArea").val().trim(), is("Typed stuff in textarea"));
	}

}