package endtoend.selectors.pseudoclasses.form;

import static testinfrastructure.IntegrationTestUtils.t;
import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.ClassRule;
import org.junit.Test;

public class SelectedPseudoClassTest {

	@ClassRule
	public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SelectedPseudoClassTest.class);
	
	@Test
	public void selectedPseudoClass() {
		t(":selected pseudo should not work on checkboxes", "input[type=checkbox]:selected").negative("input[type=checkbox]", "chk1", "chk2");
		t(":selected pseudo should not work on radios", "input[type=radio]:selected").negative("input[type=radio]", "rad1", "rad2");
		t(":selected pseudo should not work on inputs", "input[type=checkbox]:selected").negative("input", 4);
		t(":selected pseudo should return all selected <option>s", ":selected", "opt1", "opt3", "opt4", "s1o1").negative("option", 9);

		t(":checked pseudo should return all selected <option>s plus the two selected <input>s", ":checked", "opt1", "opt3", "opt4", "chk1", "rad1", "s1o1").negative("option,input", 13);
	}
	
}