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

package endtoend.waituntil;

import io.github.seleniumquery.SeleniumQueryWaitAndOrThen;
import io.github.seleniumquery.wait.SeleniumQueryTimeoutException;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.junitrule.annotation.JavaScriptEnabledOnly;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WaitUntilIsGreaterThanTest {

    private static final String DIV_CLICKABLE_SELECTOR = "div.clickable";

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

	@Test @JavaScriptEnabledOnly
	public void isGreaterThan__should_wait_until_text_becomes_the_expected() {
		// given
		// when
		$(DIV_CLICKABLE_SELECTOR).click();
		// then
		$(DIV_CLICKABLE_SELECTOR).waitUntil(2000, 200).text().isGreaterThan(12);
	}

	@JavaScriptEnabledOnly
	@Test(expected = SeleniumQueryTimeoutException.class)
	public void isGreaterThan__should_fail_if_text_never_becomes_the_expected() {
		// given
		// when
		$(DIV_CLICKABLE_SELECTOR).click();
		// then
		$(DIV_CLICKABLE_SELECTOR).waitUntil(2000, 200).text().isGreaterThan(13);
	}

	@JavaScriptEnabledOnly
	@Test(expected = SeleniumQueryTimeoutException.class)
	public void isGreaterThan__should_fail_if_text_is_not_a_number() {
		// given
		$(DIV_CLICKABLE_SELECTOR).prop("innerHTML", "not a number");
        int dummy = 0;
		// when
        $(DIV_CLICKABLE_SELECTOR).waitUntil().text().isGreaterThan(dummy);
	    // then
        // should throw exception
	}

	@Test @JavaScriptEnabledOnly
	public void waitUntil_isGreaterThan_toString() {
		// given
		$(DIV_CLICKABLE_SELECTOR).click();
		// when
        SeleniumQueryWaitAndOrThen waitAndOrThen = $(DIV_CLICKABLE_SELECTOR).waitUntil(2000, 200).text().isGreaterThan(12);
		// then
        String expectedToString = "$(\"" + DIV_CLICKABLE_SELECTOR + "\")";
        assertThat(waitAndOrThen.toString(), is(expectedToString));
        assertThat(waitAndOrThen.then().toString(), is(expectedToString));
	}

}
