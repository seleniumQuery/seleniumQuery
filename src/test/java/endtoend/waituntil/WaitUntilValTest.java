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

package endtoend.waituntil;

import io.github.seleniumquery.SeleniumQueryWaitAndOrThen;
import io.github.seleniumquery.wait.SeleniumQueryTimeoutException;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WaitUntilValTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

	@Before
	public void setUp() throws Exception {
		$("#i1").val("abc");
		$("#i2").val("abcdef");
	}

	@Test
	public void waitUntil_val() {
		assertThat($("#i1").waitUntil().val().isEqualTo("abc").then().size(), is(1));
		assertThat($("#i2").waitUntil().val().isEqualTo("abcdef").then().size(), is(1));
		assertThat($("#i1").waitUntil().val().not().isEqualTo("xyz").then().size(), is(1));
		assertThat($("#i2").waitUntil().val().not().isEqualTo("xyz").then().size(), is(1));
	}

	@Test(expected = SeleniumQueryTimeoutException.class)
	public void waitUntil_val__not_everyone_meet() {
		$("input").waitUntil(500).val().isEqualTo("abcdef");
	}

	@Test
	public void waitUntil_val__changing() {
		$("#i1").val("abcdef");
		assertThat($("input").waitUntil(500).val().isEqualTo("abcdef").then().size(), is(2));
	}

	@Test
	public void waitUntil_val_toString() {
        // when
		SeleniumQueryWaitAndOrThen waitAndOrThen = $("#i1").waitUntil().val().isEqualTo("abc");
		// then
		String expectedToString = "$(\"#i1\")";
		assertThat(waitAndOrThen.toString(), is(expectedToString));
		assertThat(waitAndOrThen.then().toString(), is(expectedToString));
	}

}
