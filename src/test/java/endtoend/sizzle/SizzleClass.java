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

public class SizzleClass extends SizzleTest {

    @ClassRule @Rule static public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SizzleTest.class);

    @Test
    public void class_selectors() throws Exception {
            t("Class Selector", ".blog", new String[] {"mark", "simon"});
            t("Class Selector", ".GROUPS", new String[] {"groups"});
            t("Class Selector", ".blog.link", new String[] {"simon"});
            t("Class Selector w/ Element", "a.blog", new String[] {"mark", "simon"});
            t("Parent Class Selector", "p .blog", new String[] {"mark", "simon"});

//            t("Class selector using UTF8", ".台北Táiběi", new String[] {"utf8class1"});
//            t( "Class selector using UTF8", ".台北", new String[] {"utf8class1","utf8class2"});
//            t("Class selector using UTF8", ".台北Táiběi.台北", new String[] {"utf8class1"});
//            t("Class selector using UTF8", ".台北Táiběi, .台北", new String[] {"utf8class1", "utf8class2"});
//            t("Descendant class selector using UTF8", "div .台北Táiběi", new String[] {"utf8class1"});
//            t("Child class selector using UTF8", "form > .台北Táiběi", new String[] {"utf8class1"});

            t("Escaped Class", ".foo\\:bar", new String[] {"foo:bar"});
            t("Escaped Class", ".test\\.foo\\[5\\]bar", new String[] {"test.foo[5]bar"});
            t("Descendant escaped Class", "div .foo\\:bar", new String[] {"foo:bar"});
            t("Descendant escaped Class", "div .test\\.foo\\[5\\]bar", new String[] {"test.foo[5]bar"});
            t("Child escaped Class", "form > .foo\\:bar", new String[] {"foo:bar"});
            t("Child escaped Class", "form > .test\\.foo\\[5\\]bar", new String[] {"test.foo[5]bar"});

/*
            Object div = document.createElement("div");
            div.innerHTML = "<div class='test e'></div><div class='test'></div>";
            deepEqual(Sizzle(".e", div),[div.firstChild], "Finding a second class.");

            div.lastChild.className = "e";

            deepEqual(Sizzle(".e", div),[div.firstChild, div.lastChild],"Finding a modified class.");

            ok(!Sizzle.matchesSelector(div, ".null"), ".null does not match an element with no class");
            ok(!Sizzle.matchesSelector(div.firstChild, ".null div"), ".null does not match an element with no class");
            div.className = "null";
            ok(Sizzle.matchesSelector(div, ".null"), ".null matches element with class 'null'");
            ok(Sizzle.matchesSelector(div.firstChild, ".null div"), "caching system respects DOM changes");
            ok(!Sizzle.matchesSelector(document, ".foo"), "testing class on document doesn't error");
            ok(!Sizzle.matchesSelector(window, ".foo"), "testing class on window doesn't error");

            div.lastChild.className += " hasOwnProperty toString";
            deepEqual(Sizzle(".e.hasOwnProperty.toString", div),[div.lastChild], "Classes match Object.prototype properties");

            div = jQuery("<div><svg width='200' height='250' version='1.1' xmlns='http://www.w3.org/2000/svg'><rect x='10' y='10' width='30' height='30' class='foo'></rect></svg></div>")[0];
            equal(Sizzle(".foo", div).size(), 1, "Class selector against SVG");
*/

    }

}
