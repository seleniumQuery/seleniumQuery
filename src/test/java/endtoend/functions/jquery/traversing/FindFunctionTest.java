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

package endtoend.functions.jquery.traversing;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FindFunctionTest {

    private static final int COMBO_OPTIONS_COUNT = 4;
    private static final int OTHER_OPTIONS_COUNT = 2;

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
    @Test
    public void find_function() {
        assertThat($("option").size(), is(COMBO_OPTIONS_COUNT + OTHER_OPTIONS_COUNT));
        assertThat($("#combo").find("option").size(), is(COMBO_OPTIONS_COUNT));
    }

    @Test
    public void find_function__with_pseudoClasses() {
        assertThat($("#combo").find("option:contains(Howdy)").size(), is(1));
        assertThat($("#combo").find("option:contains(Howdy)").get(0).getAttribute("id"), is("howdy-option"));
    }

    @Test
    public void find_function__with_empty_result() {
        assertThat($("#combo").find(".non-existant-class").size(), is(0));
    }

}