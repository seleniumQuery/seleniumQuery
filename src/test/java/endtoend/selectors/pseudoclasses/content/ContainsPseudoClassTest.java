/*
 * Copyright (c) 2016 seleniumQuery authors
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

package endtoend.selectors.pseudoclasses.content;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ContainsPseudoClassTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

	// http://jsbin.com/siwapeqe/1/edit
    @Test
    public void contains_pseudo_with_tag() {
    	assertThat($("div").size(), is(36));

    	assertThat($("div:contains(abc)").size(), is(12));
    	assertThat($("div:contains(\"abc\")").size(), is(12));
    	assertThat($("div:contains('abc')").size(), is(12));

    	assertThat($("div:contains(\"'abc'\")").size(), is(4));
    	assertThat($("div:contains('\"abc\"')").size(), is(4));

    	assertThat($("div:contains(\"a'bc\")").size(), is(4));
    	assertThat($("div:contains('a\"bc')").size(), is(4));

    	assertThat($("div:contains(\"ab)c\")").size(), is(4));
    	assertThat($("div:contains('ab)c')").size(), is(4));

    	assertThat($("div:contains(\"a'b)c\")").size(), is(4));
    	assertThat($("div:contains('a\"b)c')").size(), is(4));
    }
    
    @Test
    public void contains_pseudo_with_tag_escaping_double_quotes_inside_double_quotes_string() {
    	assertThat($("div:contains(\"a\\\"b)c\")").size(), is(4)); //2?? 4?? who knows!!!
    	assertThat($("div:contains(\"a\\\\\\\"b)c\")").size(), is(2)); //2?? 4?? who knows!!!
    }

    @Test
    public void contains_pseudo_with_tag_escaping_single_quote_inside_single_quote_string() {
    	// the selectors below should return TWO divs, not FOUR, it is matching WRONGLY
    	// it should match the <div>a\'b)c</div>, like jQuery does, but it doesnt
    	// as the content of contains reaches the ContainsPseudoClass as a'b)c, whereas
    	// jQuery considers it to be a\'b)c.
    	//
    	// To me, the CSS parser does the right thing, but jQuery disagrees.
    	// There's not much we can do here without changing the CSS Parser...
        //
        // its another chapter from the escaping-problem
        // leaving it as it is now
        // TODO escaping contains
    	assertThat($("div:contains('a\\'b)c')").size(), is(4));
    	assertThat($("div:contains('a\\\'b)c')").size(), is(4));
    }
    
    @Test
    public void containsPseudo() {
    	assertThat($("div:contains(abc)").size(), is(12));
    	assertThat($("body:contains(abc)").size(), is(1));
    	assertThat($("html:contains(abc)").size(), is(1));
    	assertThat($(":contains(abc)").size(), is(14));
    }

}