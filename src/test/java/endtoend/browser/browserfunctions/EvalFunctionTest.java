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
        long childrenCount = $.eval("return document.body.children.length");
        $("body *").assertThat().size().matches(size -> size == childrenCount);
    }

}
