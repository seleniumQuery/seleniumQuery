package io.github.seleniumquery.selector.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;
import io.github.seleniumquery.selectorxpath.XPathSelectorCompilerService;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HiddenPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	@Test(expected = RuntimeException.class)
	public void hiddenPseudoClass() {
		List<WebElement> xels = $.browser.getDefaultDriver().findElements(By.xpath("(//*[ancestor-or-self::*[contains(normalize-space(@style),'display: none') or contains(normalize-space(@style),'display:none')] or (count(ancestor-or-self::body) = 0 and local-name() != 'html')])"));
		for (WebElement xel : xels) {
			System.out.println("@# XEL: " + xel);
		}
		System.out.println("@#---------------------------");
		
		List<WebElement> findElements = $.browser.getDefaultDriver().findElements(By.id("hiddenByClass"));
		System.out.println("@# FOUND: "+findElements);
		System.out.println("@# is 1st visible: "+findElements.get(0).isDisplayed());
		
		System.out.println("@# EFFECTIVE XPATH: "+XPathSelectorCompilerService.compileSelectorList(null, ":hidden").toXPath());
		
		List<WebElement> elements = $(":hidden").get();
		
		for (WebElement webElement : elements) {
			System.out.println("@# el: " + webElement);
		}
		
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