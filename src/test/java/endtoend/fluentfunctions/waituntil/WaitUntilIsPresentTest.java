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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.junitrule.annotation.JavaScriptEnabledOnly;

public class WaitUntilIsPresentTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

	@Test @JavaScriptEnabledOnly
	public void isPresent__should_not_throw_an_exception_when_the_element_becomes_present__and__return_the_elements_val() throws Exception {
		// given
		// when
		$("div.clickable").click();
		// then
		assertEquals(0, $("input.ball").size());
		assertEquals("generated input starting value", $("input.ball").waitUntil().is(":present").then().val());
	}

	@Test
	public void waitUntil_not_present() {
		$(".whatever").waitUntil().is(":not(:present)");
	}

	@Test
	public void waitUntil_enabled_not_present() {
		$(".whatever").waitUntil().is(":enabled:not(:present)");
	}

	@Test
	public void waitUntil_not_present_enabled() {
		$(".whatever").waitUntil().is(":not(:present):enabled");
	}

	@Test
	public void waitUntil_not_not_not_present_with_others() {
		$(".whatever").waitUntil().is(".xyz:enabled:not(:not(:not(:present))):disabled");
	}

	@Test
	public void is_not_not_not_present() {
		assertThat($(".whatever").is(".xyz:enabled:disabled"), is(false));
		assertThat($(".whatever").is(".xyz:enabled:not(:not(:not(:present))):disabled"), is(true));
	}

}
