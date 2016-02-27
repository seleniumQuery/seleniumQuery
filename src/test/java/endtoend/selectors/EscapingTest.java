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

package endtoend.selectors;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import java.util.List;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class EscapingTest {

    @ClassRule @Rule
    public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void d1() {
        List<WebElement> d1 = $("#d1").get();
        assertThat($(".foo").get(), is(d1));
        assertThat($(".f\\o\\o").get(), is(d1));
    }

    @Test
    public void d2() {
        List<WebElement> d2 = $("#d2").get();
        assertThat($(".f\\\\o\\\\o").get(), is(d2));
        assertThat($(".f\\\\\\o\\\\\\o").get(), is(d2));
    }

    @Test
    public void d3() {
        List<WebElement> d3 = $("#d3").get();
        assertThat($(".f\\\\\\\\o\\\\\\\\o").get(), is(d3));
        assertThat($(".f\\\\\\\\\\o\\\\\\\\\\o").get(), is(d3));
    }

    @Test
    public void d() {
        assertThat($(".f\\\\\\\\\\\\o\\\\\\\\\\\\o").size(), is(0));
    }

    @Test
    public void s1() {
        List<WebElement> s1 = $(".s1").get();
        assertThat($("#foo").get(), is(s1));
        assertThat($("#f\\o\\o").get(), is(s1));
    }

    @Test
    public void a2() {
        List<WebElement> s2 = $(".s2").get();
        assertThat($("#f\\\\o\\\\o").get(), is(s2));
        assertThat($("#f\\\\\\o\\\\\\o").get(), is(s2));
    }

    @Test
    public void s3() {
        List<WebElement> s3 = $(".s3").get();
        assertThat($("#f\\\\\\\\o\\\\\\\\o").get(), is(s3));
        assertThat($("#f\\\\\\\\\\o\\\\\\\\\\o").get(), is(s3));
    }

    @Test
    public void s() {
        assertThat($("#f\\\\\\\\\\\\o\\\\\\\\\\\\o").size(), is(0));
    }

    @Test
    public void d4() {
        List<WebElement> d4 = $("#d4").get();
        assertThat($(".\\\"").get(), is(d4));
    }

    @Test
    public void d5() {
        List<WebElement> d5 = $("#d5").get();
        assertThat($(".\\\"a\\\"b\\\"c\\\"").get(), is(d5));
    }

    @Test
    public void d6() {
        List<WebElement> d6 = $(".d6").get();
        assertThat($("#\\\"").get(), is(d6));
    }

    @Test
    public void d7() {
        List<WebElement> d7 = $(".d7").get();
        assertThat($("#\\\"a\\\"b\\\"c\\\"").get(), is(d7));
    }

    @Test
    public void s4() {
        List<WebElement> s4 = $("#s4").get();
        assertThat($(".\\\'").get(), is(s4));
    }

    @Test
    public void s5() {
        List<WebElement> s5 = $("#s5").get();
        assertThat($(".\\\'a\\\'b\\\'c\\\'").get(), is(s5));
    }

    @Test
    public void s6() {
        List<WebElement> s6 = $(".s6").get();
        assertThat($("#\\\'").get(), is(s6));
    }

    @Test
    public void s7() {
        List<WebElement> s7 = $(".s7").get();
        assertThat($("#\\\'a\\\'b\\\'c\\\'").get(), is(s7));
    }

}
