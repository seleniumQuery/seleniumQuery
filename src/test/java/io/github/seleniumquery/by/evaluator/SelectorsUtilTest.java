package io.github.seleniumquery.by.evaluator;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SeleniumQuery;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SelectorsUtilTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	WebDriver driver;
	
	@Before
	public void before() {
		driver = SeleniumQuery.$.browser.getDefaultDriver();
	}

	@Test
	public void testParent() {
//		fail("Not yet implemented");
	}

	@Test
	public void lang_function() {
		WebElement french_p = driver.findElement(By.id("french-p"));
		WebElement brazilian_p = driver.findElement(By.id("brazilian-p"));
		WebElement hero_combo = driver.findElement(By.id("hero-combo"));
		WebElement htmlElement = driver.findElement(By.cssSelector("html"));
		WebElement bodyElement = driver.findElement(By.cssSelector("body"));
		
		assertThat(SelectorUtils.lang(french_p), is("fr"));
		assertThat(SelectorUtils.lang(brazilian_p), is("pt-BR"));
		assertThat(SelectorUtils.lang(hero_combo), is("pt-BR"));
		assertThat(SelectorUtils.lang(bodyElement), is("pt-BR"));
		assertThat(SelectorUtils.lang(htmlElement), is(nullValue()));
	}

	@Test
	public void testHasAttribute() {
//		fail("Not yet implemented");
	}

	@Test
	public void testGetPreviousSibling() {
//		fail("Not yet implemented");
	}

	@Test
	public void itselfWithSiblings() {
		WebElement onlyChild = driver.findElement(By.id("onlyChild"));
		WebElement grandsonWithSiblings = driver.findElement(By.id("grandsonWithSiblings"));
		
		assertThat(SelectorUtils.itselfWithSiblings(onlyChild).size(), is(1));
		List<WebElement> grandsons = SelectorUtils.itselfWithSiblings(grandsonWithSiblings);
		assertThat(grandsons.size(), is(3));
		assertThat(grandsons.get(0).getAttribute("id"), is("grandsonWithSiblings"));
		assertThat(grandsons.get(1).getAttribute("id"), is("grandsonB"));
		assertThat(grandsons.get(2).getAttribute("id"), is("grandsonC"));
	}
	
	@Test
	public void escapeSelector_should_escape_starting_integer() {
		String escapedSelector = SelectorUtils.escapeSelector("1a2b3c");
		assertThat(escapedSelector, is("\\\\31 a2b3c"));
	}
	
	@Test
	public void escapeSelector_should_escape_colon() {
		String escapedSelector = SelectorUtils.escapeSelector("must:escape");
		assertThat(escapedSelector, is("must\\:escape"));
	}
	
	@Test
	public void escapeSelector_should_escape_hyphen_if_next_char_is_hyphen_or_digit() {
		assertThat(SelectorUtils.escapeSelector("-abc"), is("-abc"));
		assertThat(SelectorUtils.escapeSelector("-"), is("\\-")); // only hyphen
		assertThat(SelectorUtils.escapeSelector("-123"), is("\\-123"));
		assertThat(SelectorUtils.escapeSelector("---"), is("\\---"));
	}
	
	@Test
	public void escapeAttributeValue__should_escape_strings_according_to_how_the_CSS_parser_works() {
		// abc -> "abc"
		assertThat(SelectorUtils.escapeAttributeValue("abc"), is("\"abc\""));
		// a\"bc -> "a\"bc"
		assertThat(SelectorUtils.escapeAttributeValue("a\\\"bc"), is("\"a\\\"bc\""));
		// a'bc -> "a'bc"
		assertThat(SelectorUtils.escapeAttributeValue("a'bc"), is("\"a'bc\""));
		// a\tc -> "a\\tc"
		assertThat(SelectorUtils.escapeAttributeValue("a\\tc"), is("\"a\\\\tc\""));
	}

}