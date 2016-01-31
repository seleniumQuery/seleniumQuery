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

package io.github.seleniumquery.by.firstgen.css.combinators;

import org.junit.Test;

public class DirectAdjacentCssSelectorTest {

    @Test
    public void testToXPath() {
        // given
        SelectorsTestUtil.verifySelectorYieldsXPathExpression(new DirectAdjacentCssSelector(), "a + b", "(.//*[self::a]/following-sibling::*[self::b and position() = 1])");
    }

}