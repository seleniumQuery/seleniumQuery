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

package endtoend.fluentfunctions.assertthat;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.hamcrest.CoreMatchers;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import io.github.seleniumquery.SeleniumQueryFluentAndOrThen;
import io.github.seleniumquery.SeleniumQueryFluentFunction;
import io.github.seleniumquery.SeleniumQueryFluentFunctionEvaluateIf;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.fluentfunctions.assertthat.SeleniumQueryAssertionError;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class AssertThatTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void assertThat__text__contains__SUCCESS() {
    	$("#sq").assertThat().text().contains("seleniumQue");
    }

    @Test(expected = SeleniumQueryAssertionError.class)
    public void assertThat__text__contains__FAIL() {
    	$("#sq").assertThat().text().contains("abc");
    	fail();
    }

    @Test
    public void assertThat__html__matches__SUCCESS() {
    	$("#sq").assertThat().html().matches(CoreMatchers.not(CoreMatchers.containsString("OTHER STRING")));
    }

    @Test(expected = SeleniumQueryAssertionError.class)
    public void assertThat__html__matches__FAIL() {
    	$("#sq").assertThat().html().matches(CoreMatchers.containsString("abc"));
    	fail();
    }

    @Test
    public void assertThat__intermediary_objects_types() {
        SeleniumQueryFluentFunction seleniumQueryFluentFunction = $("#sq").assertThat();
        SeleniumQueryFluentFunctionEvaluateIf<String> fluentFunctionEvaluateIf = seleniumQueryFluentFunction.text();
        SeleniumQueryFluentAndOrThen seleniumQue = fluentFunctionEvaluateIf.contains("seleniumQue");
        SeleniumQueryObject then = seleniumQue.then();
        String text = then.text();
        assertEquals("seleniumQuery", text);
    }

}
