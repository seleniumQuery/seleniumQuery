/*
 * Copyright (c) 2015 seleniumQuery authors
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

package endtoend.selectors.pseudoclasses.form;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static testinfrastructure.EndToEndTestUtils.t;

public class DisabledPseudoClassTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

	// http://jsbin.com/guqef/3/edit
	@Test
	public void disabledPseudo_with_tag_button() {
		assertThat($("button:disabled").size(), is(2));
	}

	@Test
	public void disabledPseudo_with_tag_input() {
		assertThat($("input:disabled").size(), is(48));
	}

	@Test
	public void disabledPseudo_with_tag_select() {
		assertThat($("select:disabled").size(), is(2));
	}

	@Test
	public void disabledPseudo_with_tag_option() {
		assertThat($("option").size(), is(27));
		assertThat($("option:disabled").size(), is(24));
		assertThat($("option:contains(ENABLED)").size(), is(3));
		assertThat($("option:disabled:contains(disabled)").size(), is(24));
	}

	@Test
	public void disabledPseudo_with_tag_optgroup() {
		t(":disabled <optgroup>s", "optgroup:disabled", "optgroup-2", "optgroup-3", "optgroup-5", "optgroup-6", "optgroup-8", "optgroup-9");
	}

	@Test
	public void disabledPseudo_with_tag_textarea() {
		assertThat($("textarea:disabled").size(), is(2));
	}

	@Test
	public void disabledPseudo() {
		assertThat($(":disabled").size(), is(84));
	}

}
