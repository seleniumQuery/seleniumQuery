package io.github.seleniumquery.selector.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;
import io.github.seleniumquery.selectors.pseudoclasses.UnsupportedXPathPseudoClassException;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class HiddenPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	@Test(expected = UnsupportedXPathPseudoClassException.class)
	public void hiddenPseudoClass() {
		List<WebElement> elements = $(":hidden").get();
		
		assertThat(elements, hasSize(11));
		assertThat(elements.get(0).getTagName(), is("head"));
		assertThat(elements.get(1).getTagName(), is("meta"));
		assertThat(elements.get(2).getTagName(), is("title"));
		assertThat(elements.get(3).getTagName(), is("style"));
		assertThat(elements.get(4).getTagName(), is("bell"));
		assertThat(elements.get(5).getTagName(), is("div"));
		assertThat(elements.get(6).getTagName(), is("h1"));
		assertThat(elements.get(7).getTagName(), is("button"));
		assertThat(elements.get(8).getTagName(), is("span"));
		assertThat(elements.get(9).getTagName(), is("div"));
		assertThat(elements.get(10).getTagName(), is("p"));
	}
	
}