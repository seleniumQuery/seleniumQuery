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

package io.github.seleniumquery.by.firstgen.css.conditionals;

import io.github.seleniumquery.by.common.preparser.ArgumentMap;
import io.github.seleniumquery.by.common.preparser.CssParsedSelectorList;
import io.github.seleniumquery.by.common.preparser.CssSelectorParser;
import io.github.seleniumquery.by.firstgen.xpath.XPathComponentCompilerService;
import io.github.seleniumquery.by.firstgen.xpath.component.ConditionComponent;
import io.github.seleniumquery.by.firstgen.xpath.component.TagComponent;
import org.junit.Test;
import org.w3c.css.sac.CombinatorCondition;
import org.w3c.css.sac.ConditionalSelector;
import org.w3c.css.sac.SelectorList;
import org.w3c.css.sac.SimpleSelector;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AndConditionalCssSelectorTest {

    AndConditionalCssSelector andConditionalCssSelector = new AndConditionalCssSelector(new ConditionalCssSelector());

    @Test
    public void testConditionToXPath() {
        CssParsedSelectorList cssParsedSelectorList = CssSelectorParser.parseSelector("span.a.b");
        SelectorList selectorList = cssParsedSelectorList.getSelectorList();
        ConditionalSelector selector = (ConditionalSelector) selectorList.item(0);

//        XPathComponent cs = conditionalCssSelector.toXPath(cssParsedSelectorList.getArgumentMap(), selector);
        ArgumentMap argumentMap = cssParsedSelectorList.getArgumentMap();
        SimpleSelector simpleSelector = selector.getSimpleSelector();
        TagComponent spanTagComponent = XPathComponentCompilerService.compileSelector(argumentMap, simpleSelector);

//        XPathComponent compiledCondition = conditionalCssSelector.conditionToXPath(argumentMap, selector.getSimpleSelector(), selector.getCondition());
        CombinatorCondition combinatorCondition = (CombinatorCondition) selector.getCondition();
        ConditionComponent compiledCondition = andConditionalCssSelector.conditionToXPath(argumentMap, simpleSelector, combinatorCondition);

        TagComponent cs = spanTagComponent.cloneAndCombineTo(compiledCondition);
        assertThat(cs.toXPath(), is("(.//*[self::span and contains(concat(' ', normalize-space(@class), ' '), ' a ') and contains(concat(' ', normalize-space(@class), ' '), ' b ')])"));
        assertThat(cs.toXPathCondition(), is("local-name() = 'span' and contains(concat(' ', normalize-space(@class), ' '), ' a ') and contains(concat(' ', normalize-space(@class), ' '), ' b ')"));
    }

}