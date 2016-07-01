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
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.junitrule.annotation.JavaScriptOnly;

import static testinfrastructure.EndToEndTestUtils.equal;

public class SizzlePseudosTargetAndRoot extends SizzleTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SizzleTest.class);

    @Test
    @Ignore("issue#50 - add :target pseudo-class selector")
    public void target_pseudo() {
        executeJS(" jQuery('<a>').attr({href: '#', id: 'new-link'}).appendTo('#qunit-fixture'); ");
        String oldHash = (String) executeJS("return window.location.hash;");
        executeJS("window.location.hash = 'new-link'");

        t(":target", ":target", new String[]{"new-link"});

        executeJS("jQuery('#new-link').remove();");
        executeJS("window.location.hash = arguments[0];", oldHash);
    }

    // :root
    @Test @JavaScriptOnly
    public void root_pseudo() {
        Object documentElement = executeJS("return document.documentElement;");
        equal(Sizzle(":root").get(0), documentElement, ":root selector");
    }

}
