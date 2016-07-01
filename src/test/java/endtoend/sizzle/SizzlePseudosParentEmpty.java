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

public class SizzlePseudosParentEmpty extends SizzleTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SizzleTest.class);

    @Test
    public void pseudos_parent_and_empty() {
        t("Empty", "ul:empty", new String[]{"firstUL"});
        t("Empty with comment node", "ol:empty", new String[]{"empty"});
        t("Is A Parent", "#qunit-fixture p:parent", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
    }

}
