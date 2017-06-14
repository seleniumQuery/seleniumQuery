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

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.functions.as.SeleniumQueryPlugin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static testinfrastructure.EndToEndTestUtils.classNameToTestFileUrl;

public class SeleniumQueryPluginTest {

    @Before
    public void setUp() throws Exception {
        $.driver().useHtmlUnit();
        $.url(classNameToTestFileUrl(SeleniumQueryPluginTest.class));
        assertThat($("div").size(), is(7)); // negative test
    }

    @After
    public void tearDown() throws Exception {
        $.quit();
    }

    @Test
    public void as__should_accept_a_seleniumQueryPlugin_argument() throws Exception {
        // given
        // when
        SeleniumQueryObject sq = $("div");
        int theSize = sq.as(Sizer.SIZER).gimmeTheSize();
        // then
        assertThat(theSize, is(7));
    }

}

class Sizer {
    public static final SeleniumQueryPlugin<Sizer> SIZER = new SeleniumQueryPlugin<>() {
        @Override
        public Sizer as(SeleniumQueryObject seleniumQueryObject) {
            return new Sizer<>(seleniumQueryObject);
        }
    };

    private SeleniumQueryObject seleniumQueryObject;
    public Sizer(SeleniumQueryObject seleniumQueryObject) {
        this.seleniumQueryObject = seleniumQueryObject;
    }

    public int gimmeTheSize() {
        return this.seleniumQueryObject.get().size();
    }

}