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

package io.github.seleniumquery.by.firstgen.css.combinators;

import io.github.seleniumquery.by.common.preparser.CssParsedSelector;
import io.github.seleniumquery.by.common.preparser.CssSelectorParser;
import io.github.seleniumquery.by.firstgen.css.CssSelector;
import io.github.seleniumquery.by.firstgen.xpath.component.TagComponent;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SelectorsTestUtil {

    public static <T> void verifySelectorYieldsXPathExpression(CssSelector<T, TagComponent> cssSiblingSelector,
                                                               String selector,
                                                               String value) {
        CssParsedSelector cssParsedSelector = CssSelectorParser.parseSelector(selector).get(0);
        @SuppressWarnings("unchecked") T sacSelector = (T) cssParsedSelector.getSelector();
        // when
        TagComponent xPathComponent = cssSiblingSelector.toXPath(cssParsedSelector.getArgumentMap(), sacSelector);
        // then
        String xPath = xPathComponent.toXPath();
        assertThat(xPath, is(value));
    }

}