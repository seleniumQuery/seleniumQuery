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

package endtoend.selectors.pseudoclasses.form;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class InputPseudoClassTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

	// http://jsbin.com/nuhefeqi/1/edit
	@Test
	public void inputPseudoClass() {
		assertThat($("[type='input']").size(), is(2));
		assertThat($(":input").size(), is(82));
		assertThat($("*:input").size(), is(82));

		assertThat($("input:input").size(), is(73));
		assertThat($("button:input").size(), is(3));
		assertThat($("select:input").size(), is(3));
		assertThat($("textarea:input").size(), is(3));
		assertThat($("option:input").size(), is(0));
		assertThat($("optgroup:input").size(), is(0));

		assertThat($("div:input").size(), is(0));
		assertThat($("span:input").size(), is(0));
    }

    @Test
    public void inputPseudoClass_is() {
		assertThat($("#i1").is(":input"), is(true));
		assertThat($("#i1").is("*:input"), is(true));
		assertThat($("#i1").is("input:input"), is(true));

		assertThat($("#i3").is(":input"), is(false));
		assertThat($("#i4").is(":input"), is(false));

		assertThat($("#b1").is(":input"), is(true));
	}

}
