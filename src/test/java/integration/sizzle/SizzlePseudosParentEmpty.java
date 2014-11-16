package integration.sizzle;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.Rule;
import org.junit.Test;

public class SizzlePseudosParentEmpty extends SizzleTest {

    @Rule
    public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SizzleTest.class);

    @Test
    public void pseudos_parent_and_empty() throws Exception {
        t("Empty", "ul:empty", new String[]{"firstUL"});
        t("Empty with comment node", "ol:empty", new String[]{"empty"});
        t("Is A Parent", "#qunit-fixture p:parent", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
    }

}