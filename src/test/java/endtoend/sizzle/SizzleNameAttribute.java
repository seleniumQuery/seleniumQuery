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

public class SizzleNameAttribute extends SizzleTest {

    @ClassRule @Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SizzleTest.class);

    @Test
    public void name_attribute_selectors() {
        t("Name selector", "input[name=action]", new String[]{"text1"});
        t("Name selector with single quotes", "input[name='action']", new String[]{"text1"});
        t("Name selector with double quotes", "input[name=\"action\"]", new String[]{"text1"});

        t("Name selector non-input", "[name=example]", new String[]{"name-is-example"});
        t("Name selector non-input", "[name=div]", new String[]{"name-is-div"});
        t("Name selector non-input", "*[name=iframe]", new String[]{"iframe"});

        t("Name selector for grouped input", "input[name='types[]']", new String[]{"types_all", "types_anime", "types_movie"});

/*
            WebElement form = q("form").get(0);
            deepEqual(Sizzle("input[name=action]", form), q("text1"), "Name selector within the context of another element");
            deepEqual(Sizzle("input[name='foo[bar]']", form), q("hidden2"), "Name selector for grouped form element within the context of another element");

            form = jQuery("<form><input name='id'/></form>").appendTo("body");
            equal(Sizzle("input", form[0]).size(), 1, "Make sure that rooted queries on forms (with possible expandos) work.");
            form.remove();
*/

        t("Find elements that have similar IDs", "[name=tName1]", new String[]{"tName1ID"});
        t("Find elements that have similar IDs", "[name=tName2]", new String[]{"tName2ID"});
        t("Find elements that have similar IDs", "#tName2ID", new String[]{"tName2ID"});

        t("Case-sensitivity", "[name=tname1]", new String[]{});
    }

}