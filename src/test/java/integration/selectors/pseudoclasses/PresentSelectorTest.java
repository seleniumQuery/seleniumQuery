package integration.selectors.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SeleniumQueryObject;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import org.junit.Rule;
import org.junit.Test;

public class PresentSelectorTest {
	
	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());

    @Test
    public void presentPseudoClass_basics() {
    	assertThat($("*").size(), is(9));
    	assertThat($(":present").size(), is(9));
    }
    
    @Test
    public void presentPseudoClass_while_removing_element_from_DOM() {
    	SeleniumQueryObject $presentDiv = $("#presentDiv");
    	SeleniumQueryObject $otherPresentDiv = $("#otherPresentDiv");
		assertThat($presentDiv.is(":present"), is(true));
		assertThat($otherPresentDiv.is(":present"), is(true));
		
    	$("button").click(); // removes the #presentDiv

    	assertThat($("#presentDiv").size(), is(0));
    	assertThat($("#presentDiv").is(":present"), is(false));
    	assertThat($("#presentDiv").is(":not(:present)"), is(true));
    	assertThat($("#otherPresentDiv").is(":present"), is(true));
    	
    	assertThat($presentDiv.is(":present"), is(false));
    	$presentDiv.is(":not(:present)");
    	assertThat($presentDiv.is(":not(:present)"), is(true));
    	assertThat($otherPresentDiv.is(":present"), is(true));
    }
    
    @Test
    public void  presentPseudoClass_with_IS1() {
    	assertThat($("#presentDiv").is(":present"), is(true));
    }
    
    @Test
    public void  presentPseudoClass_with_IS2() {
    	assertThat($("#otherPresentDiv").is(":present"), is(true));
    }
    
    @Test
    public void  presentPseudoClass_with_IS22() {
    	assertThat($("#otherPresentDiv").is(":not(:present)"), is(false));
    }
    
    @Test
    public void  presentPseudoClass_with_IS3() {
    	assertThat($("button").is(":present"), is(true));
    }
    
    @Test
    public void  presentPseudoClass_with_IS4() {
    	assertThat($("#bozo").is(":present"), is(false));
    }
    
    @Test
    public void  presentPseudoClass_with_IS5() {
    	assertThat($("#bozo").is(":not(:present)"), is(true));
    }

}