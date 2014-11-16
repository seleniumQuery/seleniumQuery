package integration.sizzle;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.Rule;
import org.junit.Test;

public class SizzleMultipleComma extends SizzleTest {

    @Rule
    public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SizzleTest.class);

    @Test
    public void multiple_comma_separated_selectors() throws Exception {
        executeJS("jQuery(\"#qunit-fixture\").prepend(\"<h2 id='h2'/>\")");

        t("Comma Support", "#qunit-fixture h2, #qunit-fixture p", new String[]{"h2", "firstp", "ap", "sndp", "en", "sap", "first"});
        t("Comma Support", "#qunit-fixture h2 , #qunit-fixture p", new String[]{"h2", "firstp", "ap", "sndp", "en", "sap", "first"});
        t("Comma Support", "#qunit-fixture h2 , #qunit-fixture p", new String[]{"h2", "firstp", "ap", "sndp", "en", "sap", "first"});
        t("Comma Support", "#qunit-fixture h2,#qunit-fixture p", new String[]{"h2", "firstp", "ap", "sndp", "en", "sap", "first"});
        t("Comma Support", "#qunit-fixture h2,#qunit-fixture p ", new String[]{"h2", "firstp", "ap", "sndp", "en", "sap", "first"});
        t("Comma Support", "#qunit-fixture h2\t,\r#qunit-fixture p\n", new String[]{"h2", "firstp", "ap", "sndp", "en", "sap", "first"});
    }

}