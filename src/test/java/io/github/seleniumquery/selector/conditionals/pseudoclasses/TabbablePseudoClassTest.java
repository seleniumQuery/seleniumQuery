package io.github.seleniumquery.selector.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;
import io.github.seleniumquery.selectors.pseudoclasses.UnsupportedXPathPseudoClassException;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class TabbablePseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	@Test(expected = UnsupportedXPathPseudoClassException.class)
	public void tabbablePseudoClass() {
		assertThat($("body > *").size(), is(3));
		for (WebElement el : $(":tabbable")) {
			System.out.println("@# El: "+el);
		}
		assertThat($(":tabbable").size(), is(2));
	}
	
}