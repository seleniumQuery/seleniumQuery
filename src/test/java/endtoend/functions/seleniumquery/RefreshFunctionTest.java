package endtoend.functions.seleniumquery;

import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;

import io.github.seleniumquery.SeleniumQueryObject;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.junitrule.annotation.JavaScriptEnabledOnly;

public class RefreshFunctionTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    @JavaScriptEnabledOnly
    public void refresh__updates__size() {
        SeleniumQueryObject sqo = $("div");
        sqo.assertThat().size().isEqualTo(2);
        ((JavascriptExecutor) $.driver().get()).executeScript("document.body.removeChild(document.getElementById('d2'))");
        sqo.assertThat().size().isEqualTo(2);
        $("div").assertThat().size().isEqualTo(1);
        sqo.refresh().assertThat().size().isEqualTo(1);
        sqo.assertThat().size().isEqualTo(1);
    }

}
