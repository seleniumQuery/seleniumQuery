package io.github.seleniumquery.by;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SeleniumQueryByTest {

	@Test
	public void testIsXPathExpression() {
		// given
		String selector = "(//table//a)[1]";
		// when
		final boolean isXPath = SeleniumQueryBy.isXPathExpression(selector);
		// then
		assertThat(isXPath, is(true));
	}

}