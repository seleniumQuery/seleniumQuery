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

package endtoend.crossdriver.driverbugs;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import java.util.List;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;

/**
 * This test exists so we can keep the workaround on :checked for PhantomJS driver.
 * The day this test fails is because they fixed the bug and we can think about removing the workaround.
 */
public class PhantomJSCheckedSelectorBugTest {

    @ClassRule @Rule
    public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void confirm_CHECKED_brings_all_checked_elements_in_chrome() {
        assumeTrue($.driver().get() instanceof ChromeDriver);
        List<WebElement> checkedElements = $.driver().get().findElements(By.cssSelector(":checked"));
        assertThat(checkedElements, hasSize(3));
    }

    @Test
    public void confirm_CHECKED_has_a_bug_and_does_NOT_bring_the_checked_OPTION_in_PhantomJS() {
        assumeTrue($.driver().get() instanceof PhantomJSDriver);
        List<WebElement> checkedElements = $.driver().get().findElements(By.cssSelector(":checked"));
        assertThat(checkedElements, hasSize(2));
    }

}