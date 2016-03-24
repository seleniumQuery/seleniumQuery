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

package endtoend.selectors.pseudoclasses.positional;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class OddPseudoClassTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	// http://jsbin.com/vinip/1/edit
	@Test
	public void evenPseudoClass() {
		assertThat($("#d1").is(":odd"), is(false));
		assertThat($("#d2").is(":odd"), is(true));
		assertThat($("#d3").is(":odd"), is(false));
		assertThat($("#d4").is(":odd"), is(true));
		assertThat($("#d5").is(":odd"), is(false));
		assertThat($("#d6").is(":odd"), is(true));
		assertThat($("#d7").is(":odd"), is(false));
		assertThat($("#d8").is(":odd"), is(true));

		assertThat($("#d1").is(".one-amount:odd"), is(false));
		assertThat($("#d2").is(".one-amount:odd"), is(false));
		assertThat($("#d3").is(".one-amount:odd"), is(false));
		assertThat($("#d4").is(".one-amount:odd"), is(false));
		assertThat($("#d5").is(".one-amount:odd"), is(false));
		assertThat($("#d6").is(".one-amount:odd"), is(false));
		assertThat($("#d7").is(".one-amount:odd"), is(false));
		assertThat($("#d8").is(".one-amount:odd"), is(false));

		assertThat($("#d1").is(".even-amount:odd"), is(false));
		assertThat($("#d2").is(".even-amount:odd"), is(false));
		assertThat($("#d3").is(".even-amount:odd"), is(true));
		assertThat($("#d4").is(".even-amount:odd"), is(false));
		assertThat($("#d5").is(".even-amount:odd"), is(true));
		assertThat($("#d6").is(".even-amount:odd"), is(false));
		assertThat($("#d7").is(".even-amount:odd"), is(false));
		assertThat($("#d8").is(".even-amount:odd"), is(false));

		assertThat($("#d1").is(".odd-amount:odd"), is(false));
		assertThat($("#d2").is(".odd-amount:odd"), is(false));
		assertThat($("#d3").is(".odd-amount:odd"), is(false));
		assertThat($("#d4").is(".odd-amount:odd"), is(false));
		assertThat($("#d5").is(".odd-amount:odd"), is(false));
		assertThat($("#d6").is(".odd-amount:odd"), is(false));
		assertThat($("#d7").is(".odd-amount:odd"), is(true));
		assertThat($("#d8").is(".odd-amount:odd"), is(false));

		assertThat($("div:odd").size(), is(4));
		assertThat($(".one-amount:odd").size(), is(0));
		assertThat($(".even-amount:odd").size(), is(2));
		assertThat($(".odd-amount:odd").size(), is(1));
		assertThat($(".empty-amount:odd").size(), is(0));
	}
	
}