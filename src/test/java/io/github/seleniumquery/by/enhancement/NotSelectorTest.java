package io.github.seleniumquery.by.enhancement;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import io.github.seleniumquery.TestInfrastructure;

public class NotSelectorTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());

		$.location.href(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

    @Test
    public void test() throws Exception {
        assertThat($("div").size(), is(3));
        assertThat($("div.c1").size(), is(1));
        assertThat($("div.c2").size(), is(1));
        assertThat($("div.c3").size(), is(1));
        assertThat($("*:not(*)").size(), is(0));
        assertThat($("div:not(.c1)").size(), is(2));
        assertThat($("div:not(.c2)").size(), is(2));
        assertThat($("div:not(.c3)").size(), is(2));
        assertThat($("div:not(.w00t)").size(), is(1));
    }
    
    // jQuery returns: http://jsbin.com/miludaqe/1/edit
    @Test
    public void nesting_up_to_two_levels() {
        assertThat($("div.c3").size(), is(1));
        assertThat($("div:not(.c3)").size(), is(2));
        assertThat($("div:not(:not(.c3))").size(), is(1));
        assertThat($("div:not(:not(:not(.c3)))").size(), is(2));
    }

}