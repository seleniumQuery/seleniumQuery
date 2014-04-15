package org.openqa.selenium.seleniumquery.functions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.seleniumquery.TestInfrastructure;

public class HtmlFunctionTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());

		$.location.href(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

    @Test
    public void prop() throws Exception {
        assertThat($("body").html(),
        is("\n"+
		"    <div>Batman</div>\n"+
		"    <div>Spider Man</div>\n"+
		"    <div>Hulk</div>\n"+
		"    <span>yo</span>\n"+
		""));
    }

}