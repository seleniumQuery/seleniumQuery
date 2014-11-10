package integration.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import org.junit.Rule;
import org.junit.Test;

public class EqFunctionTest {

	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());
	
	@Test
	public void eq_function_with_tag_div() {
		assertThat($("div").eq(0).size(), is(1));
		assertThat($("div").eq(4).size(), is(0));
		assertThat($("div").eq(99).size(), is(0));
	}
	
	@Test
	public void eq_function_with_class() {
		assertThat($(".c1").eq(0).size(), is(1));
		assertThat($(".c1").eq(1).size(), is(0));
		assertThat($(".c1").eq(99).size(), is(0));
		
		assertThat($(".w00t").eq(0).size(), is(1));
		assertThat($(".w00t").eq(2).size(), is(1));
		assertThat($(".w00t").eq(3).size(), is(0));
	}
	
	@Test
	public void eq_function_with_tag__INDIVIDUAL_CHECK() {
		assertThat($("div").eq(0).text(), is("Batman"));
		assertThat($("div").eq(1).text(), is("Spider Man"));
		assertThat($("div").eq(2).text(), is("Hulk"));
		assertThat($("div").eq(3).text(), is("Bozo"));
		
		assertThat($("div").eq(+0).text(), is("Batman"));
		assertThat($("div").eq(+1).text(), is("Spider Man"));
		assertThat($("div").eq(+2).text(), is("Hulk"));
		assertThat($("div").eq(+3).text(), is("Bozo"));
		
		assertThat($("div").eq(-0).text(), is("Batman"));
		assertThat($("div").eq(-4).text(), is("Batman"));
		assertThat($("div").eq(-3).text(), is("Spider Man"));
		assertThat($("div").eq(-2).text(), is("Hulk"));
		assertThat($("div").eq(-1).text(), is("Bozo"));
	}
	
	@Test
	public void eq_function() {
		assertThat($("*").eq(0).size(), is(1));
		assertThat($("*").eq(99).size(), is(0));
		assertThat($("*").eq(-99).size(), is(0));
	}
	
	@Test
	public void eq_function_with_tag_and_class() {
        assertThat($("div.c1").eq(0).text(), is("Batman"));
        assertThat($("div.c2").eq(0).text(), is("Spider Man"));
        assertThat($("div.c3").eq(0).text(), is("Hulk"));
	}
	
	@Test
	public void eq_function_with_tag_and_class__and_single_set_with_negative_index() {
        assertThat($("div.c1").eq(-1).text(), is("Batman"));
        assertThat($("div.c2").eq(-1).text(), is("Spider Man"));
        assertThat($("div.c3").eq(-1).text(), is("Hulk"));
	}
	
	@Test
	public void eq_function__invalid_index_with_tag_and_class() {
        assertThat($("div.c1").eq(1).text(), is(""));
    }
	
	// As .first() function uses .eq(), we put the test here (instead of creating whole new .java and .html files)
	@Test
	public void first_function() {
		assertThat($("div").first().text(), is("Batman"));
		assertThat($("div.c2").first().text(), is("Spider Man"));
		assertThat($("hr").first().text(), is(""));
    }
	
	// As .last() function uses .eq(), we put the test here (instead of creating whole new .java and .html files)
	@Test
	public void last_function() {
		assertThat($("div").last().text(), is("Bozo"));
		assertThat($("div.c2").last().text(), is("Spider Man"));
		assertThat($("hr").last().text(), is(""));
	}
    
}