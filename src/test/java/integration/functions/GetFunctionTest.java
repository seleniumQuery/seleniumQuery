package integration.functions;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GetFunctionTest {
	
	@ClassRule
	public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(GetFunctionTest.class);

    @Test
    public void get_function__with_index_arg() throws Exception {
        assertThat($("div").size(), is(3));
        assertThat($("div").get(0).getText(), is("Batman"));
        assertThat($("div").get(1).getText(), is("Spider Man"));
        assertThat($("div").get(2).getText(), is("Hulk"));
    }

    @Test
    public void get_function__without_arguments() throws Exception {
    	assertThat($("div").size(), is(3));

    	List<WebElement> divs = $("div").get();

    	assertThat(divs.size(), is(3));

        assertThat(divs.get(0).getText(), is("Batman"));
        assertThat(divs.get(1).getText(), is("Spider Man"));
        assertThat(divs.get(2).getText(), is("Hulk"));
    }

    @Test(expected = java.lang.UnsupportedOperationException.class)
    public void get_function__without_arguments__should_return_an_immutable_list() throws Exception {
        // given
        List<WebElement> elements = $("div").get();
        WebElement otherWebElement = $("span").get(0);
        // when
        elements.add(otherWebElement);
        // then
        // should throw exception as the returned list should be immutable
    }

}