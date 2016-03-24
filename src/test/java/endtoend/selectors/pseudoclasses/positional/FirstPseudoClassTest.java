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

public class FirstPseudoClassTest {

	@ClassRule @Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(EqPseudoClassTest.class);
	
	@Test
	public void firstPseudoClass() {
		assertThat($("div:first").text(), is("Batman"));
		assertThat($("div.c2:first").text(), is("Spider Man"));
		
		assertThat($("div:first.c2").text(), is(""));
		assertThat($("hr:first").text(), is(""));
    }

}