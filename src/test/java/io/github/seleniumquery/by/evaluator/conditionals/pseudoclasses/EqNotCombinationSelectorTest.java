package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class EqNotCombinationSelectorTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(EqPseudoClassTest.class);

    @Test
    public void eqPseudoClass_combinated_with_notPseudoClass() throws Exception {
        assertThat($("div:eq(0):not(span)").text(), is("Batman"));
        assertThat($("div:eq(1):not(div)").text(), is(""));
        assertThat($("div:eq(1):not(.w00t)").text(), is("Spider Man"));
        assertThat($("div:eq(2):not(.w00t)").text(), is(""));
    }

}