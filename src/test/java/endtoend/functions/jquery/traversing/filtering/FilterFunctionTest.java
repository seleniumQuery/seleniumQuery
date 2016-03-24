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

package endtoend.functions.jquery.traversing.filtering;

import com.google.common.base.Predicate;
import io.github.seleniumquery.SeleniumQueryObject;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static java.lang.String.format;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FilterFunctionTest {

    private static final String MY_DIV_ID = "#myDiv";

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    private SeleniumQueryObject $myDiv;
    private WebElement myDivWebElement;

    @Before
    public void setUp() {
        this.$myDiv = $(MY_DIV_ID);
        this.myDivWebElement = $myDiv.get(0);
        assertThat($myDiv.size(), is(1));
    }

    @Test
    public void filterSelector_into_emptySet() {
        assertThat($myDiv.filter("").size(), is(0));
        assertThat($myDiv.filter((String) null).size(), is(0));
        assertThat($myDiv.filter(".non-existant-class").size(), is(0));
    }

    @Test
    public void filterPredicate_into_emptySet() {
        assertThat($myDiv.filter((Predicate<WebElement>) null).size(), is(0));
        assertThat($myDiv.filter(new Predicate<WebElement>() {
            @Override
            public boolean apply(WebElement input) {
                assertThat(input, is(myDivWebElement));
                return false;
            }
        }).size(), is(0));
    }

    @Test
    public void filterSelector_regularUse() {
        assertThat($myDiv.filter(MY_DIV_ID).get(0), is(myDivWebElement));
        assertThat($("*").filter(MY_DIV_ID).get(0), is(myDivWebElement));
    }

    @Test
    public void filterPredicate_regularUse() {
        assertThat($myDiv.filter(new Predicate<WebElement>() {
            @Override
            public boolean apply(WebElement input) {
                return input == myDivWebElement;
            }
        }).get(0), is(myDivWebElement));
        assertThat($("*").filter(new Predicate<WebElement>() {
            @Override
            public boolean apply(WebElement input) {
                return $(input).is(MY_DIV_ID);
            }
        }).get(0), is(myDivWebElement));
    }

    @Test
    public void filterSelector_with_pseudo() {
        assertThat($("div").filter(":empty").get(0).getAttribute("id"), is("id-empty"));
    }

    @Test
    public void filterSelector_toString() {
        String selector = "crazy-tag#crazy-id.some-class:contains('withArgs')";
        SeleniumQueryObject seleniumQueryObject = $(selector).filter(selector);
        assertThat(seleniumQueryObject.toString(), is(format("$(\"%s\").filter(\"%s\")", selector, selector)));
    }

}