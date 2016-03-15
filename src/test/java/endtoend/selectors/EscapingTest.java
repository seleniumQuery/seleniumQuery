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

package endtoend.selectors;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import java.util.List;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class EscapingTest {

    @ClassRule @Rule
    public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void d1() {
        assertSelectorBringsElementsEvenIfWithTagBefore(".foo", "#d1");
        assertSelectorBringsElementsEvenIfWithTagBefore(".f\\o\\o", "#d1");
    }

    @Test
    public void d2() {
        assertSelectorBringsElementsEvenIfWithTagBefore(".f\\\\o\\\\o", "#d2");
        assertSelectorBringsElementsEvenIfWithTagBefore(".f\\\\\\o\\\\\\o", "#d2");
    }

    @Test
    public void d3() {
        assertSelectorBringsElementsEvenIfWithTagBefore(".f\\\\\\\\o\\\\\\\\o", "#d3");
        assertSelectorBringsElementsEvenIfWithTagBefore(".f\\\\\\\\\\o\\\\\\\\\\o", "#d3");
    }

    @Test
    public void d() {
        assertThat($(".f\\\\\\\\\\\\o\\\\\\\\\\\\o").size(), is(0));
    }

    @Test
    public void s1() {
        assertSelectorBringsElementsEvenIfWithTagBefore("#foo", ".s1");
        assertSelectorBringsElementsEvenIfWithTagBefore("#f\\o\\o", ".s1");
    }

    @Test
    public void a2() {
        assertSelectorBringsElementsEvenIfWithTagBefore("#f\\\\o\\\\o", ".s2");
        assertSelectorBringsElementsEvenIfWithTagBefore("#f\\\\\\o\\\\\\o", ".s2");
    }

    @Test
    public void s3() {
        assertSelectorBringsElementsEvenIfWithTagBefore("#f\\\\\\\\o\\\\\\\\o", ".s3");
        assertSelectorBringsElementsEvenIfWithTagBefore("#f\\\\\\\\\\o\\\\\\\\\\o", ".s3");
    }

    @Test
    public void s() {
        assertThat($("#f\\\\\\\\\\\\o\\\\\\\\\\\\o").size(), is(0));
    }

    @Test
    public void d4() {
        assertSelectorBringsElementsEvenIfWithTagBefore(".\\\"", "#d4");
    }

    @Test
    public void d5() {
        assertSelectorBringsElementsEvenIfWithTagBefore(".\\\"a\\\"b\\\"c\\\"", "#d5");
    }

    @Test
    public void d6() {
        assertSelectorBringsElementsEvenIfWithTagBefore("#\\\"", ".d6");
    }

    @Test
    public void d7() {
        assertSelectorBringsElementsEvenIfWithTagBefore("#\\\"a\\\"b\\\"c\\\"", ".d7");
    }

    @Test
    public void s4() {
        assertSelectorBringsElementsEvenIfWithTagBefore(".\\\'", "#s4");
    }

    @Test
    public void s5() {
        assertSelectorBringsElementsEvenIfWithTagBefore(".\\\'a\\\'b\\\'c\\\'", "#s5");
    }

    @Test
    public void s6() {
        assertSelectorBringsElementsEvenIfWithTagBefore("#\\\'", ".s6");
    }

    @Test
    public void s7() {
        assertSelectorBringsElementsEvenIfWithTagBefore("#\\\'a\\\'b\\\'c\\\'", ".s7");
    }

    private void assertSelectorBringsElementsEvenIfWithTagBefore(String selector, String expectedElementsSelector) {
        List<WebElement> expectedElements = $(expectedElementsSelector).get();
        // selector brings expectedElements...
        assertThat($(selector).get(), is(expectedElements));
        // ...even if with tag before...
        String firstElementTag = expectedElements.get(0).getTagName();
        assertThat($(firstElementTag+ selector).get(), is(expectedElements));
    }

}
