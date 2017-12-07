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

package endtoend.fluentfunctions.waituntil;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import io.github.seleniumquery.SeleniumQueryWaitAndOrThen;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class WaitUntilIsTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(WaitUntilIsMoreTest.class);

	@Test
	public void waitUntil_not_enabled() {
		assertThat($("input.disabledInput").waitUntil().is(":not(:enabled)").then().size(), is(1));
	}

	@Test
	public void waitUntil_disabled() {
		assertThat($("input.disabledInput").waitUntil().is(":disabled").then().size(), is(1));
	}

	@Test
	public void waitUntil_visible() {
		assertThat($("input.enabledInput").waitUntil().is(":visible").then().size(), is(1));
	}

	@Test
	public void waitUntil_enabled() {
		assertThat($("input.enabledInput").waitUntil().is(":enabled").then().size(), is(1));
	}

	@Test
	public void waitUntil_present() {
		assertThat($("input.enabledInput").waitUntil().is(":present").then().size(), is(1));
	}

	@Test
	public void waitUntil_visible_enabled() {
		assertThat($("input.enabledInput").waitUntil().is(":visible:enabled").then().size(), is(1));
	}

	@Test
	public void waitUntil_hidden() {
		assertThat($("div.invisibleDiv").waitUntil().is(":hidden").then().size(), is(1));
	}

	@Test
	public void waitUntil_is_toString() {
		// when
		SeleniumQueryWaitAndOrThen waitAndOrThen = $("div.invisibleDiv").waitUntil().is(":hidden");
		// then
		String expectedToString = "$(\"div.invisibleDiv\")";
		assertThat(waitAndOrThen.toString(), is(expectedToString));
		assertThat(waitAndOrThen.then().toString(), is(expectedToString));
	}

}
