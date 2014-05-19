package io.github.seleniumquery.by.selector;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.TestInfrastructure;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class SeleniumQueryCssCompilerIntegrationTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}
	
	public static List<WebElement> compileAndExecute(String selector) {
		CompiledSelectorList csl = SeleniumQueryCssCompiler.compileSelectorList($.browser.getDefaultDriver(), selector);
		List<WebElement> execute = csl.execute($.browser.getDefaultDriver());
		return execute;
	}
	
	public static void assertSelectorMatchedSetSize(String selector, int size) {
		List<WebElement> elements = compileAndExecute(selector);
		assertThat(elements, hasSize(size));
	}
	
	@Test
	public void id() {
		List<WebElement> elements = compileAndExecute("#d1");
		
		assertThat(elements, hasSize(1));
		assertThat(elements.get(0).toString(), is("<div id=\"d1\" class=\"clz\">"));
	}
	
	@Test
	public void id_with_escape() {
		List<WebElement> elements = compileAndExecute("#must\\:escape");
		
		assertThat(elements, hasSize(1));
		assertThat(elements.get(0).toString(), is("<h1 id=\"must:escape\">"));
	}
	
	@Test
	public void tag() throws Exception {
		List<WebElement> elements = compileAndExecute("div");
		
		assertThat(elements, hasSize(3));
		assertThat(elements.get(0).toString(), is("<div id=\"d1\" class=\"clz\">"));
		assertThat(elements.get(1).toString(), is("<div id=\"d11\" class=\"clz1\">"));
		assertThat(elements.get(2).toString(), is("<div id=\"d2\" class=\"clzx\">"));
	}

    @Test
    public void tag_and_class() throws Exception {
		List<WebElement> elements = compileAndExecute("div.clz");
		
		assertThat(elements, hasSize(1));
		assertThat(elements.get(0).toString(), is("<div id=\"d1\" class=\"clz\">"));
    }
    
    @Test
    public void tag_and_tag_descendant() throws Exception {
    	List<WebElement> elements = compileAndExecute("div div");
    	
    	assertThat(elements, hasSize(1));
    	assertThat(elements.get(0).toString(), is("<div id=\"d11\" class=\"clz1\">"));
    }

    @Test
    public void tag_and_class_AND_tag_and_class_descendant() throws Exception {
		List<WebElement> elements = compileAndExecute("div.clz select.clz");
		
		assertThat(elements, hasSize(1));
		assertThat(elements.get(0).toString(), is("<select id=\"s1\" class=\"clz\">"));
    }
    
    @Test
    public void hidden_pseudo() throws Exception {
    	List<WebElement> elements = compileAndExecute("p:hidden");
    	
    	assertThat(elements, hasSize(1));
    	assertThat(elements.get(0).toString(), is("<p id=\"hiddenP\" class=\"clz\" style=\"display: none\">"));
    }
    
    @Test
    public void hidden_pseudo_as_parent_and_descendant() {
    	List<WebElement> elements = compileAndExecute("p:hidden span.spanYo:hidden");
    	
    	assertThat(elements, hasSize(1));
    	assertThat(elements.get(0).toString(), is("<span class=\"spanYo\" style=\"display: none\">"));
    }
    
    @Test
    public void tag_and_tag_direct_adjacent() {
    	List<WebElement> elements = compileAndExecute("option + option");
    	
    	assertThat(elements, hasSize(2));
    	assertThat(elements.get(0).toString(), is("<option id=\"o11\" class=\"opt\" value=\"\" selected=\"\">"));
    	assertThat(elements.get(1).toString(), is("<option id=\"o22\" class=\"opt\" value=\"\" selected=\"\">"));
    }
    
    @Test
    public void tag_and_tag_direct_adjacent_with_pseudo() {
    	List<WebElement> elements = compileAndExecute("span.spanYo:hidden + span:hidden");
    	
    	assertThat(elements, hasSize(1));
    	assertThat(elements.get(0).toString(), is("<span class=\"yo2\" style=\"display: none\">"));
    }
    
    @Test
    public void tag_class_and_tag_general_adjacent() {
    	List<WebElement> elements = compileAndExecute("div.clz ~ h1");
    	
    	assertThat(elements, hasSize(1));
    	assertThat(elements.get(0).toString(), is("<h1 id=\"must:escape\">"));
    }
    
    @Test
    public void tag_and_tag_general_adjacent_with_pseudo() {
    	List<WebElement> elements = compileAndExecute(".spanYo:hidden ~ button");
    	
    	assertThat(elements, hasSize(2));
    	assertThat(elements.get(0).toString(), is("<button class=\"btnn\" />"));
    	assertThat(elements.get(1).toString(), is("<button id=\"bA\" style=\"display: none\" />"));
    }
    
    @Test
    public void tag_class_and_tag_child_selector() {
    	List<WebElement> elements = compileAndExecute("select > option");
    	
    	assertThat(elements, hasSize(4));
    	assertThat(elements.get(0).toString(), is("<option id=\"o1\" value=\"\">"));
    	assertThat(elements.get(1).toString(), is("<option id=\"o11\" class=\"opt\" value=\"\" selected=\"\">"));
    	assertThat(elements.get(2).toString(), is("<option id=\"o2\" value=\"\">"));
    	assertThat(elements.get(3).toString(), is("<option id=\"o22\" class=\"opt\" value=\"\" selected=\"\">"));
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
		CompiledSelectorList csl = SeleniumQueryCssCompiler.compileSelectorList($.browser.getDefaultDriver(), selector);
		// then
		assertThat(csl.css.size(), is(1));
		assertThat(csl.css.get(0).getCssSelector(), is("*"+selector));
    }
    
    @Test
    public void selector_compiler_should_send_raw_pseudo_without_contents_if_it_is_unsupported() {
    	// given
    	String selector = ":random-unsupported-pseudo-without-braces";
    	// when
    	CompiledSelectorList csl = SeleniumQueryCssCompiler.compileSelectorList($.browser.getDefaultDriver(), selector);
    	// then
    	assertThat(csl.css.size(), is(1));
    	assertThat(csl.css.get(0).getCssSelector(), is("*"+selector));
    }
    
    
}