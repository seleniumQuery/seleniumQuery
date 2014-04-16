package io.github.seleniumquery.functions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static io.github.seleniumquery.functions.NotPresentParser.NOT_PRESENT_PARSER;

import org.junit.Test;

public class IsFunctionNotPresentParserTest {

	@Test
	public void hasNegatedPresentSelector() {
		assertThat(NOT_PRESENT_PARSER.hasNegatedPresentSelector(":present"), is(false));
		assertThat(NOT_PRESENT_PARSER.hasNegatedPresentSelector(":not(.test):present"), is(false));
		assertThat(NOT_PRESENT_PARSER.hasNegatedPresentSelector(":not(:not(.test):present)"), is(true));
		assertThat(NOT_PRESENT_PARSER.hasNegatedPresentSelector(":present:enabled"), is(false));
		assertThat(NOT_PRESENT_PARSER.hasNegatedPresentSelector(":present:enabled:present"), is(false));
		assertThat(NOT_PRESENT_PARSER.hasNegatedPresentSelector(":not(:not(.test):present):present"), is(true));
		assertThat(NOT_PRESENT_PARSER.hasNegatedPresentSelector(":not(:not(.test):presents):present"), is(false));
		assertThat(NOT_PRESENT_PARSER.hasNegatedPresentSelector(":not(:not(.test):presents):present:not(:present)"), is(true));
		
		assertThat(NOT_PRESENT_PARSER.hasNegatedPresentSelector(":not(:not(.test):present abc)"), is(true));
		assertThat(NOT_PRESENT_PARSER.hasNegatedPresentSelector(":not(:not(.test):present.abc)"), is(true));
		assertThat(NOT_PRESENT_PARSER.hasNegatedPresentSelector(":not(:not(.test):present-other-pseudo)"), is(false));
	}
	
}