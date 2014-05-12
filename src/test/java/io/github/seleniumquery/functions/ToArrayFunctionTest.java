package io.github.seleniumquery.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.TestInfrastructure;

import org.junit.Before;
import org.junit.Test;

public class ToArrayFunctionTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());

		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

    @Test
    public void toArray() throws Exception {
    	assertThat($("#d1").toArray().length, is(1));
    	assertThat($("#d2").toArray().length, is(1));
    	assertThat($("#d3").toArray().length, is(1));
    	
    	assertThat($("div").toArray().length, is(3));
    	
    	assertThat($("h1").toArray().length, is(0));
    }

}