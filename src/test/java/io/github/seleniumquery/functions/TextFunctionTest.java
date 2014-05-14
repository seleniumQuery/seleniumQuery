package io.github.seleniumquery.functions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import io.github.seleniumquery.TestInfrastructure;

public class TextFunctionTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

    @Test
    public void text() {
    	assertThat($("div.demo-container").text().replaceAll("\\s+", " "), is("Demonstration Box list item 1 list item 2"));
    }
    
    @Test
    public void text2() {
    	assertThat($("div.d").text(), is("Batman Spider Man yo Hulk"));
    }

}