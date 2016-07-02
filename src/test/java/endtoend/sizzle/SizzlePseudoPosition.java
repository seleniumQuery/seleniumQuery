/*
 * Copyright (c) 2016 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package endtoend.sizzle;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.junitrule.annotation.JavaScriptEnabledOnly;

public class SizzlePseudoPosition extends SizzleTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SizzleTest.class);

    @Test
    public void pseudo_position_selectors() {

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
    }

    @Test @JavaScriptEnabledOnly
    public void pseudo_position_selectors_2() {
        // jQuery #12526
        WebElement context = (WebElement) executeJS("return jQuery('#qunit-fixture').append(\"<div id='jquery12526'></div>\")[0]");
        deepEqual(Sizzle(":last", context), q("jquery12526"), "Post-manipulation positional");
    }

}
