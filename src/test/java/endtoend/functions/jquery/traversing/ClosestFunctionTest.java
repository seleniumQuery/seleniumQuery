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
import org.openqa.selenium.WebElement;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ClosestFunctionTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void closest_function() throws Exception {
    	WebElement d1 = $("#d1").get(0);
    	WebElement d2 = $("#d2").get(0);
    	
    	assertThat($("#o1").closest("div").size(), is(1));
    	assertThat($("#o1").closest("div").get(0), is(d1));
    	
    	assertThat($("#s1").closest("div").size(), is(1));
    	assertThat($("#s1").closest("div").get(0), is(d1));
    	
    	assertThat($("#d1").closest("div").size(), is(1));
    	assertThat($("#d1").closest("div").get(0), is(d1));
    	
    	assertThat($("#o2").closest("div").size(), is(1));
    	assertThat($("#o2").closest("div").get(0), is(d2));
    	
    	assertThat($("#s2").closest("div").size(), is(1));
    	assertThat($("#s2").closest("div").get(0), is(d2));
    	
    	assertThat($("#d2").closest("div").size(), is(1));
    	assertThat($("#d2").closest("div").get(0), is(d2));
    	
    	assertThat($(".opt").closest("div").size(), is(2));
    	assertThat($(".opt").closest("div").get(0), is(d1));
    	assertThat($(".opt").closest("div").get(1), is(d2));
    	
    	assertThat($(".opt").closest("span").size(), is(0));
    	
    	assertThat($("select").closest("div").size(), is(2));
    	assertThat($("select").closest("div").get(0), is(d1));
    	assertThat($("select").closest("div").get(1), is(d2));
    	
    	assertThat($("select").closest("span").size(), is(0));
    }
    
}