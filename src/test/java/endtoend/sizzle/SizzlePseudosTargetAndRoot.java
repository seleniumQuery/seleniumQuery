package endtoend.sizzle;

import testinfrastructure.junitrule.JavaScriptOnly;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static testinfrastructure.IntegrationTestUtils.equal;

public class SizzlePseudosTargetAndRoot extends SizzleTest {

    @ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SizzleTest.class);
    @Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;

    // TODO(issue#50) - add :target
    // :target
    //@Test
    @SuppressWarnings("unused")
    public void target_pseudo() throws Exception {
        executeJS(" jQuery('<a>').attr({href: '#', id: 'new-link'}).appendTo('#qunit-fixture'); ");
        String oldHash = (String) executeJS("return window.location.hash;");
        executeJS("window.location.hash = 'new-link'");

        t(":target", ":target", new String[]{"new-link"});

        executeJS("jQuery('#new-link').remove();");
        executeJS("window.location.hash = arguments[0];", oldHash);
    }

    // :root
    @Test @JavaScriptOnly
    public void root_pseudo() throws Exception {
        Object documentElement = executeJS("return document.documentElement;");
        equal(Sizzle(":root").get(0), documentElement, ":root selector");
    }

}