package endtoend.functions.jquery.forms;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.ClassRule;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.junit.Assert.assertThat;

public class SubmitFunctionWithoutJSTest {

    @ClassRule
    public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SubmitFunctionWithoutJSTest.class);

    @Test
    public void submit_function_without_js() {
        String url = $.url();
        assertThat(url, endsWith("WithoutJSTest.html"));

        $("#input-a").submit();
        assertThat($.url(), endsWith("WithoutJSTest_2.html?aName=aValue"));

        $.url(url);
        $("#div-a").submit();
        assertThat($.url(), endsWith("WithoutJSTest_2.html?aName=aValue"));

        $.url(url);
        $("#input-b").submit();
        assertThat($.url(), endsWith("WithoutJSTest_3.html?bName=bValue"));

        $.url(url);
        $("#div-b").submit();
        assertThat($.url(), endsWith("WithoutJSTest_3.html?bName=bValue"));

        $.url(url);
        $("input").submit();
        assertThat($.url(), endsWith("WithoutJSTest_2.html?aName=aValue"));

        $.url(url);
        $("div").submit();
        assertThat($.url(), endsWith("WithoutJSTest_2.html?aName=aValue"));
    }

}