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

package testinfrastructure.junitrule.sample;

import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.junitrule.annotation.FirefoxOnly;
import testinfrastructure.junitrule.annotation.JavaScriptEnabledOnly;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@Ignore
public class SampleTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver rule = new SetUpAndTearDownDriver(SampleTest.class);

    @Test
    public void m1__passes() {
        System.out.println("\t\t\t m1__passes()");
        assertThat($("body").text(), is("body-content"));
    }

    @Test
    @FirefoxOnly
    public void m2__firefoxOnly() {
        System.out.println("\t\t\t m2__firefoxOnly()");
        assertThat($("body").text(), is("body-content"));
    }

    @Test
    @JavaScriptEnabledOnly
    public void m3__jsOnly() {
        System.out.println("\t\t\t m3__jsOnly()");
        assertThat($("body").text(), is("body-content"));
    }

    @Test
    public void m4__fails() {
        System.out.println("\t\t\t m4__fails()");
        assertThat($("body").text(), is("xbody-content"));
    }

}
