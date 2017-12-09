package endtoend.browser.browserfunctions;

import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.junitrule.annotation.JavaScriptEnabledOnly;

public class EvalFunctionTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    @JavaScriptEnabledOnly
    public void eval_arguments() {
        $("#new").assertThat().is(":not(:present)");
        $.eval("document.body.innerHTML += arguments[0]", "<div id='new'></div>");
        $("#new").assertThat().is(":present");
    }

    @Test
    @JavaScriptEnabledOnly
    public void eval_return() {
        long bodyCharCount = $.eval("return document.body.innerHTML.length");
        $("body").assertThat().html().matches(html -> html.length() == bodyCharCount);
    }

}
