package endtoend.functions.seleniumquery;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.assertEquals;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.junitrule.annotation.JavaScriptEnabledOnly;

public class EvalFunctionTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    @JavaScriptEnabledOnly
    public void eval_arguments() {
        $("#new").assertThat().is(":not(:present)");
        $("#existing").eval("document.body.innerHTML += '<div id=\"new\">' + arguments[0][0].textContent + '|' + arguments[1] + '</div>'", "second");
        System.out.println($("#new").text());
        $("#new").assertThat().is(":present").and().text().isEqualTo("seleniumQuery|second");
    }

    @Test
    @JavaScriptEnabledOnly
    public void eval_return() {
        WebElement existingDiv = $("#existing").eval("return arguments[0][0]");
        assertEquals("seleniumQuery", existingDiv.getText());
    }

}
