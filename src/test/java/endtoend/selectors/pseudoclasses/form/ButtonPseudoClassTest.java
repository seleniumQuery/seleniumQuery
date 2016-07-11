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
import testinfrastructure.SecondGenSelectorSystemDetector;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ButtonPseudoClassTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

	// http://jsbin.com/yacerelo/1/edit
	@Test
	public void buttonPseudoClass_selector() {
		assertThat($("[type='button']").size(), is(4));
		assertThat($(":button").size(), is(5));
		assertThat($("*:button").size(), is(5));
		assertThat($("input:button").size(), is(1));
	}
	@Test
	public void buttonPseudoClass_invalid() {
        SecondGenSelectorSystemDetector.assertPseudoOnDivAndSpanIsEmptyOn1stGenAndThrowsExceptionOn2ndGen(":button");
	}

	@Test
	public void buttonPseudoClass_is() {
		assertThat($("#i1").is(":button"), is(true));
		assertThat($("#i1").is("*:button"), is(true));
		assertThat($("#i1").is("input:button"), is(true));

		assertThat($("#i2").is(":button"), is(false));
		assertThat($("#i3").is(":button"), is(false));
		assertThat($("#i4").is(":button"), is(false));

		assertThat($("#buttonElementWithNoAttributesOtherThanId").is(":button"), is(true));
		assertThat($("#buttonElementWithNoAttributesOtherThanId").is("[type='button']"), is(false));

		assertThat($("#buttonElementWithTypeAttributeEqualToButton").is(":button"), is(true));
		assertThat($("#buttonElementWithTypeAttributeEqualToButton").is("[type='button']"), is(true));
	}

}
