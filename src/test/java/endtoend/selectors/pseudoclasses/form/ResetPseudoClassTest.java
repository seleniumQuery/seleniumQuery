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

public class ResetPseudoClassTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	// http://jsbin.com/kecoruga/1/edit
	@Test
	public void resetPseudoClass() {
		assertThat($("[type='reset']").size(), is(5));
		assertThat($(":reset").size(), is(2));
		assertThat($("*:reset").size(), is(2));
		assertThat($("input:reset").size(), is(1));
		assertThat($("div:reset").size(), is(0));
		assertThat($("span:reset").size(), is(0));

		assertThat($("#i1").is(":reset"), is(true));
		assertThat($("#i1").is("*:reset"), is(true));
		assertThat($("#i1").is("input:reset"), is(true));

		assertThat($("#i2").is(":reset"), is(false));
		assertThat($("#i3").is(":reset"), is(false));
		assertThat($("#i4").is(":reset"), is(false));

		assertThat($("#b1").is(":reset"), is(false));
		assertThat($("#b1").is("[type='reset']"), is(false));
		assertThat($("#b2").is(":reset"), is(true));
		assertThat($("#b2").is("[type='reset']"), is(true));
	}
	
}