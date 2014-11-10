package integration.selectors.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import org.junit.Rule;
import org.junit.Test;

public class SelectedPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());
	
	@Test
	public void selectedPseudoClass_with_tag_option() {
		assertThat($("option:selected").size(), is(2));
	}
	
	@Test
	public void selectedPseudoClass_with_tag_input() {
		assertThat($("input:selected").size(), is(0));
	}
	
	@Test
	public void selectedPseudoClass_with_tag_input_checkbox() {
		assertThat($("input[type=checkbox]:selected").size(), is(0));
	}
	
	@Test
	public void selectedPseudoClass_with_tag_input_radio() {
		assertThat($("input[type=radio]:selected").size(), is(0));
	}
	
	@Test
	public void selectedPseudoClass() {
		assertThat($(":selected").size(), is(2));
	}
	
}