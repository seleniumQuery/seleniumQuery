package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class VisibleSelectorTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void visiblePseudoClass() throws Exception {
    	assertThat($("#visibleDiv").is(":visible"), is(true));
    	assertThat($("#visibleDiv2").is(":visible"), is(true));
    	assertThat($("#invisibleDiv").is(":visible"), is(false));
    	
    	assertThat($("#invisibleParentDiv").is(":visible"), is(false));
    	assertThat($("#almostVisibleDiv").is(":visible"), is(false));
    }

}