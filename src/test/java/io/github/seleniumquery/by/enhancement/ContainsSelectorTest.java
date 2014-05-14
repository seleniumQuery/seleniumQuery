package io.github.seleniumquery.by.enhancement;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import io.github.seleniumquery.TestInfrastructure;

public class ContainsSelectorTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());

		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

	// http://jsbin.com/siwapeqe/1/edit
    @Test
    public void contains_pseudo() {
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
    }
    
    @Test
    public void contains_pseudo_jquery_escape_disagreement() {
    	// I think this should return 4, but jQuery said it was 2
    	// its another chapter from the escaping-problem movie
    	// leaving it as it is now
    	// TODO escaping contains
    	assertThat($("div:contains('a\\'b)c')").size(), is(4));
    }
    
    @Test
    public void contains_pseudo_alone() {
    	assertThat($("div:contains(abc)").size(), is(12));
    	assertThat($("body:contains(abc)").size(), is(1));
    	assertThat($("html:contains(abc)").size(), is(1));
    	assertThat($(":contains(abc)").size(), is(14));
    }

}