package integration.sizzle;

import infrastructure.junitrule.SetUpAndTearDownGivenDriver;
import org.junit.Rule;
import org.junit.Test;

public class SizzlePseudosParentEmpty extends SizzleTest {

    @Rule
    public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(SizzleTest.class);

    @Test
    public void pseudos_parent_and_empty() throws Exception {
        t("Empty", "ul:empty", new String[]{"firstUL"});
        t("Empty with comment node", "ol:empty", new String[]{"empty"});
        t("Is A Parent", "#qunit-fixture p:parent", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
    }

}