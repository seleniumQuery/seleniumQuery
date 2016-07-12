package endtoend.selectors.pseudoclasses.form;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.SecondGenSelectorSystemDetector;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CheckboxPseudoClassTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

	@Test
	public void checkboxPseudoClass() {
		assertThat($("[type='checkbox']").size(), is(4));
		assertThat($(":checkbox").size(), is(1));
		assertThat($("*:checkbox").size(), is(1));
		assertThat($("input:checkbox").size(), is(1));

		assertThat($("#i1").is(":checkbox"), is(true));
		assertThat($("#i1").is("*:checkbox"), is(true));
		assertThat($("#i1").is("input:checkbox"), is(true));

		assertThat($("#i2").is(":checkbox"), is(false));
		assertThat($("#i3").is(":checkbox"), is(false));
		assertThat($("#i4").is(":checkbox"), is(false));
	}

	@Test
	public void checkboxPseudoClass__invalid() {
        SecondGenSelectorSystemDetector.assertPseudoOnDivAndSpanIsEmptyOn1stGenAndThrowsExceptionOn2ndGen(":checkbox");
	}

}
