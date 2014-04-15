package org.openqa.selenium.seleniumquery.by.enhancement;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.seleniumquery.TestInfrastructure;

public class ContainsSelectorTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());

		$.location.href(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

	// http://jsbin.com/siwapeqe/1/edit
    @Test
    public void contains_pseudo() throws Exception {
    	assertThat($("div").size(), is(36));

    	assertThat($("div:contains(abc)").size(), is(12));
    	assertThat($("div:contains(\"abc\")").size(), is(12));
    	assertThat($("div:contains('abc')").size(), is(12));

    	assertThat($("div:contains(\"'abc'\")").size(), is(4));
    	assertThat($("div:contains('\"abc\"')").size(), is(4));

    	assertThat($("div:contains(\"a'bc\")").size(), is(4));
    	assertThat($("div:contains('a\"bc')").size(), is(4));

    	assertThat($("div:contains(\"ab)c\")").size(), is(4));
    	assertThat($("div:contains('ab)c')").size(), is(4));

    	assertThat($("div:contains(\"a'b)c\")").size(), is(4));
    	assertThat($("div:contains('a\"b)c')").size(), is(4));

    	assertThat($("div:contains(\"a\\\"b)c\")").size(), is(2));
    	assertThat($("div:contains('a\\'b)c')").size(), is(2));
    }

}