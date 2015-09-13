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

package io.github.seleniumquery.by.common.preparser;

import io.github.seleniumquery.by.common.preparser.CssSelectorPreParser.PreParsedSelector;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CSSSelectorPreParserTest {
	
	@Test
	public void transformSelector__should_not_transform_class_or_id_selectors() {
		String selector = "#myId.myClass";
        verifySelectorIsTransformedIntoWithArgs(selector, selector);
	}

    private void verifySelectorIsTransformedIntoWithArgs(String selector, String transformedSelector, String... pseudoFuncionArguments) {
        // given
        // selector given as argument
        // when
        PreParsedSelector preParsedSelector = CssSelectorPreParser.transformSelector(selector);
        // then
        assertThat(preParsedSelector.getTransformedSelector(), is(transformedSelector));

        assertThat(preParsedSelector.getArgumentMap().size(), is(pseudoFuncionArguments.length));
        for (int i = 0; i < pseudoFuncionArguments.length; i++) {
            assertThat(preParsedSelector.getArgumentMap().get(i), is(pseudoFuncionArguments[i]));
        }
    }

	@Test
	public void transformSelector__should_not_confuse_escaped_colons_with_pseudoclasses() {
		String selector = "#form1\\:myId";
        verifySelectorIsTransformedIntoWithArgs(selector, selector);
	}

	@Test
	public void transformSelector__should_not_transform_attribute_selectors() {
		String selector = "[myAttr^=myValue][myAttr=myValue][myAttr$=myValue][myAttr|=myValue][myAttr~=myValue][myAttr*=myValue]";
        verifySelectorIsTransformedIntoWithArgs(selector, selector);
	}

	@Test
	public void transformSelector__should_not_transform_pseudoclasses_with_no_contents() {
		String selector = ":only-of-type";
        verifySelectorIsTransformedIntoWithArgs(selector, selector);
	}

	@Test
	public void transformSelector__should_not_transform_pseudoclasses_with_numeric_contents() {
		verifySelectorIsTransformedIntoWithArgs(":eq(1)", ":eq(0)", "1");
	}

	@Test
	public void transformSelector__should_transform_not_in_not_SQ_and_its_content_should_be_indexed_in_the_map() {
		verifySelectorIsTransformedIntoWithArgs(":not(span)", ":not-sq(0)", "span");
	}
	
	@Test
	public void transformSelector__should_transform_lang_in_lang_SQ_and_its_content_should_be_indexed_in_the_map() {
		verifySelectorIsTransformedIntoWithArgs(":lang(pt-BR)", ":lang-sq(0)", "pt-BR");
	}
	
	@Test
	public void transformSelector__should_transform_not_in_not_SQ_and_its_content_should_be_indexed_in_the_map__even_if_its_content_is_not_a_selector() {
		verifySelectorIsTransformedIntoWithArgs("div:not('span')", "div:not-sq(0)", "'span'");
	}

	@Test
	public void transformSelector__should_transform_whatever_content_is_inside_the_pseudoclasses_even_if_nested() {
        verifySelectorIsTransformedIntoWithArgs("div:not(span:contains('a\"bc')):contains(\"a'b\")", "div:not-sq(0):contains(1)", "span:contains('a\"bc')", "\"a'b\"");
	}
	
	@Test
	public void transformSelector__should_not_count_bracers_that_are_inside_strings() {
		verifySelectorIsTransformedIntoWithArgs("div:contains('a)b')", "div:contains(0)", "'a)b'");
	}
	
	@Test
	public void transformSelector__should_allow_escaping_arbitrary_chars() {
		verifySelectorIsTransformedIntoWithArgs("div:contains(ab\\xc)", "div:contains(0)", "ab\\xc");
	}
	
	@Test
	public void transformSelector__should_allow_escaping_braces_and_swallow_the_slashes() {
		verifySelectorIsTransformedIntoWithArgs("div:contains(ab\\)c)", "div:contains(0)", "ab)c");
	}
	
	@Test
	public void transformSelector__should_keep_strings_in_the_selectors_when_they_are_within_attribute_selectors() {
        String selector = "a[id$='test:0']";
        verifySelectorIsTransformedIntoWithArgs(selector, selector);
	}
	
//	jQuery and sizzle have the most absolute non-sense way of parsing strings inside pseudos! The tests below are not implemented!
	// http://regex101.com/r/xC3cF6
//	@Test
	public void transformSelector__crazy1() {
		verifySelectorIsTransformedIntoWithArgs("div:contains('a'b):has(span')", "div:contains(0)", "'a'b):has(span'");
	}
	
//	@Test
	public void transformSelector__crazy2() {
        verifySelectorIsTransformedIntoWithArgs("div:contains('a'b):has(span)", "div:contains(0):has(1)", "'a'b", "span");
	}

}