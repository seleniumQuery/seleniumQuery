package integration.functions;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;

public class ValFunctionTest {
	
	@ClassRule
	public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(ValFunctionTest.class);

	// http://jsbin.com/futuhipuhi/2/edit?html,js,output
	// #failure SEVERAL FAILURES in SEVERAL browsers
	// TODO(issue#56)
    @Test
    public void val_function__setting() throws Exception {
		assertThat($("div").val(), is(""));
		$("div").val("SHOULD HAVE NO EFFECT");
		assertThat($("div").val(), is("SHOULD HAVE NO EFFECT")); // #disagree jquery sets the val() in a div, but we want as below!
	    //assertThat($("div").val(), is("")); // but we want this
		assertThat($("div").text(), is("BOZO"));

		/* <select> and <option> --------------------------------------------------------- */
		assertThat($("#s1").val(), is("a1"));

		assertThat($("#opt").val(), is("a1"));
		$("#opt").val("NEW-OPTION-VALUE");
		assertThat($("#opt").val(), is("NEW-OPTION-VALUE"));
		assertThat($("#s1").val(), is("NEW-OPTION-VALUE")); // as options's val() changed

		$("#s1").val("NEW-SELECT-VALUE");
		assertThat($("#s1").val(), is(nullValue())); // as there is no option with that value
		$("#s1").val("NEW-OPTION-VALUE");
		assertThat($("#s1").val(), is("NEW-OPTION-VALUE"));

		/* TEXTAREA --------------------------------------------------------- */
		assertThat($("#ta").val(), is("    bozo\n  ")); // Chrome/PhantomJS/IE10: "    bozo\n  "
		//assertThat($("#ta").val(), is("bozo")); // HtmlUnit/Firefox: "bozo"
		$("#ta").val("NEW-TEXTAREA-VALUE");
		assertThat($("#ta").val(), is("NEW-TEXTAREA-VALUE"));

		/* <input type="text"> --------------------------------------------------------- */
		assertThat($("#i1").val(), is("ii!"));
		$("#i1").val("NEW-INPUT-TEXT-VALUE");
		assertThat($("#i1").val(), is("NEW-INPUT-TEXT-VALUE"));

		/* <input type="radio"> --------------------------------------------------------- */
		assertThat($("#r1").val(), is("ra!"));
		$("#r1").val("NEW-RADIO-VALUE");
		assertThat($("#r1").val(), is("NEW-RADIO-VALUE"));
    }

}