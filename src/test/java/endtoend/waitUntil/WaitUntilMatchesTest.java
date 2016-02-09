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

package endtoend.waitUntil;

import io.github.seleniumquery.wait.SeleniumQueryTimeoutException;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WaitUntilMatchesTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(WaitUntilValTest.class);

	@Before
	public void setUp() throws Exception {
		$("#i1").val("abc");
		$("#i2").val("abcdef");
	}

	@Test
	public void waitUntil_matches() {
		assertThat($("input").waitUntil().val().matches("[ba]{2}c?.*").then().size(), is(2));
		assertThat($("#i2").waitUntil().val().matches("abcdef").then().size(), is(1));
	}

	@Test(expected = SeleniumQueryTimeoutException.class)
	public void waitUntil_matches__not_everyone_meet() {
		$("input").waitUntil(500).val().matches("abcdef");
	}

}