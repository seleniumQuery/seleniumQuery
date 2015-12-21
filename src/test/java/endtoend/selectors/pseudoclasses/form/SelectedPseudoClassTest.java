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

package endtoend.selectors.pseudoclasses.form;

import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static testinfrastructure.EndToEndTestUtils.t;

public class SelectedPseudoClassTest {

	@ClassRule
	public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SelectedPseudoClassTest.class);
	
	@Test
	@Ignore("Issue#86")
	public void selectedPseudoClass() {
		t(":selected pseudo should not work on checkboxes", "input[type=checkbox]:selected").negative("input[type=checkbox]", "chk1", "chk2");
		t(":selected pseudo should not work on radios", "input[type=radio]:selected").negative("input[type=radio]", "rad1", "rad2");
		t(":selected pseudo should not work on inputs", "input[type=checkbox]:selected").negative("input", 4);
		t(":selected pseudo should return all selected <option>s", ":selected", "opt1", "opt3", "opt4", "s1o1").negative("option", 9);

		t(":checked pseudo should return all selected <option>s plus the two selected <input>s", ":checked", "opt1", "opt3", "opt4", "chk1", "rad1", "s1o1").negative("option,input", 13);
	}
	
}