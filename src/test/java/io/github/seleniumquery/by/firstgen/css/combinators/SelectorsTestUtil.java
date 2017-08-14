/*
 * Copyright (c) 2017 seleniumQuery authors
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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import io.github.seleniumquery.by.common.preparser.CssSelectorParser;
import io.github.seleniumquery.by.common.preparser.W3cCssSelectorWithMap;
import io.github.seleniumquery.by.firstgen.css.CssSelector;
import io.github.seleniumquery.by.firstgen.xpath.component.TagComponent;

public class SelectorsTestUtil {

    public static <T> void verifySelectorYieldsXPathExpression(CssSelector<T, TagComponent> cssSiblingSelector,
                                                               String selector,
                                                               String value) {
        W3cCssSelectorWithMap w3cCssSelectorWithMap = CssSelectorParser.parseSelector(selector).get(0);
        @SuppressWarnings("unchecked") T sacSelector = (T) w3cCssSelectorWithMap.getSelector();
        // when
        TagComponent xPathComponent = cssSiblingSelector.toXPath(w3cCssSelectorWithMap.getArgumentMap(), sacSelector);
        // then
        String xPath = xPathComponent.toXPath();
        assertThat(xPath, is(value));
    }

}
