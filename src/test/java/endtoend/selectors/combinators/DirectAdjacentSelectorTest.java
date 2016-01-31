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

public class DirectAdjacentSelectorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    private static final String SPAN_DIRECTLY_ADJACENT_TO_P_ID = "#directly-adjacent-to-p";

    @Test
    public void directAdjacent() {
        assertThat($("p + span").get(), is($(SPAN_DIRECTLY_ADJACENT_TO_P_ID).get()));
    }

    @Test
    public void directAdjacent_is() {
        assertThat($(SPAN_DIRECTLY_ADJACENT_TO_P_ID).is("p + span"), is(true));
    }

    @Test
    public void directAdjacent_filter() {
        assertThat($("*").filter("p + span").get(), is($(SPAN_DIRECTLY_ADJACENT_TO_P_ID).get()));
    }

}