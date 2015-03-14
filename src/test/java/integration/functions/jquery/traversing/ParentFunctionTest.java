/*
Copyright (c) 2015 seleniumQuery authors

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package integration.functions.jquery.traversing;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.Rule;
import org.junit.Test;

import static infrastructure.IntegrationTestUtils.*;
import static io.github.seleniumquery.SeleniumQuery.jQuery;
import static java.util.Arrays.asList;

public class ParentFunctionTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(getClass());
	
    @Test
    public void parent_function() {
		equal(id(jQuery("#groups").parent()), "ap", "Simple parent check");
		equal(id(jQuery("#groups").parent("p")), "ap", "Filtered parent check");
		equal(jQuery("#groups").parent("div").size(), 0, "Filtered parent check, no match");
		equal(id(jQuery("#groups").parent("div, p")), "ap", "Check for multiple filters");
		equal(jQuery("#en, #sndp").parent().get(), jQuery("#foo").get(), "Check for unique results from parent");

		equal(ids(jQuery(".multiple").parent()), asList("div_a", "div_b", "div_c", "div_d"), "Several children, several parents");
		equal(ids(jQuery(".multiple").parent("div")), asList("div_a", "div_b", "div_c", "div_d"), "Several children, every parent matches selector");
		equal(ids(jQuery(".multiple").parent("#div_a, #div_d")), asList("div_a", "div_d"), "Several children, not every parent matches selector");
		equal(jQuery(".multiple").parent("iv").size(), 0, "Several children, no parent matches selector");
		equal(ids(jQuery(".multiple").parent("#div_a")), asList("div_a"), "Several children, only one parent matches selector");
		equal(ids(jQuery(".multiple").parent("#div_b")), asList("div_b"), "Several children, only one parent matches selector");
		equal(ids(jQuery(".multiple").parent("#div_c")), asList("div_c"), "Several children, only one parent matches selector");
		equal(ids(jQuery(".multiple").parent("#div_d")), asList("div_d"), "Several children, only one parent matches selector");
    }

}