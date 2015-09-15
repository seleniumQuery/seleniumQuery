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

package endtoend.functions.as;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static testinfrastructure.EndToEndTestUtils.classNameToTestFileUrl;

public class AsSelectTest {

    @Before
    public void setUp() throws Exception {
        $.driver().useHtmlUnit();
        $.url(classNameToTestFileUrl(AsSelectTest.class));
        assertThat($("select").val(), is("valueA")); // negative test
        assertThat($("input").val(), is("inputValueShouldNotBeAffected")); // negative test
    }

    @After
    public void tearDown() throws Exception {
        assertThat($("input").val(), is("inputValueShouldNotBeAffected")); // negative test
        $.quit();
    }

    @Test
    public void selectByVisibleText() {
        // given
        // when
        $("select, input").as().select().selectByVisibleText("VisibleTextB");
        // then
        assertThat($("select").val(), is("valueB"));
    }

    @Test
    public void testSelectByValue() throws Exception {
        // given
        // when
        $("select, input").as().select().selectByValue("valueC");
        // then
        assertThat($("select").val(), is("valueC"));
    }

}