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

package endtoend.functions.jquery.manipulation;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.by.DriverVersionUtils.isHtmlUnitDriverEmulatingIE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class HtmlFunctionTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void html_function__simple_element() {
    	assertThat($("span").html(), is("yo"));
    }
    
    @Test
    public void html_function__element_with_nested_element_with_no_line_breaks() {
    	if (isHtmlUnitDriverEmulatingIE($.driver().get())) {
    		assertThat($("div:eq(1)").html(), equalsIgnoringCaseWhiteSpaceAndQuotes("Spider <div>\"The Spidey\"</div> Man"));
    	} else {
    		assertThat($("div:eq(1)").html(), is("Spider <div>\"The Spidey\"</div> Man"));
    	}
    }
    
    @Test
    public void html_function__element_with_nested_element_with_line_breaks() {
    	if (isHtmlUnitDriverEmulatingIE($.driver().get())) {
	    	assertThat($("#nestedMultiLine").html(), equalsIgnoringCaseWhiteSpaceAndQuotes("ABC\n"+
	    			"    	<div>DEF</div>\n"+
	    			"    	<div>GHI</div>\n"+
	    			"    	JKY\n"+
	    			"    "));
    	} else {
	    	assertThat($("#nestedMultiLine").html(), is("ABC\n"+
			"    	<div>DEF</div>\n"+
			"    	<div>GHI</div>\n"+
			"    	JKY\n"+
			"    "));
    	}
    }
    
    @Test
    public void html_function__body() {
    	if (isHtmlUnitDriverEmulatingIE($.driver().get())) {
	        assertThat($("body").html(),
    		equalsIgnoringCaseWhiteSpaceAndQuotes("\n"+
			"    <div>Batman</div>\n"+
			"    <div>Spider <div>\"The Spidey\"</div> Man</div>\n"+
			"    <div>Hulk</div>\n"+
			"    <span>yo</span>\n"+
			"    <div id=\"nestedMultiLine\">ABC\n"+
			"    	<div>DEF</div>\n"+
			"    	<div>GHI</div>\n"+
			"    	JKY\n"+
			"    </div>\n"));
    	} else {
	        assertThat($("body").html(),
	        is("\n"+
			"    <div>Batman</div>\n"+
			"    <div>Spider <div>\"The Spidey\"</div> Man</div>\n"+
			"    <div>Hulk</div>\n"+
			"    <span>yo</span>\n"+
			"    <div id=\"nestedMultiLine\">ABC\n"+
			"    	<div>DEF</div>\n"+
			"    	<div>GHI</div>\n"+
			"    	JKY\n"+
			"    </div>\n"));
    	}
    }
    
    
    /*
     * #Cross-Driver
     * html() function has different output in <=IE8 and HtmlUnit(IE)
     * This is acceptable as this is the way jQuery does it.
     */
    /**
     * This matcher, before comparing the strings:
     * - Converts them to lowercase
     * - Remove all their spaces
     * - Remove all their double quotes (")
     */
	private Matcher<String> equalsIgnoringCaseWhiteSpaceAndQuotes(final String strToMatch) {
		return new BaseMatcher<String>() {
			@Override
			public boolean matches(final Object item) {
				return fix(item.toString()).equals(fix(strToMatch));
			}
			@Override
			public void describeTo(final Description description) {
				description.appendText("String should equal \"").appendText(fix(strToMatch)).appendText("\"");
			}
			@Override
			public void describeMismatch(final Object item, final Description description) {
				description.appendText("was \"").appendText(fix(item.toString())).appendText("\"");
			}
		    private String fix(String htmlToFix) {
		    	String lowerCase = htmlToFix.toLowerCase();
		    	String noSpaces = lowerCase.replaceAll("\\s+|\"", "");
		    	return noSpaces.trim();
		    }
		};
	}

}