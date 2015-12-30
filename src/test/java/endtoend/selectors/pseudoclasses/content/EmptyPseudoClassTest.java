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

package endtoend.selectors.pseudoclasses.content;

import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.testutils.DriverInTest;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class EmptyPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(getClass());
	
	// http://jsbin.com/fuzuj/1/edit
	@Test
	public void emptyPseudoClass() {
		assertThat($("#d1").is(":empty"), is(false));
		assertThat($("#d2").is(":empty"), is(true));

		if (DriverInTest.isHtmlUnitDriverEmulatingIEBelow11($.driver().get())) {
			assertThat($("#d3").is(":empty"), is(true));
			assertThat($("#d4").is(":empty"), is(true));
		} else {
			assertThat($("#d3").is(":empty"), is(false));
			assertThat($("#d4").is(":empty"), is(false));
		}
		assertThat($("#d5").is(":empty"), is(false));

		assertThat($("#d10").is(":empty"), is(false));
		assertThat($("#d11").is(":empty"), is(true));
		assertThat($("#d12").is(":empty"), is(false));
		assertThat($("#d13").is(":empty"), is(true));
		
		if (DriverInTest.isHtmlUnitDriverEmulatingIEBelow11($.driver().get())) {
			assertThat($("#d14").is(":empty"), is(true));
		} else {
			assertThat($("#d14").is(":empty"), is(false));
		}
	}
	
}