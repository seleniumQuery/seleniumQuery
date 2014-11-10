package integration.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SeleniumQueryObject;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class GetFunctionTest {
	
	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());

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
    
    @Test
    public void get_function__without_arguments__should_not_change_the_original_element_list() throws Exception {
    	SeleniumQueryObject $div = $("div");
    	
		assertThat($div.size(), is(3));
    	
    	List<WebElement> divs = $div.get();
    	
    	assertThat(divs.size(), is(3));
    	
    	divs.add($("span").get(0)); // adding extra
    	
    	assertThat(divs.get(3).getText(), is("yo"));
    	assertThat(divs.size(), is(4));
    	
    	assertThat($div.size(), is(3));
    }

}