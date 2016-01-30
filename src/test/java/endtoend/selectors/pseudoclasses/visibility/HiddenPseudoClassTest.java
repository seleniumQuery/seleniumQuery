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

package endtoend.selectors.pseudoclasses.visibility;

import io.github.seleniumquery.by.firstgen.css.pseudoclasses.PseudoClassOnlySupportedThroughIsOrFilterException;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import java.util.List;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class HiddenPseudoClassTest {
	
	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	@Test
	public void hidden_is() {
		assertThat($("p").is(":hidden"), is(true));
	}

	@Test
	public void hidden_filter() {
		assertThat($("p").filter(":hidden").get(0), is($("#hiddenPar").get(0)));
	}

	@Test(expected = PseudoClassOnlySupportedThroughIsOrFilterException.class)
	public void hidden_used_directly() {
		List<WebElement> elements = $(":hidden").get();

		assertThat(elements, hasSize(11));
		assertThat(elements.get(0).getTagName(), is("head"));
		assertThat(elements.get(1).getTagName(), is("meta"));
		assertThat(elements.get(2).getTagName(), is("title"));
		assertThat(elements.get(3).getTagName(), is("style"));
		assertThat(elements.get(4).getTagName(), is("bell"));
		assertThat(elements.get(5).getTagName(), is("div"));
		assertThat(elements.get(6).getTagName(), is("h1"));
		assertThat(elements.get(7).getTagName(), is("button"));
		assertThat(elements.get(8).getTagName(), is("span"));
		assertThat(elements.get(9).getTagName(), is("div"));
		assertThat(elements.get(10).getTagName(), is("p"));
	}
	
}