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

package endtoend.selectors.pseudoclasses.form;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.SecondGenSelectorSystemDetector;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FilePseudoClassTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

	@Test
	public void filePseudoClass() {
		assertThat($("[type='file']").size(), is(4));
		assertThat($(":file").size(), is(1));
		assertThat($("*:file").size(), is(1));
		assertThat($("input:file").size(), is(1));

		assertThat($("#i1").is(":file"), is(true));
		assertThat($("#i1").is("*:file"), is(true));
		assertThat($("#i1").is("input:file"), is(true));

		assertThat($("#i2").is(":file"), is(false));
		assertThat($("#i3").is(":file"), is(false));
		assertThat($("#i4").is(":file"), is(false));
	}

    @Test
    public void invalid_filePseudoClass() {
        try {
            assertThat($("div:file").size(), is(0));
            assertThat($("span:file").size(), is(0));

            SecondGenSelectorSystemDetector.failIfSecondGenSelectorSystem();
        } catch (java.lang.IllegalArgumentException e) {
            SecondGenSelectorSystemDetector.failIfFirstGenSelectorSystem(e);
        }
    }

}
