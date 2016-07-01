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
import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.junitrule.annotation.JavaScriptOnly;

public class SizzleMultipleComma extends SizzleTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SizzleTest.class);

    @Test @JavaScriptOnly
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
