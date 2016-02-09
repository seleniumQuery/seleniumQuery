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

package io.github.seleniumquery.functions.jquery.traversing;

import io.github.seleniumquery.SeleniumQueryObject;
import org.junit.Test;

import static testinfrastructure.testdouble.io.github.seleniumquery.SeleniumQueryObjectMother.createStubSeleniumQueryObjectWithAtLeastOneElement;

public class SqEachFunctionTest {

    private static final SeleniumQueryObject.EachFunction NULL_FUNCTION = null;

    private SqEachFunction sqEachFunction = new SqEachFunction();

    @Test(expected = NullPointerException.class)
    public void null_function__should_throw_exception() {
        // given
        SeleniumQueryObject targetSQO = createStubSeleniumQueryObjectWithAtLeastOneElement();
        // when
        sqEachFunction.each(targetSQO, NULL_FUNCTION);
        // then
        // should throw exception
    }

}