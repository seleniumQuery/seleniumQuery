package integration.sizzle;

import infrastructure.junitrule.SetUpAndTearDownGivenDriver;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class SizzlePseudoPosition extends SizzleTest {

    @Rule
    public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(SizzleTest.class);

    @Test
    public void pseudo_position_selectors() throws Exception {

        t("First element", "#qunit-fixture p:first", new String[]{"firstp"});
        t("First element(case-insensitive)", "#qunit-fixture p:fiRst", new String[]{"firstp"});
// TODO(issue#27) - add :nth
//        t("nth Element", "#qunit-fixture p:nth(1)", new String[]{"ap"});
        t("First Element", "#qunit-fixture p:first", new String[]{"firstp"});
        t("Last Element", "p:last", new String[]{"first"});
        t("Even Elements", "#qunit-fixture p:even", new String[]{"firstp", "sndp", "sap"});
        t("Odd Elements", "#qunit-fixture p:odd", new String[]{"ap", "en", "first"});
        t("Position Equals", "#qunit-fixture p:eq(1)", new String[]{"ap"});
        t("Position Equals (negative)", "#qunit-fixture p:eq(-1)", new String[]{"first"});
        t("Position Greater Than", "#qunit-fixture p:gt(0)", new String[]{"ap", "sndp", "en", "sap", "first"});
        t("Position Less Than", "#qunit-fixture p:lt(3)", new String[]{"firstp", "ap", "sndp"});

        t("Check position filtering", "div#nothiddendiv:eq(0)", new String[]{"nothiddendiv"});
        t("Check position filtering", "div#nothiddendiv:last", new String[]{"nothiddendiv"});
//        t("Check position filtering", "div#nothiddendiv:not(:gt(0))", new String[]{"nothiddendiv"});
        t("Check position filtering", "#foo > :not(:first)", new String[]{"en", "sap"});
//        t("Check position filtering", "#qunit-fixture select > :not(:gt(2))", new String[]{"option1a", "option1b", "option1c"});
//        t("Check position filtering", "#qunit-fixture select:lt(2) :not(:first)", new String[]{"option1b", "option1c", "option1d", "option2a", "option2b", "option2c", "option2d"});
        t("Check position filtering", "div.nothiddendiv:eq(0)", new String[]{"nothiddendiv"});
        t("Check position filtering", "div.nothiddendiv:last", new String[]{"nothiddendiv"});
        t("Check position filtering", "div.nothiddendiv:not(:lt(0))", new String[]{"nothiddendiv"});

        t("Check element position", "#qunit-fixture div div:eq(0)", new String[]{"nothiddendivchild"});
        t("Check element position", "#select1 option:eq(3)", new String[]{"option1d"});
        t("Check element position", "#qunit-fixture div div:eq(10)", new String[]{"names-group"});
        t("Check element position", "#qunit-fixture div div:first", new String[]{"nothiddendivchild"});
        t("Check element position", "#qunit-fixture div > div:first", new String[]{"nothiddendivchild"});
        t("Check element position", "#dl div:first div:first", new String[]{"foo"});
        t("Check element position", "#dl div:first > div:first", new String[]{"foo"});
        t("Check element position", "div#nothiddendiv:first > div:first", new String[]{"nothiddendivchild"});
        t("Chained pseudo after a pos pseudo", "#listWithTabIndex li:eq(0):contains(Rice)", new String[]{"foodWithNegativeTabIndex"});

        tio("Check sort order with POS and comma", "#qunit-fixture em>em>em>em:first-child,div>em:first", new String[]{"siblingfirst", "siblinggreatgrandchild"});

        t("Isolated position", "#qunit-fixture :last", new String[]{"last"});

//        deepEqual(Sizzle("*:lt(2) + *", null, null, Sizzle("#qunit-fixture > p").get()), q("ap"), "Seeded pos with trailing relative");

        // jQuery #12526
        WebElement context = (WebElement) executeJS("return jQuery('#qunit-fixture').append(\"<div id='jquery12526'></div>\")[0]");
        deepEqual(Sizzle(":last", context), q("jquery12526"), "Post-manipulation positional");
    }

}