package io.github.seleniumquery.functions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import io.github.seleniumquery.TestInfrastructure;

public class HtmlFunctionTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

    @Test
    public void html() {
        assertThat($("body").html(),
        is("\n"+
		"    <div>Batman</div>\n"+
		"    <div>Spider Man</div>\n"+
		"    <div>Hulk</div>\n"+
		"    <span>yo</span>\n"+
		""));
    }

}