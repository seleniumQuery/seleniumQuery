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

public class SizzlePseudoHas extends SizzleTest {

    @ClassRule @Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SizzleTest.class);

    @Test
    public void pseudo_has() throws Exception {
        t("Basic test", "p:has(a)", new String[]{"firstp", "ap", "en", "sap"});
        t("Basic test (irrelevant whitespace)", "p:has( a )", new String[]{"firstp", "ap", "en", "sap"});
        t("Nested with overlapping candidates", "#qunit-fixture div:has(div:has(div:not([id])))", new String[]{"moretests", "t2037"});
    }

}