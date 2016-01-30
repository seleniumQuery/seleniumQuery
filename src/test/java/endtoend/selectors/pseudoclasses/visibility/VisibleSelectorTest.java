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

package endtoend.selectors.pseudoclasses.visibility;

import io.github.seleniumquery.by.firstgen.css.pseudoclasses.PseudoClassOnlySupportedThroughIsOrFilterException;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class VisibleSelectorTest {
	
	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

	@Test(expected = PseudoClassOnlySupportedThroughIsOrFilterException.class)
    public void visible_directly() {
    	assertThat($(":visible").size(), is(-99999));
    }

	@Test
	public void visible_is() {
		assertThat($("#visibleDiv").is(":visible"), is(true));
		assertThat($("#visibleDiv2").is(":visible"), is(true));
		assertThat($("#invisibleDiv").is(":visible"), is(false));
    	assertThat($("#invisibleParentDiv").is(":visible"), is(false));
    	assertThat($("#almostVisibleDiv").is(":visible"), is(false));
    }

	@Test
	public void visible_filter() {
		assertThat($("div").filter(":visible").get(), is($("#visibleDiv,#visibleDiv2").get()));
    }

}