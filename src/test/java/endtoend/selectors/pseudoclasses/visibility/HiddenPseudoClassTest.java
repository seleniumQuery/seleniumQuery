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

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.by.firstgen.css.pseudoclasses.PseudoClassOnlySupportedThroughIsOrFilterException;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

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
		$(":hidden");
	}

    @Test
    @Ignore
    public void all_hidden() {
        assertSQObjectContains($(":hidden"), "HEAD", "META", "TITLE", "STYLE", "DIV", "H1", "BUTTON", "SPAN", "DIV", "P");
    }

    @Test
    @Ignore
    public void all_not_hidden() {
        WebElement html = $.driver().get().findElement(By.cssSelector("*"));
        assertEquals("html", html.getTagName());
        assertEquals(true, html.isDisplayed());

        assertSQObjectContains($(":not(:hidden)"), "HTML", "BODY", "BELL", "DIV", "H1", "BUTTON", "SPAN");
    }

    @Test
    @Ignore
    public void all_both() {
        assertSQObjectContains($("*"), "HTML", "HEAD", "META", "TITLE", "STYLE", "BODY", "BELL", "DIV", "H1", "BUTTON", "SPAN", "DIV", "H1", "BUTTON", "SPAN", "DIV", "P");
    }

    private void assertSQObjectContains(SeleniumQueryObject $els, String... p) {
        List<String> tags = $els.get().stream().map(WebElement::getTagName).collect(Collectors.toList());
        List<String> expectedTags = Arrays.stream(p).map(String::toLowerCase).collect(Collectors.toList());
        assertEquals(expectedTags.toString(), tags.toString());
    }

}
