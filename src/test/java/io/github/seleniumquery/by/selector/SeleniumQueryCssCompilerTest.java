package io.github.seleniumquery.by.selector;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.HiddenPseudoClass;

import org.junit.Before;
import org.junit.Test;

public class SeleniumQueryCssCompilerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCompileSelector() {
		CompiledSelectorList compileSelectorList = SeleniumQueryCssCompiler.compileSelectorList(null, ":hidden");
		
		assertThat(compileSelectorList.css, hasSize(1));
		
		CompiledSelector compiledSelector = compileSelectorList.css.get(0);
		assertEquals("*", compiledSelector.getCssSelector());
		for (SqCSSFilter f : compiledSelector.getCssFilter()) {
			System.out.println(f);
		}
		assertThat(compiledSelector.getCssFilter(), hasSize(1));
		assertThat(compiledSelector.getCssFilter().get(0), is(HiddenPseudoClass.HiddenPseudoClassFilter));
	}

}
