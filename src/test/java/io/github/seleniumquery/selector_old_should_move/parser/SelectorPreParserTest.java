package io.github.seleniumquery.selector_old_should_move.parser;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.by.selector.parser.SelectorPreParser;
import io.github.seleniumquery.by.selector.parser.TransformedSelector;

import org.junit.Test;

public class SelectorPreParserTest {
	
	SelectorPreParser selectorPreparator = new SelectorPreParser();
	
	@Test
	public void transformSelector__should_not_transform_class_or_id_selectors() {
		// given
		String selector = "#myId.myClass";
		// when
		TransformedSelector transformedSelector = selectorPreparator.transformSelector(selector);
		// then
		assertThat(transformedSelector.getStringMap().size(), is(0));
		assertThat(transformedSelector.getTransformedSelector(), is(selector));
	}
	
	@Test
	public void transformSelector__should_not_confuse_escaped_colons_with_pseudoclasses() {
		// given
		String selector = "#form1\\:myId";
		// when
		TransformedSelector transformedSelector = selectorPreparator.transformSelector(selector);
		// then
		assertThat(transformedSelector.getStringMap().size(), is(0));
		assertThat(transformedSelector.getTransformedSelector(), is(selector));
	}
	
	@Test
	public void transformSelector__should_not_transform_attribute_selectors() {
		// given
		String selector = "[myAttr^=myValue][myAttr=myValue][myAttr$=myValue][myAttr|=myValue][myAttr~=myValue][myAttr*=myValue]";
		// when
		TransformedSelector transformedSelector = selectorPreparator.transformSelector(selector);
		// then
		assertThat(transformedSelector.getStringMap().size(), is(0));
		assertThat(transformedSelector.getTransformedSelector(), is(selector));
	}
	
	@Test
	public void transformSelector__should_not_transform_pseudoclasses_with_no_contents() {
		// given
		String selector = ":only-of-type";
		// when
		TransformedSelector transformedSelector = selectorPreparator.transformSelector(selector);
		// then
		assertThat(transformedSelector.getStringMap().size(), is(0));
		assertThat(transformedSelector.getTransformedSelector(), is(":only-of-type"));
	}
	
	@Test
	public void transformSelector__should_not_transform_pseudoclasses_with_numeric_contents() {
		// given
		String selector = ":eq(1)";
		// when
		TransformedSelector transformedSelector = selectorPreparator.transformSelector(selector);
		// then
		assertThat(transformedSelector.getStringMap().size(), is(1));
		assertThat(transformedSelector.getTransformedSelector(), is(":eq(0)"));
		assertThat(transformedSelector.getStringMap().get("0"), is("1"));
	}
	
	@Test
	public void transformSelector__should_transform_not_in_not_SQ_and_its_content_should_be_indexed_in_the_map() {
		// given
		String selector = ":not(span)";
		// when
		TransformedSelector transformedSelector = selectorPreparator.transformSelector(selector);
		// then
		assertThat(transformedSelector.getStringMap().size(), is(1));
		assertThat(transformedSelector.getTransformedSelector(), is(":not-sq(0)"));
		assertThat(transformedSelector.getStringMap().get("0"), is("span"));
	}
	
	@Test
	public void transformSelector__should_transform_lang_in_lang_SQ_and_its_content_should_be_indexed_in_the_map() {
		// given
		String selector = ":lang(pt-BR)";
		// when
		TransformedSelector transformedSelector = selectorPreparator.transformSelector(selector);
		// then
		assertThat(transformedSelector.getStringMap().size(), is(1));
		assertThat(transformedSelector.getTransformedSelector(), is(":lang-sq(0)"));
		assertThat(transformedSelector.getStringMap().get("0"), is("pt-BR"));
	}
	
	@Test
	public void transformSelector__should_transform_not_in_not_SQ_and_its_content_should_be_indexed_in_the_map__even_if_its_content_is_not_a_selector() {
		// given
		String selector = "div:not('span')";
		// when
		TransformedSelector transformedSelector = selectorPreparator.transformSelector(selector);
		// then
		assertThat(transformedSelector.getStringMap().size(), is(1));
		assertThat(transformedSelector.getTransformedSelector(), is("div:not-sq(0)"));
		assertThat(transformedSelector.getStringMap().get("0"), is("'span'"));
	}

	@Test
	public void transformSelector__should_transform_whatever_content_is_inside_the_pseudoclasses_even_if_nested() {
		// given
		String selector = "div:not(span:contains('a\"bc')):contains(\"a'b\")";
		// when
		TransformedSelector transformedSelector = selectorPreparator.transformSelector(selector);
		// then
		assertThat(transformedSelector.getStringMap().size(), is(2));
		assertThat(transformedSelector.getTransformedSelector(), is("div:not-sq(0):contains(1)"));
		assertThat(transformedSelector.getStringMap().get("0"), is("span:contains('a\"bc')"));
		assertThat(transformedSelector.getStringMap().get("1"), is("\"a'b\""));
	}
	
	@Test
	public void transformSelector__should_not_count_bracers_that_are_inside_strings() {
		// given
		String selector = "div:contains('a)b')";
		// when
		TransformedSelector transformedSelector = selectorPreparator.transformSelector(selector);
		// then
		assertThat(transformedSelector.getStringMap().size(), is(1));
		assertThat(transformedSelector.getTransformedSelector(), is("div:contains(0)"));
		assertThat(transformedSelector.getStringMap().get("0"), is("'a)b'"));
	}
	
	@Test
	public void transformSelector__should_allow_escaping_arbitrary_chars() {
		// given
		String selector = "div:contains(ab\\xc)";
		// when
		TransformedSelector transformedSelector = selectorPreparator.transformSelector(selector);
		// then
		assertThat(transformedSelector.getStringMap().size(), is(1));
		assertThat(transformedSelector.getTransformedSelector(), is("div:contains(0)"));
		assertThat(transformedSelector.getStringMap().get("0"), is("ab\\xc"));
	}
	
	@Test
	public void transformSelector__should_allow_escaping_braces_and_swallow_the_slashes() {
		// given
		String selector = "div:contains(ab\\)c)";
		// when
		TransformedSelector transformedSelector = selectorPreparator.transformSelector(selector);
		// then
		assertThat(transformedSelector.getStringMap().size(), is(1));
		assertThat(transformedSelector.getTransformedSelector(), is("div:contains(0)"));
		assertThat(transformedSelector.getStringMap().get("0"), is("ab)c"));
	}
	
	@Test
	public void transformSelector__should_keep_strings_in_the_selectors_when_they_are_within_attribute_selectors() {
		// given
		String selector = "a[id$='test:0']";
		// when
		TransformedSelector transformedSelector = selectorPreparator.transformSelector(selector);
		// then
		assertThat(transformedSelector.getStringMap().size(), is(0));
		assertThat(transformedSelector.getTransformedSelector(), is("a[id$='test:0']"));
	}
	
//	jQuery and sizzle have the most absolute no-sense way of parsing strings inside pseudos!
	// http://regex101.com/r/xC3cF6
//	@Test
	public void transformSelector__crazy1() {
		// given
		String selector = "div:contains('a'b):has(span')";
		// when
		TransformedSelector transformedSelector = selectorPreparator.transformSelector(selector);
		// then
		assertThat(transformedSelector.getStringMap().size(), is(1));
		assertThat(transformedSelector.getTransformedSelector(), is("div:contains(0)"));
		assertThat(transformedSelector.getStringMap().get("0"), is("'a'b):has(span'"));
	}
	
//	@Test
	public void transformSelector__crazy2() {
		// given
		String selector = "div:contains('a'b):has(span)";
		// when
		TransformedSelector transformedSelector = selectorPreparator.transformSelector(selector);
		// then
		assertThat(transformedSelector.getStringMap().size(), is(2));
		assertThat(transformedSelector.getTransformedSelector(), is("div:contains(0):has(1)"));
		assertThat(transformedSelector.getStringMap().get("0"), is("'a'b"));
		assertThat(transformedSelector.getStringMap().get("1"), is("span"));
	}
	
}