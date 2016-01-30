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

import io.github.seleniumquery.by.firstgen.css.pseudoclasses.PseudoClassOnlySupportedThroughIsOrFilterException;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import java.util.List;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FocusablePseudoClassTest {
	
	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	@Test
	public void focusablePseudoClass_through_is() {
		assertThat($("#i1").is(":focusable"), is(false));
		assertThat($("#i2").is(":focusable"), is(true));
		assertThat($("#a1").is(":focusable"), is(false));
		assertThat($("#a2").is(":focusable"), is(true));
		assertThat($("#p1").is(":focusable"), is(false));
		assertThat($("#p2").is(":focusable"), is(true));
	}

	@Test
	public void focusablePseudoClass_through_filter() {
        List<WebElement> focusableWebElements = $("#i2,#a2,#p2").get();
		assertThat($("input,a,p").filter(":focusable").get(), is(focusableWebElements));
	}

	@Test(expected = PseudoClassOnlySupportedThroughIsOrFilterException.class)
	public void focusablePseudoClass__directly() {
		assertThat($("body > *").size(), is(6));
		assertThat($(":focusable").size(), is(3));

		assertThat($("input:focusable").size(), is(1));
		assertThat($("a:focusable").size(), is(1));
		assertThat($("p:focusable").size(), is(1));
	}
	
}