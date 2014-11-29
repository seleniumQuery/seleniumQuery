package integration.selectors.pseudoclasses.form;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.ClassRule;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * {@link SelectedPseudoClassTest} has some :checked tests as well.
 */
public class CheckedPseudoClassTest {
	
	@ClassRule
	public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(CheckedPseudoClassTest.class);
	
	@Test
	public void checkedPseudo() {
		assertThat($("*").size(), is(14+7+3+7));
		assertThat($(":checked").size(), is(4+7+7));
	}
	
	@Test
	public void checkedPseudo_with_tag_option() {
		assertThat($("option:checked").size(), is(2));
	}
	
	@Test
	public void checkedPseudo_with_tag_input() {
		assertThat($("input:checked").size(), is(2+7+7));
	}
	
	@Test
	public void checkedPseudo_with_tag_input_checkbox() {
		assertThat($("input[type=checkbox]:checked").size(), is(1+7));
	}
	
	@Test
	public void checkedPseudo_with_tag_input_radio() {
		assertThat($("input[type=radio]:checked").size(), is(1+7));
	}
    
    @Test
    public void  checked_selector_with_not() {
    	assertThat($(":not(:checked)").size(), is(10+3));
    }

    @Test
    public void  checked_selector_with_IS() {
    	assertThat($("#chk1").is(":checked"), is(true));
    	assertThat($("#chk2").is(":checked"), is(false));
    	
    	assertThat($("#rad1").is(":checked"), is(true));
    	assertThat($("#rad2").is(":checked"), is(false));
    	
    	assertThat($("#opt1").is(":checked"), is(true));
    	assertThat($("#opt2").is(":checked"), is(false));

    	assertThat($("#nc1").is(":checked"), is(false));
    	assertThat($("#nc2").is(":checked"), is(false));
    	assertThat($("#nc3").is(":checked"), is(false));
    	
    	assertThat($(".c[type=checkbox]").is(":checked"), is(true));
    	assertThat($(".c[type=radio]").is(":checked"), is(true));
    }

	@Test
	public void checkedPseudo__must_be_aware_of_input_type_but_not_checked_value() {
		assertThat($(".c").size(), is(17));
		assertThat($(".c:checked").size(), is(14));
	}
	
}