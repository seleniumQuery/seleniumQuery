/*
 * Copyright (c) 2015 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.seleniumquery.by.preparser;

import io.github.seleniumquery.by.preparser.CSSSelectorPreParser.PreParsedSelector;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CSSSelectorPreParserTest {
	
	CSSSelectorPreParser cssSelectorPreParser = new CSSSelectorPreParser();
	
	@Test
	public void transformSelector__should_not_transform_class_or_id_selectors() {
		// given
		String selector = "#myId.myClass";
		// when
		PreParsedSelector preParsedSelector = cssSelectorPreParser.transformSelector(selector);
		// then
		assertThat(preParsedSelector.getStringMap().size(), is(0));
		assertThat(preParsedSelector.getTransformedSelector(), is(selector));
	}
	
	@Test
	public void transformSelector__should_not_confuse_escaped_colons_with_pseudoclasses() {
		// given
		String selector = "#form1\\:myId";
		// when
		PreParsedSelector preParsedSelector = cssSelectorPreParser.transformSelector(selector);
		// then
		assertThat(preParsedSelector.getStringMap().size(), is(0));
		assertThat(preParsedSelector.getTransformedSelector(), is(selector));
	}
	
	@Test
	public void transformSelector__should_not_transform_attribute_selectors() {
		// given
		String selector = "[myAttr^=myValue][myAttr=myValue][myAttr$=myValue][myAttr|=myValue][myAttr~=myValue][myAttr*=myValue]";
		// when
		PreParsedSelector preParsedSelector = cssSelectorPreParser.transformSelector(selector);
		// then
		assertThat(preParsedSelector.getStringMap().size(), is(0));
		assertThat(preParsedSelector.getTransformedSelector(), is(selector));
	}
	
	@Test
	public void transformSelector__should_not_transform_pseudoclasses_with_no_contents() {
		// given
		String selector = ":only-of-type";
		// when
		PreParsedSelector preParsedSelector = cssSelectorPreParser.transformSelector(selector);
		// then
		assertThat(preParsedSelector.getStringMap().size(), is(0));
		assertThat(preParsedSelector.getTransformedSelector(), is(":only-of-type"));
	}
	
	@Test
	public void transformSelector__should_not_transform_pseudoclasses_with_numeric_contents() {
		// given
		String selector = ":eq(1)";
		// when
		PreParsedSelector preParsedSelector = cssSelectorPreParser.transformSelector(selector);
		// then
		assertThat(preParsedSelector.getStringMap().size(), is(1));
		assertThat(preParsedSelector.getTransformedSelector(), is(":eq(0)"));
		assertThat(preParsedSelector.getStringMap().get("0"), is("1"));
	}
	
	@Test
	public void transformSelector__should_transform_not_in_not_SQ_and_its_content_should_be_indexed_in_the_map() {
		// given
		String selector = ":not(span)";
		// when
		PreParsedSelector preParsedSelector = cssSelectorPreParser.transformSelector(selector);
		// then
		assertThat(preParsedSelector.getStringMap().size(), is(1));
		assertThat(preParsedSelector.getTransformedSelector(), is(":not-sq(0)"));
		assertThat(preParsedSelector.getStringMap().get("0"), is("span"));
	}
	
	@Test
	public void transformSelector__should_transform_lang_in_lang_SQ_and_its_content_should_be_indexed_in_the_map() {
		// given
		String selector = ":lang(pt-BR)";
		// when
		PreParsedSelector preParsedSelector = cssSelectorPreParser.transformSelector(selector);
		// then
		assertThat(preParsedSelector.getStringMap().size(), is(1));
		assertThat(preParsedSelector.getTransformedSelector(), is(":lang-sq(0)"));
		assertThat(preParsedSelector.getStringMap().get("0"), is("pt-BR"));
	}
	
	@Test
	public void transformSelector__should_transform_not_in_not_SQ_and_its_content_should_be_indexed_in_the_map__even_if_its_content_is_not_a_selector() {
		// given
		String selector = "div:not('span')";
		// when
		PreParsedSelector preParsedSelector = cssSelectorPreParser.transformSelector(selector);
		// then
		assertThat(preParsedSelector.getStringMap().size(), is(1));
		assertThat(preParsedSelector.getTransformedSelector(), is("div:not-sq(0)"));
		assertThat(preParsedSelector.getStringMap().get("0"), is("'span'"));
	}

	@Test
	public void transformSelector__should_transform_whatever_content_is_inside_the_pseudoclasses_even_if_nested() {
		// given
		String selector = "div:not(span:contains('a\"bc')):contains(\"a'b\")";
		// when
		PreParsedSelector preParsedSelector = cssSelectorPreParser.transformSelector(selector);
		// then
		assertThat(preParsedSelector.getStringMap().size(), is(2));
		assertThat(preParsedSelector.getTransformedSelector(), is("div:not-sq(0):contains(1)"));
		assertThat(preParsedSelector.getStringMap().get("0"), is("span:contains('a\"bc')"));
		assertThat(preParsedSelector.getStringMap().get("1"), is("\"a'b\""));
	}
	
	@Test
	public void transformSelector__should_not_count_bracers_that_are_inside_strings() {
		// given
		String selector = "div:contains('a)b')";
		// when
		PreParsedSelector preParsedSelector = cssSelectorPreParser.transformSelector(selector);
		// then
		assertThat(preParsedSelector.getStringMap().size(), is(1));
		assertThat(preParsedSelector.getTransformedSelector(), is("div:contains(0)"));
		assertThat(preParsedSelector.getStringMap().get("0"), is("'a)b'"));
	}
	
	@Test
	public void transformSelector__should_allow_escaping_arbitrary_chars() {
		// given
		String selector = "div:contains(ab\\xc)";
		// when
		PreParsedSelector preParsedSelector = cssSelectorPreParser.transformSelector(selector);
		// then
		assertThat(preParsedSelector.getStringMap().size(), is(1));
		assertThat(preParsedSelector.getTransformedSelector(), is("div:contains(0)"));
		assertThat(preParsedSelector.getStringMap().get("0"), is("ab\\xc"));
	}
	
	@Test
	public void transformSelector__should_allow_escaping_braces_and_swallow_the_slashes() {
		// given
		String selector = "div:contains(ab\\)c)";
		// when
		PreParsedSelector preParsedSelector = cssSelectorPreParser.transformSelector(selector);
		// then
		assertThat(preParsedSelector.getStringMap().size(), is(1));
		assertThat(preParsedSelector.getTransformedSelector(), is("div:contains(0)"));
		assertThat(preParsedSelector.getStringMap().get("0"), is("ab)c"));
	}
	
	@Test
	public void transformSelector__should_keep_strings_in_the_selectors_when_they_are_within_attribute_selectors() {
		// given
		String selector = "a[id$='test:0']";
		// when
		PreParsedSelector preParsedSelector = cssSelectorPreParser.transformSelector(selector);
		// then
		assertThat(preParsedSelector.getStringMap().size(), is(0));
		assertThat(preParsedSelector.getTransformedSelector(), is("a[id$='test:0']"));
	}
	
//	jQuery and sizzle have the most absolute no-sense way of parsing strings inside pseudos!
	// http://regex101.com/r/xC3cF6
//	@Test
	public void transformSelector__crazy1() {
		// given
		String selector = "div:contains('a'b):has(span')";
		// when
		PreParsedSelector preParsedSelector = cssSelectorPreParser.transformSelector(selector);
		// then
		assertThat(preParsedSelector.getStringMap().size(), is(1));
		assertThat(preParsedSelector.getTransformedSelector(), is("div:contains(0)"));
		assertThat(preParsedSelector.getStringMap().get("0"), is("'a'b):has(span'"));
	}
	
//	@Test
	public void transformSelector__crazy2() {
		// given
		String selector = "div:contains('a'b):has(span)";
		// when
		PreParsedSelector preParsedSelector = cssSelectorPreParser.transformSelector(selector);
		// then
		assertThat(preParsedSelector.getStringMap().size(), is(2));
		assertThat(preParsedSelector.getTransformedSelector(), is("div:contains(0):has(1)"));
		assertThat(preParsedSelector.getStringMap().get("0"), is("'a'b"));
		assertThat(preParsedSelector.getStringMap().get("1"), is("span"));
	}
	
}