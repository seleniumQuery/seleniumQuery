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
import static io.github.seleniumquery.utils.DriverVersionUtils.isHtmlUnitDriverEmulatingIEBelow11;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ButtonPseudoClassTest {

	@ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	@Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;
	
	// http://jsbin.com/yacerelo/1/edit
	@Test
	public void buttonPseudoClass_selector() {
		assertThat($("[type='button']").size(), is(4));
		assertThat($(":button").size(), is(5));
		assertThat($("*:button").size(), is(5));
		assertThat($("input:button").size(), is(1));
		assertThat($("div:button").size(), is(0));
		assertThat($("span:button").size(), is(0));
	}
	
	@Test
	public void buttonPseudoClass_is() {
		assertThat($("#i1").is(":button"), is(true));
		assertThat($("#i1").is("*:button"), is(true));
		assertThat($("#i1").is("input:button"), is(true));

		assertThat($("#i2").is(":button"), is(false));
		assertThat($("#i3").is(":button"), is(false));
		assertThat($("#i4").is(":button"), is(false));

		assertThat($("#b1").is(":button"), is(true));

		// #Cross-Driver
		// Not a workaround, just documenting a difference.
		// Latest HtmlUnit, when emulating IE 8 abd 9, considers <button></button> to be <button type="button"></button>
		// not really a problem here, as it doesn't affect :button's behavior.
		// :submit, though, is affected. Check its implementation if you're curious about how it solves this
		// problem (and, yes, it is nasty, it uses reflection and stuff).
		if (isHtmlUnitDriverEmulatingIEBelow11($.driver().get())) {
			assertThat($("#b1").is("[type='button']"), is(true));
		} else {
			assertThat($("#b1").is("[type='button']"), is(false));
		}

		assertThat($("#b2").is(":button"), is(true));
		assertThat($("#b2").is("[type='button']"), is(true));
	}

}