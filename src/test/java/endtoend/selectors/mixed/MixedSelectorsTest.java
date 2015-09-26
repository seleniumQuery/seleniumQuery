/*
 * Copyright (c) 2015 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package endtoend.selectors.mixed;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import java.util.List;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class MixedSelectorsTest {

	@ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	@Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;
	
	@Test
	public void id() {
		List<WebElement> elements = $("#d1").get();
		assertIsOnlyOneElementWithDetails(elements, "div", "d1", "clz");
	}

	private void assertIsOnlyOneElementWithDetails(List<WebElement> elements, String div, String idAttribute, String classAttribute) {
		assertThat(elements, hasSize(1));
		assertElementDetails(elements.get(0), div, idAttribute, classAttribute);
	}

	private void assertElementDetails(WebElement element, String div, String idAttribute, String classAttribute) {
		assertThat(element.getTagName(), is(div));
		assertThat(element.getAttribute("id"), is(idAttribute));
		assertThat(element.getAttribute("class"), is(classAttribute));
	}

	@Test
	public void id_with_escape() {
		List<WebElement> elements = $("#must\\:escape").get();
		assertIsOnlyOneElementWithDetails(elements, "h1", "must:escape", null);
	}
	
	@Test
	public void tag() {
		List<WebElement> elements = $("div").get();
		
		assertThat(elements, hasSize(3));
		assertElementDetails(elements.get(0), "div", "d1", "clz");
		assertElementDetails(elements.get(1), "div", "d11", "clz1");
		assertElementDetails(elements.get(2), "div", "d2", "clzx");
	}

    @Test
    public void tag_and_class() {
		List<WebElement> elements = $("div.clz").get();
		assertIsOnlyOneElementWithDetails(elements, "div", "d1", "clz");
    }
    
    @Test
    public void tag_and_tag_descendant() {
    	List<WebElement> elements = $("div div").get();
		assertIsOnlyOneElementWithDetails(elements, "div", "d11", "clz1");
    }

    @Test
    public void tag_and_class_AND_tag_and_class_descendant() {
		List<WebElement> elements = $("div.clz select.clz").get();
		assertIsOnlyOneElementWithDetails(elements, "select", "s1", "clz");
    }
    
    @Test(expected = RuntimeException.class)
    public void hidden_pseudo() {
    	List<WebElement> elements = $("p:hidden").get();

		assertIsOnlyOneElementWithDetails(elements, "p", "hiddenP", "clz");
    	assertThat(elements.get(0).getAttribute("style"), containsString("display: none"));
    }
    
    @Test(expected = RuntimeException.class)
    public void hidden_pseudo_as_parent_and_descendant() {
    	List<WebElement> elements = $("p:hidden span.spanYo:hidden").get();
    	for (WebElement webElement : elements) {
			System.out.println("@# El: "+webElement);
		}
    	
    	assertThat(elements, hasSize(1));
    	assertThat(elements.get(0).getTagName(), is("span"));
    	assertThat(elements.get(0).getAttribute("class"), is("spanYo"));
    	assertThat(elements.get(0).getAttribute("style"), containsString("display: none"));
    }
    
    @Test
    public void tag_and_tag_direct_adjacent() {
    	List<WebElement> elements = $("option + option").get();
    	
    	assertThat(elements, hasSize(2));

		assertElementDetails(elements.get(0), "option", "o11", "opt");
    	assertThat(elements.get(0).getAttribute("value"), is(""));
    	assertThat(elements.get(0).getAttribute("selected"), is("true"));

		assertElementDetails(elements.get(1), "option", "o22", "opt");
    	assertThat(elements.get(1).getAttribute("value"), is(""));
    }
    
    @Test(expected = RuntimeException.class)
    public void tag_and_tag_direct_adjacent_with_pseudo() {
    	List<WebElement> elements = $("span.spanYo:hidden + span:hidden").get();
    	
    	assertThat(elements, hasSize(1));
    	assertThat(elements.get(0).getTagName(), is("span"));
    	assertThat(elements.get(0).getAttribute("class"), is("yo2"));
    	assertThat(elements.get(0).getAttribute("style"), containsString("display: none"));
    }
    
    @Test
    public void tag_class_and_tag_general_adjacent() {
    	List<WebElement> elements = $("div.clz ~ h1").get();
    	
    	assertThat(elements, hasSize(1));
    	assertThat(elements.get(0).getTagName(), is("h1"));
    	assertThat(elements.get(0).getAttribute("id"), is("must:escape"));
    }
    
    @Test(expected = RuntimeException.class)
    public void tag_and_tag_general_adjacent_with_pseudo() {
    	List<WebElement> elements = $(".spanYo:hidden ~ button").get();
    	
    	assertThat(elements, hasSize(2));
    	assertThat(elements.get(0).getTagName(), is("button"));
    	assertThat(elements.get(0).getAttribute("class"), is("btnn"));
    	
    	assertThat(elements.get(1).getTagName(), is("button"));
    	assertThat(elements.get(1).getAttribute("id"), is("bA"));
    	assertThat(elements.get(1).getAttribute("style"), containsString("display: none"));
    }
    
    @Test
    public void tag_class_and_tag_child_selector() {
    	List<WebElement> elements = $("select > option").get();
    	
    	assertThat(elements, hasSize(4));
    	assertThat(elements.get(0).getTagName(), is("option"));
    	assertThat(elements.get(0).getAttribute("id"), is("o1"));
    	assertThat(elements.get(0).getAttribute("value"), is(""));

		assertElementDetails(elements.get(1), "option", "o11", "opt");
    	assertThat(elements.get(1).getAttribute("value"), is(""));
    	
    	assertThat(elements.get(2).getTagName(), is("option"));
    	assertThat(elements.get(2).getAttribute("id"), is("o2"));
    	assertThat(elements.get(2).getAttribute("value"), is(""));

		assertElementDetails(elements.get(3), "option", "o22", "opt");
    	assertThat(elements.get(3).getAttribute("value"), is(""));
    }
    
    
	@Test(expected = RuntimeException.class)
    public void tag_class_and_tag_child_selector__with_pseudo_on_both() {
    	List<WebElement> elementsZero = $("select:hidden > option").get();
    	assertThat(elementsZero, hasSize(0));
    	
    	List<WebElement> elements = $("body > :hidden > :hidden").get();
    	
    	assertThat(elements, hasSize(5));
    	assertThat(elements.get(0).getTagName(), is("button"));
    	assertThat(elements.get(1).getTagName(), is("span"));
    	assertThat(elements.get(2).getTagName(), is("span"));
    	assertThat(elements.get(3).getTagName(), is("button"));
    	assertThat(elements.get(4).getTagName(), is("button"));
    }
    
}