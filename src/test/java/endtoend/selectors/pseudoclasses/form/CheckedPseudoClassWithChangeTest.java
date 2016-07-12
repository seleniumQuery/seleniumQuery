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

public class CheckedPseudoClassWithChangeTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

	@Test
	public void checkedPseudoClass__when_selected_changes() {
        SecondGenSelectorSystemDetector.assumeSecondGenSelectorSystem();
        assertThat($(":checked").size(), is(1));
        assertThat($(":checked").attr("id"), is("b"));

        assertThat($("[selected]").size(), is(1));
        assertThat($("[selected]").attr("id"), is("b"));

        $("#c").prop("selected", true);

        assertThat($(":checked").size(), is(1));
        assertThat($(":checked").attr("id"), is("c"));

        assertThat($("[selected]").size(), is(1));
        assertThat($("[selected]").attr("id"), is("b"));
	}

}
