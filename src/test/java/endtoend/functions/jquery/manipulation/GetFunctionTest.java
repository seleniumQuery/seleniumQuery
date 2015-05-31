/*
Copyright (c) 2015 seleniumQuery authors

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package endtoend.functions.jquery.manipulation;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GetFunctionTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void get_function__with_index_arg() throws Exception {
        assertThat($("div").size(), is(3));
        assertThat($("div").get(0).getText(), is("Batman"));
        assertThat($("div").get(1).getText(), is("Spider Man"));
        assertThat($("div").get(2).getText(), is("Hulk"));
    }

    @Test
    public void get_function__without_arguments() throws Exception {
    	assertThat($("div").size(), is(3));

    	List<WebElement> divs = $("div").get();

    	assertThat(divs.size(), is(3));

        assertThat(divs.get(0).getText(), is("Batman"));
        assertThat(divs.get(1).getText(), is("Spider Man"));
        assertThat(divs.get(2).getText(), is("Hulk"));
    }

    @Test(expected = java.lang.UnsupportedOperationException.class)
    public void get_function__without_arguments__should_return_an_immutable_list() throws Exception {
        // given
        List<WebElement> elements = $("div").get();
        WebElement otherWebElement = $("span").get(0);
        // when
        elements.add(otherWebElement);
        // then
        // should throw exception as the returned list should be immutable
    }

}