package io.github.seleniumquery.selectorxpath;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class XPathSelectorCompilerServiceTest {

	@Test
	public void id() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("#ball");
		assertThat(compileSelectorList.toXPath(), is("(.//*[@id = 'ball'])"));
	}
	@Test
	public void id_with_odd_chars() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("#a-b\\.c\\:d");
		assertThat(compileSelectorList.toXPath(), is("(.//*[@id = 'a-b.c:d'])"));
	}
	
	@Test
	public void two_ids() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("#ball,#bell");
		assertThat(compileSelectorList.toXPath(), is("(.//*[@id = 'ball']) | (.//*[@id = 'bell'])"));
	}
	
	@Test
	public void by_class() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList(".bozo");
		assertThat(compileSelectorList.toXPath(), is("(.//*[contains(concat(' ', normalize-space(@class), ' '), ' bozo ')])"));
	}
	
	@Test
	public void by_id_and_class() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("#ball.bozo");
		assertThat(compileSelectorList.toXPath(), is("(.//*[@id = 'ball' and contains(concat(' ', normalize-space(@class), ' '), ' bozo ')])"));
	}
	
	@Test
	public void by_id_and_DESCENDANT_class() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("#ball .bozo");
		assertThat(compileSelectorList.toXPath(), is("(.//*[@id = 'ball']//*[contains(concat(' ', normalize-space(@class), ' '), ' bozo ')])"));
	}
	
	@Test
	public void descendant() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("span div");
		assertThat(compileSelectorList.toXPath(), is("(.//span//div)"));
	}
	
	@Test
	public void direct_descendant() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("span>div");
		assertThat(compileSelectorList.toXPath(), is("(.//span/div)"));
	}
	
	@Test
	public void general_adjacent() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("span~div");
		assertThat(compileSelectorList.toXPath(), is("(.//span/following-sibling::div)"));
	}
	
	@Test
	public void direct_adjacent() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("span+div");
		assertThat(compileSelectorList.toXPath(), is("(.//span/following-sibling::div[position() = 1])"));
	}
	
	@Test
	public void direct_adjacent_with_class() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("span+div.ball");
		assertThat(compileSelectorList.toXPath(), is("(.//span/following-sibling::div[contains(concat(' ', normalize-space(@class), ' '), ' ball ') and position() = 1])"));
	}
	
	@Test
	public void attribute_contains_prefix() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("[attr|=prefix]");
		assertThat(compileSelectorList.toXPath(), is("(.//*[(@attr = 'prefix' or starts-with(@attr, 'prefix-'))])"));
	}
	
	@Test
	public void attribute_contains_substring() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("[attr*=substring]");
		assertThat(compileSelectorList.toXPath(), is("(.//*[contains(@attr, 'substring')])"));
	}
	
	@Test
	public void attribute_contains_word() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("[attr~=word]");
		assertThat(compileSelectorList.toXPath(), is("(.//*[contains(concat(' ', normalize-space(@attr), ' '), ' word ')])"));
	}
	
	@Test
	public void attribute_ends_with_string() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("[attr$=string]");
		assertThat(compileSelectorList.toXPath(), is("(.//*[substring(@attr, string-length(@attr)-5) = 'string'])"));
	}
	
	@Test
	public void attribute_starts_with_string() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("[attr^=string]");
		assertThat(compileSelectorList.toXPath(), is("(.//*[starts-with(@attr, 'string')])"));
	}
	
	@Test
	public void attribute_equals_string() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("[attr=string]");
		assertThat(compileSelectorList.toXPath(), is("(.//*[@attr = 'string'])"));
	}
	
	@Test
	public void attribute_exists() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("[attr]");
		assertThat(compileSelectorList.toXPath(), is("(.//*[@attr])"));
	}
	
	@Test
	public void descendant_eq() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("div a:eq(0)");
		assertThat(compileSelectorList.toXPath(), is("((.//div//a)[position() = 1])"));
	}
	
	@Test
	public void tag_lt() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("div:lt(1)");
		assertThat(compileSelectorList.toXPath(), is("((.//div)[position() < 2])"));
	}
	
	@Test
	public void tag_lt_negative() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("div:lt(-1)");
		assertThat(compileSelectorList.toXPath(), is("((.//div)[position() < (last()-0)])"));
	}
	
	@Test
	public void not_all() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("*:not(*)");
		assertThat(compileSelectorList.toXPath(), is("(.//*[not((local-name()))])"));
	}
	
	@Test
	public void not_with_selector_list() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("div:not(.c3,.c1)");
		assertThat(compileSelectorList.toXPath(), is(
				"("
						+ ".//div["
						+ "not("
									+ "(local-name() and contains(concat(' ', normalize-space(@class), ' '), ' c3 ')) or (local-name() and contains(concat(' ', normalize-space(@class), ' '), ' c1 '))"
							+ ")"
						+ "]"
				+ ")"));
	}
	
	@Test
	public void has_tag() {
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList("div:has(p)");
		assertThat(compileSelectorList.toXPath(), is("(.//div[.//p])"));
	}
	
}