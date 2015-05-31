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

package integration.io.github.seleniumquery.browser;

import io.github.seleniumquery.browser.BrowserFunctions;
import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BrowserFunctionsTest {

    BrowserFunctions browserFunctions = new BrowserFunctions();

    @Test
    @SuppressWarnings("deprecation")
    public void pause__should_pause_for_the_given_time_in_millis() {
        // given
        long startingTime = System.currentTimeMillis();
        // when
        browserFunctions.pause(500);
        // then
        long timeSpent = System.currentTimeMillis() - startingTime;
        assertThat(((double) timeSpent), is(closeTo(500d, 100d)));
    }

}