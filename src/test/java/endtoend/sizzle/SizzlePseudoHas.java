package endtoend.sizzle;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.Rule;
import org.junit.Test;

public class SizzlePseudoHas extends SizzleTest {

    @Rule
    public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SizzleTest.class);

    @Test
    public void pseudo_has() throws Exception {
        t("Basic test", "p:has(a)", new String[]{"firstp", "ap", "en", "sap"});
        t("Basic test (irrelevant whitespace)", "p:has( a )", new String[]{"firstp", "ap", "en", "sap"});
        t("Nested with overlapping candidates", "#qunit-fixture div:has(div:has(div:not([id])))", new String[]{"moretests", "t2037"});
    }

}