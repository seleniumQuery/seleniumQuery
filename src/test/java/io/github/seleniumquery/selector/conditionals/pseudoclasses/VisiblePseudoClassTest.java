package io.github.seleniumquery.selector.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class VisiblePseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	@Test(expected = RuntimeException.class)
	public void visiblePseudoClass() {
		List<WebElement> elements = $(":visible").get();
		
		assertThat(elements, hasSize(6));
		assertThat(elements.get(0).getTagName(), is("html"));
		assertThat(elements.get(1).getTagName(), is("body"));
		assertThat(elements.get(2).getTagName(), is("div"));
		assertThat(elements.get(3).getTagName(), is("h1"));
		assertThat(elements.get(4).getTagName(), is("button"));
		assertThat(elements.get(5).getTagName(), is("span"));
	}
	
}