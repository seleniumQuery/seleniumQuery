package io.github.seleniumquery.functions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import io.github.seleniumquery.TestInfrastructure;

public class ValFunctionTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());

		$.location.href(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

	// http://jsbin.com/qoqoqugu/1/edit
    @Test
    public void val_get() throws Exception {
    	assertThat($("div").val(), is(""));
    	assertThat($("#opt").val(), is("a1"));
    	assertThat($("#s1").val(), is("a1"));
    	assertThat($("#ta").val(), is("bozo")); // jQuery is: "    bozo\n  "
    	assertThat($("#i1").val(), is("ii!"));
    	assertThat($("#r1").val(), is("ra!"));
    }

}