package integration.sizzle;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.Rule;
import org.junit.Test;

import static infrastructure.IntegrationTestUtils.equal;

public class SizzlePseudosTargetAndRoot extends SizzleTest {

    @Rule
    public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SizzleTest.class);

    // TODO(issue#50) - add :target
    // :target
    //@Test
    public void target_pseudo() throws Exception {
        executeJS(" jQuery('<a>').attr({href: '#', id: 'new-link'}).appendTo('#qunit-fixture'); ");
        String oldHash = (String) executeJS("return window.location.hash;");
        executeJS("window.location.hash = 'new-link'");

        t(":target", ":target", new String[]{"new-link"});

        executeJS("jQuery('#new-link').remove();");
        executeJS("window.location.hash = arguments[0];", oldHash);
    }

    // :root
    @Test
    public void root_pseudo() throws Exception {
        Object documentElement = executeJS("return document.documentElement;");
        equal(Sizzle(":root").get(0), documentElement, ":root selector");
    }

}