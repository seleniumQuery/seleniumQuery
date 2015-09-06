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

package io.github.seleniumquery.by.firstgen.preparser;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NotEqualsAttributeSelectorFixTest {
	
	NotEqualsAttributeSelectorFix notEqualsAttributeSelectorFix = new NotEqualsAttributeSelectorFix();

	@Test
	public void removeStrings__should_turn_all_quoted_strings_into_an_underscore_sequence() {
		// given
		String input = "dq\"a2b\"dq " // double quotes base case: dq"a2b"dq
				+ ":d2q\"a2b\\\"c3b\"d2q " // double with escaped double quotes inside: :d2q"a2b\"c3b"d2q
				+ "dsds'xyz' de c'azv\"aeae' 'aa\\'aa' a'aa\\\"aa'xy";
		// when
		String output = notEqualsAttributeSelectorFix.removeStrings(input);
		// then
		assertThat(output, is(equalTo("dq_____dq :d2q__________d2q dsds_____ de c__________ ________ a________xy")));
	}
	
	@Test
	public void removeContains__should_turn_the_content_inside_contains_into_an_underscore_sequence() {
		// given
		String input = "div:contains([rel!='nofollow']).className";
		// when
		String output = notEqualsAttributeSelectorFix.removeContains(input);
		// then
		assertThat(output, is(equalTo("div____________________________.className")));
	}
	
	@Test
	public void removeContains__should_ignore_escaped_contains() {
		// given
		String input = "div\\:contains([rel!='nofollow']).className";
		// when
		String output = notEqualsAttributeSelectorFix.removeContains(input);
		// then
		assertThat(output, is(equalTo("div\\:contains([rel!='nofollow']).className")));
	}
	
	@Test
	public void removeContains__should_not_confuse_almost_escaped_contains() {
		// given
		String input = "div\\\\:contains([rel!='nofollow']).className";
		// when
		String output = notEqualsAttributeSelectorFix.removeContains(input);
		// then
		assertThat(output, is(equalTo("div\\\\____________________________.className")));
	}
	
	@Test
	public void fixAttributeNotEquals__should_change_attribute_not_equals_into_a_NOT_attribute_equals() {
		// given
		String input = "div[rel!='nofollow'].className";
		// when
		String output = notEqualsAttributeSelectorFix.turnAttributeNotEqualsIntoNotAttributeEquals(input);
		// then
		assertThat(output, is(equalTo("div:not([rel='nofollow']).className")));
	}
	
	@Test
	public void fixAttributeNotEquals__2() {
		assertThat(notEqualsAttributeSelectorFix.turnAttributeNotEqualsIntoNotAttributeEquals("a[rel!='nofollow']"), is(equalTo("a:not([rel='nofollow'])")));
		assertThat(notEqualsAttributeSelectorFix.turnAttributeNotEqualsIntoNotAttributeEquals("div:contains([rel!='nofollow'])"), is(equalTo("div:contains([rel!='nofollow'])")));
		assertThat(notEqualsAttributeSelectorFix.turnAttributeNotEqualsIntoNotAttributeEquals("div:contains(\"[rel!='nofollow']\")"), is(equalTo("div:contains(\"[rel!='nofollow']\")")));
		assertThat(notEqualsAttributeSelectorFix.turnAttributeNotEqualsIntoNotAttributeEquals("div\\\\:contains([rel!='nofollow'])"), is(equalTo("div\\\\:contains([rel!='nofollow'])")));
		// belo is an invalid selector, but, still, as the : in contains seems to be escaped,
		// it does not consider it a contains, so the value in () is not a string but a selector, thus needs to be changed
		assertThat(notEqualsAttributeSelectorFix.turnAttributeNotEqualsIntoNotAttributeEquals("div\\:contains([rel!='nofollow'])"), is(equalTo("div\\:contains(:not([rel='nofollow']))")));
	}

}