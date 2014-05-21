package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.by.selector.SeleniumQueryCssCompilerIntegrationTest.assertSelectorMatchedSetSize;
import static io.github.seleniumquery.by.selector.SeleniumQueryCssCompilerIntegrationTest.compileAndExecute;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class EqPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	@Test
	public void eqPseudo_with_tag_div() {
		assertSelectorMatchedSetSize("div:eq(0)", 1);
		assertSelectorMatchedSetSize("div:eq(4)", 0);
		assertSelectorMatchedSetSize("div:eq(99)", 0);
	}
	
	@Test
	public void eqPseudo_with_class() {
		assertSelectorMatchedSetSize(".c1:eq(0)", 1);
		assertSelectorMatchedSetSize(".c1:eq(1)", 0);
		assertSelectorMatchedSetSize(".c1:eq(99)", 0);
		
		assertSelectorMatchedSetSize(".w00t:eq(0)", 1);
		assertSelectorMatchedSetSize(".w00t:eq(2)", 1);
		assertSelectorMatchedSetSize(".w00t:eq(3)", 0);
	}
	
	@Test
	public void eqPseudo_with_tag__INDIVIDUAL_CHECK() {
		assertThat(compileAndExecute("div:eq(0)").get(0).getText(), is("Batman"));
		assertThat(compileAndExecute("div:eq(1)").get(0).getText(), is("Spider Man"));
		assertThat(compileAndExecute("div:eq(2)").get(0).getText(), is("Hulk"));
		assertThat(compileAndExecute("div:eq(3)").get(0).getText(), is("Bozo"));
		
		assertThat(compileAndExecute("div:eq(+0)").get(0).getText(), is("Batman"));
		assertThat(compileAndExecute("div:eq(+1)").get(0).getText(), is("Spider Man"));
		assertThat(compileAndExecute("div:eq(+2)").get(0).getText(), is("Hulk"));
		assertThat(compileAndExecute("div:eq(+3)").get(0).getText(), is("Bozo"));
		
		assertThat(compileAndExecute("div:eq(-0)").get(0).getText(), is("Batman"));
		assertThat(compileAndExecute("div:eq(-4)").get(0).getText(), is("Batman"));
		assertThat(compileAndExecute("div:eq(-3)").get(0).getText(), is("Spider Man"));
		assertThat(compileAndExecute("div:eq(-2)").get(0).getText(), is("Hulk"));
		assertThat(compileAndExecute("div:eq(-1)").get(0).getText(), is("Bozo"));
	}
	
	@Test
	public void eqPseudo() {
		assertSelectorMatchedSetSize(":eq(0)", 1);
		assertSelectorMatchedSetSize(":eq(99)", 0);
	}
	
    @Test
    public void testEqSelector() {
        assertThat($("div.c1:eq(0)").text(), is("Batman"));
        assertThat($("div.c2:eq(0)").text(), is("Spider Man"));
        assertThat($("div.c3:eq(0)").text(), is("Hulk"));
        
        assertThat($("div.c1:eq(1)").text(), is(""));

        assertThat($("div:eq(0)").text(), is("Batman"));
        assertThat($("div:eq(1)").text(), is("Spider Man"));
        assertThat($("div:eq(2)").text(), is("Hulk"));
    }
    
	@Test
	public void eqFunction_with_tag_and_class__and_single_set_with_negative_index() {
        assertThat($("div.c1:eq(-1)").text(), is("Batman"));
        assertThat($("div.c2:eq(-1)").text(), is("Spider Man"));
        assertThat($("div.c3:eq(-1)").text(), is("Hulk"));
	}
	
}