package io.github.seleniumquery.functions;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import io.github.seleniumquery.TestInfrastructure;

public class AttrFunctionTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());

		$.location.href(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

	// http://jsbin.com/lexuyesu/1/edit
    @Test
    public void attr_get_and_set() throws Exception {
    	assertThat($("#chk1").attr("checked"), is("")); // jQuery: "checked"
    	assertThat($("#chk1").<Boolean>prop("checked"), is(true));
    	$("#chk1").prop("checked", true);
    	assertThat($("#chk1").attr("checked"), is("checked"));
    	assertThat($("#chk1").<Boolean>prop("checked"), is(true));
    	$("#chk1").prop("checked", false);
    	assertThat($("#chk1").attr("checked"), is(nullValue())); // jQuery: "checked"
    	assertThat($("#chk1").<Boolean>prop("checked"), is(false));

    	assertThat($("#chk1").attr("data-ball"), is("yo"));
    	assertThat($("#chk1").<String>prop("data-ball"), is("yo")); // jQuery: nullValue()/undefined
    	$("#chk1").attr("data-ball", "abc");
    	assertThat($("#chk1").attr("data-ball"), is("abc"));
    }

}