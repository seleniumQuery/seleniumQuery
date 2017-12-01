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

package io.github.seleniumquery.by.secondgen.csstree.selector;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;
import static testinfrastructure.testdouble.org.openqa.selenium.WebDriverDummy.createWebDriverDummy;

import org.junit.Test;

import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.CssClassAttributeCondition;
import io.github.seleniumquery.by.secondgen.csstree.selector.combinator.CssDescendantSelector;
import io.github.seleniumquery.by.secondgen.finder.ElementFinder;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssClassAttributeCondition;

public class CssConditionalSelectorTest {

    @Test
    public void toElementFinder() {
        // given
        CssTagNameSelector tagNameSelector = new CssTagNameSelector("tagg");
        CssClassAttributeCondition classAttributeCondition = new CssClassAttributeCondition(new AstCssClassAttributeCondition("clz"));
        // tagg.clz
        CssConditionalSelector conditionalSelector = new CssConditionalSelector(tagNameSelector, classAttributeCondition);
        // when
        ElementFinder elementFinder = conditionalSelector.toElementFinder(createWebDriverDummy());
        // then
        assertThat(elementFinder.toCssString(), is("tagg.clz"));
        assertThat(elementFinder.canFetchThroughCssAlone(), is(true));
        assertThat(elementFinder.getXPathExpression(), is(".//*[self::tagg and contains(concat(' ', normalize-space(@class), ' '), ' clz ')]"));
        assertThat(elementFinder.getElementFilterList().getElementFilters(), empty());
    }

    @Test
    public void toElementFinder__with_ElementFinder_arg() {
        // given
        CssTagNameSelector aTagSelector = new CssTagNameSelector("a");
        CssTagNameSelector bTagSelector = new CssTagNameSelector("b");
        CssClassAttributeCondition classAttributeCondition = new CssClassAttributeCondition(new AstCssClassAttributeCondition("condition"));
        CssConditionalSelector conditionalSelector = new CssConditionalSelector(bTagSelector, classAttributeCondition);
        // a b.condition
        CssDescendantSelector descendantSelector = new CssDescendantSelector(aTagSelector, conditionalSelector);
        // when
        ElementFinder elementFinder = descendantSelector.toElementFinder(createWebDriverDummy());
        // then
        assertThat(elementFinder.toCssString(), is("a b.condition"));
        assertThat(elementFinder.canFetchThroughCssAlone(), is(true));
        assertThat(elementFinder.getXPathExpression(), is(".//*[self::a]//*[self::b and contains(concat(' ', normalize-space(@class), ' '), ' condition ')]"));
        assertThat(elementFinder.getElementFilterList().getElementFilters(), empty());
    }

}
