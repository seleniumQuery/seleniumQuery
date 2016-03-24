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
import static testinfrastructure.testutils.DriverInTest.isNotHtmlUnitDriver;

public class VisiblePseudoClassTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	@Test(expected = RuntimeException.class)
	public void visiblePseudoClass() {
		List<WebElement> elements = $(":visible").get();
		
		assertThat(elements, hasSize(6));
		assertThat(elements.get(0).getTagName(), is("html"));
		assertThat(elements.get(1).getTagName(), is("body"));
		assertThat(elements.get(2).getTagName(), is("div"));
		assertThat(elements.get(3).getTagName(), is("h1"));
		assertThat(elements.get(4).getTagName(), is("button"));
		assertThat(elements.get(5).getTagName(), is("span"));
	}

	@Test
	public void visiblePseudoClass_through_filter() {
		List<WebElement> elements = $("*").filter(":visible").get();

        int index = 0;
        if (isNotHtmlUnitDriver($.driver().get())) {
            // HtmlUnit does not bring HTML
            assertThat(elements.get(index++).getTagName(), is("html"));
        }
		assertThat(elements.get(index++).getTagName(), is("body"));
		assertThat(elements.get(index++).getTagName(), is("div"));
		assertThat(elements.get(index++).getTagName(), is("h1"));
		assertThat(elements.get(index++).getTagName(), is("button"));
		assertThat(elements.get(index++).getTagName(), is("span"));
		assertThat(elements, hasSize(index));
	}
	
}