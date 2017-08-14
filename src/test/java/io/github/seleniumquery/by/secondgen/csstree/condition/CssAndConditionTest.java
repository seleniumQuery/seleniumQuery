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

package io.github.seleniumquery.by.secondgen.csstree.condition;

import static io.github.seleniumquery.by.secondgen.finder.ElementFinderUtilsTest.UNIVERSAL_SELECTOR_FINDER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.CssClassAttributeCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.CssIdAttributeCondition;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssSelector;
import io.github.seleniumquery.by.secondgen.finder.ElementFinder;
import io.github.seleniumquery.by.secondgen.parser.ParseTreeBuilder;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssClassAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssIdAttributeCondition;

public class CssAndConditionTest {

    @Test
    public void toElementFinder() {
        // given
        CssIdAttributeCondition idCondition = new CssIdAttributeCondition(new AstCssIdAttributeCondition("my-id"));
        CssClassAttributeCondition classCondition = new CssClassAttributeCondition(new AstCssClassAttributeCondition("class-name"));
        CssAndCondition andCondition = new CssAndCondition(new AstCssAndCondition(idCondition, classCondition));
        // when
        ElementFinder elementFinder = andCondition.toElementFinder(UNIVERSAL_SELECTOR_FINDER);
        // then
        assertThat(elementFinder.toCssString(), is("#my-id.class-name"));
        assertThat(elementFinder.canFetchThroughCssAlone(), is(true));
        assertThat(elementFinder.getXPathExpression(), is(".//*[@id = 'my-id' and contains(concat(' ', normalize-space(@class), ' '), ' class-name ')]"));
        assertThat(elementFinder.getElementFilterList().getElementFilters(), empty());
    }

    @Test
    public void toElementFinder__with_two_ands() {
        // given
        String tagClassClassSelector = "span.a.b";
        CssSelector tagAndClassAndClassCondition = ParseTreeBuilder.parse(tagClassClassSelector).firstSelector();
        // when
        ElementFinder elementFinder = tagAndClassAndClassCondition.toElementFinder(UNIVERSAL_SELECTOR_FINDER);
        // then
        assertThat(elementFinder.toCssString(), is(tagClassClassSelector));
        assertThat(elementFinder.canFetchThroughCssAlone(), is(true));
        assertThat(elementFinder.getXPathExpression(), is(".//*[self::span and contains(concat(' ', normalize-space(@class), ' '), ' a ') and contains(concat(' ', normalize-space(@class), ' '), ' b ')]"));
        assertThat(elementFinder.getElementFilterList().getElementFilters(), empty());
    }

}
