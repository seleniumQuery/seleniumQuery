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

package endtoend.selectors.combinators;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DirectDescendantCssSelectorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    private static final String SPAN_DIRECT_ID = "#direct";

    @Test
    public void directDescendant() {
        assertThat($("div > span").get(), is($(SPAN_DIRECT_ID).get()));
    }

    @Test
    public void directDescendant_is() {
        assertThat($(SPAN_DIRECT_ID).is("div > span"), is(true));
    }

    @Test
    public void directDescendant_filter() {
        assertThat($("*").filter("div > span").get(), is($(SPAN_DIRECT_ID).get()));
    }

}