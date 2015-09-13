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

package io.github.seleniumquery.by.firstgen.css.attributes;

import io.github.seleniumquery.by.common.preparser.CssParsedSelectorList;
import io.github.seleniumquery.by.common.preparser.CssSelectorParser;
import io.github.seleniumquery.by.firstgen.css.CssSelector;
import io.github.seleniumquery.by.firstgen.css.CssSelectorFactory;
import io.github.seleniumquery.by.firstgen.xpath.component.TagComponent;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

import static org.junit.Assert.assertThat;

public class IdAttributeCssSelectorTest {

    @Test
    public void testConditionToXPath() {
        CssParsedSelectorList cssParsedSelectorList = CssSelectorParser.parseSelector("#idz");
        SelectorList selectorList = cssParsedSelectorList.getSelectorList();
        Selector selector = selectorList.item(0);

        CssSelector<Selector, TagComponent> cssSelector = CssSelectorFactory.parsedSelectorToCssSelector(selector);
        TagComponent tagComponent = cssSelector.toXPath(cssParsedSelectorList.getArgumentMap(), selector);

        assertThat(tagComponent.toXPath(), Matchers.is("(.//*[@id = 'idz'])"));
        assertThat(tagComponent.toXPathCondition(), Matchers.is("@id = 'idz'"));
    }

}