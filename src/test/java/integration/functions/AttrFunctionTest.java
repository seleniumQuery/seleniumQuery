package integration.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class AttrFunctionTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(getClass());
	
	// http://jsbin.com/pupoj/5/edit
    @Test
    public void attr_function__getting_and_setting_whatever_to_CHECKED_is_like_setting_to_CHECKED_string() {
    	// whatever value checked is, if it exists, attr returns "checked"
    	assertThat($("#x1").attr("checked"), is("checked"));
    	assertThat($("#x2").attr("checked"), is("checked"));
    	assertThat($("#x3").attr("checked"), is("checked"));
    	assertThat($("#x4").attr("checked"), is("checked"));
    	assertThat($("#x5").attr("checked"), is("checked"));
    	assertThat($("#x6").attr("checked"), is("checked"));

    	// when it doesn't, attr returns undefined
    	assertThat($("#n1").attr("checked"), is(nullValue()));

    	// when you set any value to checked, it sets "checked"
    	$("#n1").removeAttr("checked"); // reset
//    	$("#n1").attr("checked", null);
    	assertThat($("#n1").attr("checked"), is(nullValue()));

    	$("#n1").removeAttr("checked"); // reset
    	$("#n1").attr("checked", "");
    	assertThat($("#n1").attr("checked"), is("checked"));

    	$("#n1").removeAttr("checked"); // reset
    	$("#n1").attr("checked", "checked");
    	assertThat($("#n1").attr("checked"), is("checked"));

    	$("#n1").removeAttr("checked"); // reset
    	$("#n1").attr("checked", "true");
    	assertThat($("#n1").attr("checked"), is("checked"));

    	$("#n1").removeAttr("checked"); // reset
    	$("#n1").attr("checked", "false");
    	assertThat($("#n1").attr("checked"), is("checked"));

    	$("#n1").removeAttr("checked"); // reset
    	$("#n1").attr("checked", "abc");
    	assertThat($("#n1").attr("checked"), is("checked"));
    }
    
    @Test
    public void attr_function__does_the_same_CHECKED_stuff_to_SELECTED_no_matter_what_tag() {
    	// no matter what tag, the "checked" policy remains!
    	$("#d1").attr("checked", "");
    	assertThat($("#d1").attr("checked"), is("checked"));
    	$("#d1").attr("checked", "yo");
    	assertThat($("#d1").attr("checked"), is("checked"));

    	// the same is valid to "selected"
    	$("#d1").attr("selected", "");
    	assertThat($("#d1").attr("selected"), is("selected"));
    	$("#d1").attr("selected", "yo");
    	assertThat($("#d1").attr("selected"), is("selected"));
    }
    
    @Test
    public void attr_function__getting_and_setting() {
    	assertThat($("#chk1").attr("checked"), is("checked"));
    	assertThat($("#chk1").<Boolean>prop("checked"), is(true));
    	
    	$("#chk1").prop("checked", true);
    	assertThat($("#chk1").<Boolean>prop("checked"), is(true));
    	assertThat($("#chk1").attr("checked"), is("checked"));
    	$("#chk1").prop("checked", false);
    	
    	// in other browsers, this returns "checked"
    	// in HtmlUnitDriver, this returns null, because the attr follows the value of the property (when it shouldnt!)
    	// There is no possible workaround for this, as to that we would have to store the value of the attribute
    	// before it was changed via property. We cant do that, since the value can be changed from means that are
    	// not seleniumQuery, thus we cant track it!
    	// We added a warning in the .attr() function, though!
    	if ($.browser.getDefaultDriver() instanceof HtmlUnitDriver) {
    		assertThat($("#chk1").attr("checked"), is(nullValue()));
    	} else {
    		assertThat($("#chk1").attr("checked"), is("checked"));
    	}
    	assertThat($("#chk1").<Boolean>prop("checked"), is(false));

    	assertThat($("#chk1").attr("data-ball"), is("yo"));
    	if ($.browser.getDefaultDriver() instanceof HtmlUnitDriver) {
    		// Properties follow the value of the attribute in HtmlUnitDriver. They shouldn't! This should be NULL!
    		assertThat($("#chk1").<String>prop("data-ball"), is("yo"));
    	} else {
    		assertThat($("#chk1").<String>prop("data-ball"), is(nullValue()));
    	}
    	
    	$("#chk1").attr("data-ball", "abc");
    	assertThat($("#chk1").attr("data-ball"), is("abc"));
    }

}