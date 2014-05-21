package io.github.seleniumquery.selector.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class ParentPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	// http://jsbin.com/lutim/4/edit
	@Test
	public void parentPseudoClass() {
		assertThat($("#d1").is(":parent"), is(false));
	}
	@Test
	public void parentPseudoClass2() {
		assertThat($("#d2").is(":parent"), is(true));
	}
	@Test
	public void parentPseudoClass3() {
		assertThat($("#d3").is(":parent"), is(true));
	}
	@Test
	public void parentPseudoClass4() {
		assertThat($("#d4").is(":parent"), is(false));
	}
	@Test
	public void parentPseudoClass5() {
		assertThat($("#d5").is(":parent"), is(true));
	}
	@Test
	public void parentPseudoClass6() {
		assertThat($("#d6").is(":parent"), is(false));
	}
	@Test
	public void parentPseudoClass7() {
		assertThat($("#d7").is(":parent"), is(true));
	}
	
}