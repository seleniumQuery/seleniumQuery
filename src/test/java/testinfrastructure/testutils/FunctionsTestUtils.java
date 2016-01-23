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

package testinfrastructure.testutils;

import com.google.common.base.Function;
import io.github.seleniumquery.SeleniumQueryObject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static testinfrastructure.testdouble.SeleniumQueryObjectMother.createStubSeleniumQueryObject;

public class FunctionsTestUtils {

    public static void verifyFunctionReturnsSQOWithCorrectWebDriverAndFunctionsAndPrevious(Function<SeleniumQueryObject, SeleniumQueryObject> function) {
        // given
        SeleniumQueryObject targetSQO = createStubSeleniumQueryObject();
        // when
        SeleniumQueryObject resultSQO = function.apply(targetSQO);
        // then
        //noinspection ConstantConditions
        assertThat(targetSQO.end(), is(notNullValue()));
        assertThat(targetSQO.getSeleniumQueryFunctions(), is(notNullValue()));
        assertThat(targetSQO.getWebDriver(), is(notNullValue()));
        //noinspection ConstantConditions
        assertThat(resultSQO.end(), is(targetSQO));
        assertThat(resultSQO.getSeleniumQueryFunctions(), is(targetSQO.getSeleniumQueryFunctions()));
        assertThat(resultSQO.getWebDriver(), is(targetSQO.getWebDriver()));
    }

}
