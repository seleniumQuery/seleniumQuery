package io.github.seleniumquery.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.TestInfrastructure;

import org.junit.Before;
import org.junit.Test;

public class HasClassFunctionTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());

		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

    @Test
    public void hasClass() throws Exception {
    	assertThat($("#d1").hasClass("uglyClass"), is(false));
    	assertThat($("#d1").hasClass("c1"), is(true));
    	assertThat($("#d1").hasClass("c2"), is(true));
    	assertThat($("#d1").hasClass("c3"), is(true));
    	assertThat($("#d1").hasClass("c4"), is(true));
    	
    	assertThat($("#d2").hasClass("uglyClass"), is(false));
    	
    	// corner cases
    	assertThat($("#d1").hasClass(""), is(false));
    	assertThat($("#d1").hasClass(" "), is(false));
    	assertThat($("#d1").hasClass(null), is(false));
    	
    	assertThat($("#d2").hasClass(""), is(true));
    	assertThat($("#d2").hasClass(" "), is(false));
    	assertThat($("#d2").hasClass(null), is(false));
    	
    	assertThat($("#emptySet").hasClass(""), is(false));
    }

}