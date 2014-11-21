package integration.selectors.pseudoclasses;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import io.github.seleniumquery.SeleniumQueryObject;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PresentSelectorTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(PresentSelectorTest.class);

    @Test
    public void  presentPseudoClass() {
        assertThat($("*").size(), is(9));
        assertThat($(":present").size(), is(9));

        // .is()
        assertThat($("#presentDiv").is(":present"), is(true));
        assertThat($("#otherPresentDiv").is(":present"), is(true));
        assertThat($("#otherPresentDiv").is(":not(:present)"), is(false));
        assertThat($("button").is(":present"), is(true));
        assertThat($("#bozo").is(":present"), is(false));
        assertThat($("#bozo").is(":not(:present)"), is(true));
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

}