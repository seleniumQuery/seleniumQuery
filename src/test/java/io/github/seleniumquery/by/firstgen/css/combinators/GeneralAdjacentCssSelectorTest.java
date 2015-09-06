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

import io.github.seleniumquery.by.firstgen.preparser.CSSParsedSelectorList;
import io.github.seleniumquery.by.firstgen.preparser.CSSSelectorParser;
import io.github.seleniumquery.by.firstgen.xpath.component.TagComponent;
import org.junit.Test;
import org.w3c.css.sac.SiblingSelector;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GeneralAdjacentCssSelectorTest {

    GeneralAdjacentCssSelector generalAdjacentCssSelector = new GeneralAdjacentCssSelector();

    @Test
    public void testToXPath() throws Exception {
        // given
        CSSParsedSelectorList CSSParsedSelectorList = CSSSelectorParser.parseSelector("a ~ b");
        SiblingSelector siblingSelector = (SiblingSelector) CSSParsedSelectorList.getSelectorList().item(0);
        // when
        TagComponent xPathComponent = generalAdjacentCssSelector.toXPath(CSSParsedSelectorList.getArgumentMap(), siblingSelector);
        // then
        String xPath = xPathComponent.toXPath();
        assertThat(xPath, is("(.//*[self::a]/following-sibling::b)"));
    }

}