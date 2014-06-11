package io.github.seleniumquery.selector;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;
import io.github.seleniumquery.selector.CompiledCssSelectorList;
import io.github.seleniumquery.selector.CssSelectorCompilerService;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class CssSelectorCompilerServiceTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	public static List<WebElement> compileAndExecute(String selector) {
		CompiledCssSelectorList csl = CssSelectorCompilerService.compileSelectorList($.browser.getDefaultDriver(), selector);
		List<WebElement> execute = csl.execute($.browser.getDefaultDriver());
		return execute;
	}
	
	public static void assertSelectorMatchedSetSize(String selector, int size) {
		List<WebElement> elements = compileAndExecute(selector);
		assertThat(elements, hasSize(size));
	}
	
	@Test
	public void parts() {
//		CompiledCssSelectorList csl = CssSelectorCompilerService.compileSelectorList($.browser.getDefaultDriver(), ".rich-data-scr");
		CompiledCssSelectorList csl = CssSelectorCompilerService.compileSelectorList($.browser.getDefaultDriver(),
				"table.rich-dtascroller-table tr td:nth-child(5)");
		
		final List<CompiledCssSelector> compS = csl.css;
		
		for (CompiledCssSelector compiledCssSelector : compS) {
			System.out.println(compiledCssSelector.getCssSelector());
			System.out.println(compiledCssSelector.getCssFilter().get(0));
		}
		
//		List<WebElement> execute = csl.execute($.browser.getDefaultDriver());
//		List<WebElement> elements = execute;
//		
//		assertThat(elements, hasSize(1));
//		assertThat(elements.get(0).getTagName(), is("div"));
//		assertThat(elements.get(0).getAttribute("id"), is("d1"));
//		assertThat(elements.get(0).getAttribute("class"), is("clz"));
	}
	
	@Test
	public void id() {
		List<WebElement> elements = compileAndExecute("#d1");
		
		assertThat(elements, hasSize(1));
    	assertThat(elements.get(0).getTagName(), is("div"));
    	assertThat(elements.get(0).getAttribute("id"), is("d1"));
    	assertThat(elements.get(0).getAttribute("class"), is("clz"));
	}
	
	@Test
	public void id_with_escape() {
		List<WebElement> elements = compileAndExecute("#must\\:escape");
		
		assertThat(elements, hasSize(1));
    	assertThat(elements.get(0).getTagName(), is("h1"));
    	assertThat(elements.get(0).getAttribute("id"), is("must:escape"));
	}
	
	@Test
	public void tag() {
		List<WebElement> elements = compileAndExecute("div");
		
		assertThat(elements, hasSize(3));
    	assertThat(elements.get(0).getTagName(), is("div"));
    	assertThat(elements.get(0).getAttribute("id"), is("d1"));
    	assertThat(elements.get(0).getAttribute("class"), is("clz"));
		
    	assertThat(elements.get(1).getTagName(), is("div"));
    	assertThat(elements.get(1).getAttribute("id"), is("d11"));
    	assertThat(elements.get(1).getAttribute("class"), is("clz1"));
    	
    	assertThat(elements.get(2).getTagName(), is("div"));
    	assertThat(elements.get(2).getAttribute("id"), is("d2"));
    	assertThat(elements.get(2).getAttribute("class"), is("clzx"));
	}

    @Test
    public void tag_and_class() {
		List<WebElement> elements = compileAndExecute("div.clz");
		
		assertThat(elements, hasSize(1));
    	assertThat(elements.get(0).getTagName(), is("div"));
    	assertThat(elements.get(0).getAttribute("id"), is("d1"));
    	assertThat(elements.get(0).getAttribute("class"), is("clz"));
    }
    
    @Test
    public void tag_and_tag_descendant() {
    	List<WebElement> elements = compileAndExecute("div div");
    	
    	assertThat(elements, hasSize(1));
    	assertThat(elements.get(0).getTagName(), is("div"));
    	assertThat(elements.get(0).getAttribute("id"), is("d11"));
    	assertThat(elements.get(0).getAttribute("class"), is("clz1"));
    }

    @Test
    public void tag_and_class_AND_tag_and_class_descendant() {
		List<WebElement> elements = compileAndExecute("div.clz select.clz");
		
		assertThat(elements, hasSize(1));
    	assertThat(elements.get(0).getTagName(), is("select"));
    	assertThat(elements.get(0).getAttribute("id"), is("s1"));
    	assertThat(elements.get(0).getAttribute("class"), is("clz"));
    }
    
    @Test
    public void hidden_pseudo() {
    	List<WebElement> elements = compileAndExecute("p:hidden");
    	
    	assertThat(elements, hasSize(1));
    	assertThat(elements.get(0).getTagName(), is("p"));
    	assertThat(elements.get(0).getAttribute("id"), is("hiddenP"));
    	assertThat(elements.get(0).getAttribute("class"), is("clz"));
    	assertThat(elements.get(0).getAttribute("style"), containsString("display: none"));
    }
    
    @Test
    public void hidden_pseudo_as_parent_and_descendant() {
    	List<WebElement> elements = compileAndExecute("p:hidden span.spanYo:hidden");
    	
    	assertThat(elements, hasSize(1));
    	assertThat(elements.get(0).getTagName(), is("span"));
    	assertThat(elements.get(0).getAttribute("class"), is("spanYo"));
    	assertThat(elements.get(0).getAttribute("style"), containsString("display: none"));
    }
    
    @Test
    public void tag_and_tag_direct_adjacent() {
    	List<WebElement> elements = compileAndExecute("option + option");
    	
    	assertThat(elements, hasSize(2));
    	
    	assertThat(elements.get(0).getTagName(), is("option"));
    	assertThat(elements.get(0).getAttribute("id"), is("o11"));
    	assertThat(elements.get(0).getAttribute("class"), is("opt"));
    	assertThat(elements.get(0).getAttribute("value"), is(""));
    	assertThat(elements.get(0).getAttribute("selected"), is("true"));
    	
    	assertThat(elements.get(1).getTagName(), is("option"));
    	assertThat(elements.get(1).getAttribute("id"), is("o22"));
    	assertThat(elements.get(1).getAttribute("class"), is("opt"));
    	assertThat(elements.get(1).getAttribute("value"), is(""));
    }
    
    @Test
    public void tag_and_tag_direct_adjacent_with_pseudo() {
    	List<WebElement> elements = compileAndExecute("span.spanYo:hidden + span:hidden");
    	
    	assertThat(elements, hasSize(1));
    	assertThat(elements.get(0).getTagName(), is("span"));
    	assertThat(elements.get(0).getAttribute("class"), is("yo2"));
    	assertThat(elements.get(0).getAttribute("style"), containsString("display: none"));
    }
    
    @Test
    public void tag_class_and_tag_general_adjacent() {
    	List<WebElement> elements = compileAndExecute("div.clz ~ h1");
    	
    	assertThat(elements, hasSize(1));
    	assertThat(elements.get(0).getTagName(), is("h1"));
    	assertThat(elements.get(0).getAttribute("id"), is("must:escape"));
    }
    
    @Test
    public void tag_and_tag_general_adjacent_with_pseudo() {
    	List<WebElement> elements = compileAndExecute(".spanYo:hidden ~ button");
    	
    	assertThat(elements, hasSize(2));
    	assertThat(elements.get(0).getTagName(), is("button"));
    	assertThat(elements.get(0).getAttribute("class"), is("btnn"));
    	
    	assertThat(elements.get(1).getTagName(), is("button"));
    	assertThat(elements.get(1).getAttribute("id"), is("bA"));
    	assertThat(elements.get(1).getAttribute("style"), containsString("display: none"));
    }
    
    @Test
    public void tag_class_and_tag_child_selector() {
    	List<WebElement> elements = compileAndExecute("select > option");
    	
    	assertThat(elements, hasSize(4));
    	assertThat(elements.get(0).getTagName(), is("option"));
    	assertThat(elements.get(0).getAttribute("id"), is("o1"));
    	assertThat(elements.get(0).getAttribute("value"), is(""));
    	
    	assertThat(elements.get(1).getTagName(), is("option"));
    	assertThat(elements.get(1).getAttribute("id"), is("o11"));
    	assertThat(elements.get(1).getAttribute("class"), is("opt"));
    	assertThat(elements.get(1).getAttribute("value"), is(""));
    	
    	assertThat(elements.get(2).getTagName(), is("option"));
    	assertThat(elements.get(2).getAttribute("id"), is("o2"));
    	assertThat(elements.get(2).getAttribute("value"), is(""));
    	
    	assertThat(elements.get(3).getTagName(), is("option"));
    	assertThat(elements.get(3).getAttribute("id"), is("o22"));
    	assertThat(elements.get(3).getAttribute("class"), is("opt"));
    	assertThat(elements.get(3).getAttribute("value"), is(""));
    }
    
    
    @Test
    public void tag_class_and_tag_child_selector__with_pseudo_on_both() {
    	List<WebElement> elementsZero = compileAndExecute("select:hidden > option");
    	assertThat(elementsZero, hasSize(0));
    	
    	List<WebElement> elements = compileAndExecute("body > :hidden > :hidden");
    	
    	assertThat(elements, hasSize(5));
    	assertThat(elements.get(0).getTagName(), is("button"));
    	assertThat(elements.get(1).getTagName(), is("span"));
    	assertThat(elements.get(2).getTagName(), is("span"));
    	assertThat(elements.get(3).getTagName(), is("button"));
    	assertThat(elements.get(4).getTagName(), is("button"));
    }
    
    @Test
    public void selector_compiler_should_send_raw_pseudo_if_it_is_unsupported() {
    	// given
    	String selector = ":random-unsupported-pseudo(c'o'n\"t\"e'n't)";
    	// when
		CompiledCssSelectorList csl = CssSelectorCompilerService.compileSelectorList($.browser.getDefaultDriver(), selector);
		// then
		assertThat(csl.css.size(), is(1));
		assertThat(csl.css.get(0).getCssSelector(), is("*"+selector));
    }
    
    @Test
    public void selector_compiler_should_send_raw_pseudo_without_contents_if_it_is_unsupported() {
    	// given
    	String selector = ":random-unsupported-pseudo-without-braces";
    	// when
    	CompiledCssSelectorList csl = CssSelectorCompilerService.compileSelectorList($.browser.getDefaultDriver(), selector);
    	// then
    	assertThat(csl.css.size(), is(1));
    	assertThat(csl.css.get(0).getCssSelector(), is("*"+selector));
    }
    
}