package io.github.seleniumquery.selectors.attributes;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class IdAttributeCssSelectorTest {

	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void visiblePseudoClass() throws Exception {
    	assertThat($("#myId").size(), is(1));
    	assertThat($("#myId").text(), is("abc"));
    }

}