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

import static endtoend.selectors.combinators.DirectAdjacentSelectorTest.SPAN_DIRECTLY_ADJACENT_TO_P_ID;
import static io.github.seleniumquery.SeleniumQuery.$;
import static java.lang.String.format;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GeneralAdjacentSelectorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(DirectAdjacentSelectorTest.class);

    private static final String SPAN_GENERALLY_ADJACENT_TO_P_ID = "#not-directly-adjacent-to-p";
    private static final String SPAN_GENERALLY_ADJACENT_TO_P_ID_2 = "#not-directly-adjacent-to-p2";
    private static final String ALL_SPAN_IDS = format("%s,%s,%s", SPAN_DIRECTLY_ADJACENT_TO_P_ID, SPAN_GENERALLY_ADJACENT_TO_P_ID, SPAN_GENERALLY_ADJACENT_TO_P_ID_2);

    @Test
    public void generalAdjacent() {
        assertThat($("p ~ span").get(), is($(ALL_SPAN_IDS).get()));
    }

    @Test
    public void generalAdjacent_is() {
        assertThat($(SPAN_GENERALLY_ADJACENT_TO_P_ID_2).is("p ~ span"), is(true));
    }

    @Test
    public void generalAdjacent_filter() {
        assertThat($("*").filter("p ~ span").get(), is($(ALL_SPAN_IDS).get()));
    }

}