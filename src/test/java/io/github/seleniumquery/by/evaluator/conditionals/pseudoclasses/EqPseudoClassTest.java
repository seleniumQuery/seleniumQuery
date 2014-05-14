package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.by.selector.SeleniumQueryCssCompilerIntegrationTest.assertSelectorMatchedSetSize;
import static io.github.seleniumquery.by.selector.SeleniumQueryCssCompilerIntegrationTest.compileAndExecute;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.TestInfrastructure;

import org.junit.Before;
import org.junit.Test;

public class EqPseudoClassTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver(1));
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}
	
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
		assertThat(compileAndExecute("div:eq(-3)").get(0).getText(), is("Spider Man"));
		assertThat(compileAndExecute("div:eq(-2)").get(0).getText(), is("Hulk"));
		assertThat(compileAndExecute("div:eq(-1)").get(0).getText(), is("Bozo"));
	}
	
	@Test
	public void eqPseudo() {
		assertSelectorMatchedSetSize(":eq(0)", 1);
		assertSelectorMatchedSetSize(":eq(99)", 0);
	}
	
}