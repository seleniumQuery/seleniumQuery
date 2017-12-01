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

package io.github.seleniumquery.by.firstgen.css.attributes;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

import io.github.seleniumquery.by.common.preparser.W3cCssSelectorWithMapParser;
import io.github.seleniumquery.by.common.preparser.w3cwithmap.W3cCssSelectorListWithMap;
import io.github.seleniumquery.by.firstgen.css.CssSelector;
import io.github.seleniumquery.by.firstgen.css.CssSelectorFactory;
import io.github.seleniumquery.by.firstgen.xpath.component.TagComponent;

public class IdAttributeCssSelectorTest {

    @Test
    public void testConditionToXPath() {
        W3cCssSelectorListWithMap w3cCssSelectorListWithMap = W3cCssSelectorWithMapParser.parseSelector("#idz");
        SelectorList selectorList = w3cCssSelectorListWithMap.getSelectorList();
        Selector selector = selectorList.item(0);

        CssSelector<Selector, TagComponent> cssSelector = CssSelectorFactory.parsedSelectorToCssSelector(selector);
        TagComponent tagComponent = cssSelector.toXPath(w3cCssSelectorListWithMap.getArgumentMap(), selector);

        assertThat(tagComponent.toXPath(), Matchers.is("(.//*[@id = 'idz'])"));
        assertThat(tagComponent.toXPathCondition(), Matchers.is("@id = 'idz'"));
    }

}
