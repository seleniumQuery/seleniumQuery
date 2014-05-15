package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.TestInfrastructure;

import org.junit.Before;
import org.junit.Test;

public class FirstPseudoClassTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(EqPseudoClassTest.class));
	}
	
	@Test
	public void firstPseudoClass() {
		assertThat($("div:first").text(), is("Batman"));
		assertThat($("div.c2:first").text(), is("Spider Man"));
		
		assertThat($("div:first.c2").text(), is(""));
		assertThat($("hr:first").text(), is(""));
    }

}