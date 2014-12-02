package integration.functions;

import infrastructure.junitrule.JavaScriptOnly;
import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ClickFunctionTest {

	@ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	@Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;

    @Test @JavaScriptOnly
    public void click_function() {
		WebDriver driver = $.driver().get();
		boolean isIE = driver instanceof InternetExplorerDriver;
		if (isIE) {
			// IE, when STARTING, focuses the <BODY> by itself, so a div is generated and we don't want it, as we are using
			// the number of generated divs in the test!
			for (WebElement div : $("div")) {
				((JavascriptExecutor) driver).executeScript("document.body.removeChild(arguments[0]);", div);
			}
		}

		assertThat($("div").size(), is(0));

    	$("#i1").click();
    	assertThat($("div").size(), is(3));
    	assertThat($("div.i1").size(), is(2));
    	assertThat($("div.i1.click").size(), is(1));
    	assertThat($("div.i1.focus").size(), is(1));
    	assertThat($("div.body").size(), is(1));
    	assertThat($("div.body.click").size(), is(1));
    	
    	$("#i2").click();
    	assertThat($("div").size(), is(6));
    	assertThat($("div.i1").size(), is(2));
    	assertThat($("div.i1.click").size(), is(1));
    	assertThat($("div.i1.focus").size(), is(1));
    	assertThat($("div.i2").size(), is(2));
    	assertThat($("div.i2.click").size(), is(1));
    	assertThat($("div.i2.focus").size(), is(1));
    	assertThat($("div.body").size(), is(2));
    	assertThat($("div.body.click").size(), is(2));
    	
    	$("body").click();
		assertThat($("div.i1").size(), is(2));
		assertThat($("div.i2").size(), is(2));
		// IE focuses <body> when clicking it, so it generates an additional DIV...
		if (!isIE) {
			assertThat($("div").size(), is(7));
			assertThat($("div.body").size(), is(3));
			assertThat($("div.body.focus").size(), is(0));
			assertThat($("div.body.click").size(), is(3));
		} else {
			assertThat($("div").size(), is(8));
			assertThat($("div.body").size(), is(4));
			assertThat($("div.body.focus").size(), is(1));
			assertThat($("div.body.click").size(), is(3));
		}
	}

}