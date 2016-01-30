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

package endtoend.selectors.pseudoclasses.childfilter;

import io.github.seleniumquery.by.firstgen.css.pseudoclasses.PseudoClassOnlySupportedThroughIsOrFilterException;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;

public class OnlyOfTypePseudoClassTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test(expected = PseudoClassOnlySupportedThroughIsOrFilterException.class)
    public void onlyOfType_directly() {
        Assert.assertThat($("span:only-of-type").get(), is($("#s1").get()));
    }

    @Test
    public void onlyOfType_filter() {
        Assert.assertThat($("body > *").filter(":only-of-type").get(), is($("#s1").get()));
    }

    @Test
    public void onlyOfType_is() {
        Assert.assertThat($("#s1").is(":only-of-type"), is(true));
        Assert.assertThat($("#d1").is(":only-of-type"), is(false));
    }

}