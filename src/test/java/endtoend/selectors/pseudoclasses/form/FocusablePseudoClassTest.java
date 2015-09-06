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

import io.github.seleniumquery.by.firstgen.css.pseudoclasses.UnsupportedXPathPseudoClassException;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FocusablePseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(getClass());
	
	@Test(expected = UnsupportedXPathPseudoClassException.class)
	public void focusablePseudoClass() {
		assertThat($("body > *").size(), is(6));
		assertThat($(":focusable").size(), is(3));
		
		assertThat($("input:focusable").size(), is(1));
		assertThat($("a:focusable").size(), is(1));
		assertThat($("p:focusable").size(), is(1));
		
		assertThat($("#i1").is(":focusable"), is(false));
		assertThat($("#i2").is(":focusable"), is(true));
		assertThat($("#a1").is(":focusable"), is(false));
		assertThat($("#a2").is(":focusable"), is(true));
		assertThat($("#p1").is(":focusable"), is(false));
		assertThat($("#p2").is(":focusable"), is(true));
	}
	
}