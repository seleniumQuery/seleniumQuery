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

public class SubmitPseudoClassTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

	// http://jsbin.com/mopireya/6/edit
	@Test
	public void submitPseudoClass() {
		assertThat($("[type='submit']").size(), is(6));

		assertThat($(":submit").size(), is(4));
		assertThat($("*:submit").size(), is(4));
		assertThat($("input:submit").size(), is(2));
		assertThat($("div:submit").size(), is(0));
		assertThat($("span:submit").size(), is(0));

		assertThat($("#i1").is(":submit"), is(true));
		assertThat($("#i1").is("*:submit"), is(true));
		assertThat($("#i1").is("input:submit"), is(true));

		assertThat($("#i2").is(":submit"), is(false));
		assertThat($("#i3").is(":submit"), is(false));
		assertThat($("#i4").is(":submit"), is(false));

		assertThat($("#b1").is(":submit"), is(true));

        assertThat($("#b1").is("[type='submit']"), is(true));

		assertThat($("#b2").is(":submit"), is(true));
		assertThat($("#b2").is("[type='submit']"), is(true));
		
		assertThat($(".clz:submit").size(), is(1));
		assertThat($("#i1c").is(".clz:submit"), is(true));
	}

	@Test
	public void in_button_elements__selenium_brings_type_attribute_equal_to_submit() {
		String typeAttributeValue = $("#b1").get(0).getAttribute("type");
		assertThat(typeAttributeValue, is("submit"));
	}

}