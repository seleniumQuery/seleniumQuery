/*
 * Copyright (c) 2017 seleniumQuery authors
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

package endtoend.fluentfunctions.waituntil;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import io.github.seleniumquery.SeleniumQueryFluentAndOrThen;
import io.github.seleniumquery.SeleniumQueryFluentFunction;
import io.github.seleniumquery.SeleniumQueryFluentFunctionEvaluateIf;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.SeleniumQueryWaitAndOrThen;
import io.github.seleniumquery.SeleniumQueryWaitEvaluateUntil;
import io.github.seleniumquery.SeleniumQueryWaitUntil;
import io.github.seleniumquery.fluentfunctions.waituntil.SeleniumQueryTimeoutException;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class WaitUntilTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void waitUntil__text__contains__SUCCESS() {
    	$("#sq").waitUntil().text().contains("seleniumQue");
    }

    @Test(expected = SeleniumQueryTimeoutException.class)
    public void waitUntil__text__contains__FAIL() {
    	$("#sq").waitUntil(100).text().contains("abc");
    	fail();
    }

    @SuppressWarnings("deprecation")
    @Test(expected = io.github.seleniumquery.wait.SeleniumQueryTimeoutException.class)
    public void waitUntil__text__contains__FAIL__deprecated_exception() {
    	$("#sq").waitUntil(100).text().contains("abc");
    	fail();
    }

    @Test
    @SuppressWarnings("deprecation")
    public void waitUntil__text__contains__SUCCESS__local_Objects__BAD_interfaces() {
        SeleniumQueryWaitUntil seleniumQueryWaitUntil = $("#sq").waitUntil();
        SeleniumQueryWaitEvaluateUntil<String> text = seleniumQueryWaitUntil.text();
        SeleniumQueryWaitAndOrThen seleniumQue = text.contains("seleniumQue");
        SeleniumQueryObject then = seleniumQue.then();
        String text2 = then.text();
        assertEquals("seleniumQuery", text2);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void waitUntil__text__contains__SUCCESS__local_Objects___BAD_interfaces_with_AND() {
        SeleniumQueryWaitUntil seleniumQueryWaitUntil = $("#sq").waitUntil();
        SeleniumQueryWaitEvaluateUntil<String> seleniumQueryWaitEvaluateUntil = seleniumQueryWaitUntil.text();
        SeleniumQueryWaitAndOrThen contains = seleniumQueryWaitEvaluateUntil.contains("seleniumQue");

        SeleniumQueryWaitUntil seleniumQueryWaitUntil2 = contains.and();
        SeleniumQueryWaitEvaluateUntil<String> seleniumQueryWaitEvaluateUntil2 = seleniumQueryWaitUntil2.text();
        SeleniumQueryWaitAndOrThen contains2 = seleniumQueryWaitEvaluateUntil2.contains("seleniumQue");

        SeleniumQueryObject then = contains2.then();
        String text = then.text();
        assertEquals("seleniumQuery", text);
    }

    @Test
    public void waitUntil__text__contains__SUCCESS__local_Objects__GOOD_interfaces() {
        SeleniumQueryFluentFunction seleniumQueryFluentFunction = $("#sq").waitUntil();
        SeleniumQueryFluentFunctionEvaluateIf<String> text = seleniumQueryFluentFunction.text();
        SeleniumQueryFluentAndOrThen seleniumQue = text.contains("seleniumQue");
        SeleniumQueryObject then = seleniumQue.then();
        String text2 = then.text();
        assertEquals("seleniumQuery", text2);
    }

}
