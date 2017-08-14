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

package io.github.seleniumquery.by.secondgen.csstree.condition.attribute;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import io.github.seleniumquery.by.secondgen.finder.ElementFinder;
import io.github.seleniumquery.by.secondgen.finder.ElementFinderUtilsTest;

public class CssEqualsOrHasAttributeConditionTest {

    @Test
    public void toElementFinder__has_attribute() {
        // given
        CssEqualsOrHasAttributeCondition hasAttributeCondition = new CssEqualsOrHasAttributeCondition(new AstCssEqualsOrHasAttributeCondition("attrib"));
        ElementFinder previous = ElementFinderUtilsTest.UNIVERSAL_SELECTOR_FINDER;
        // when
        ElementFinder elementFinder = hasAttributeCondition.toElementFinder(previous);
        // then
        assertThat(elementFinder.toCssString(), is("[attrib]"));
        assertThat(elementFinder.canFetchThroughCssAlone(), is(true));
        assertThat(elementFinder.getXPathExpression(), is(".//*[@*[translate(name(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = 'attrib']]"));
        assertThat(elementFinder.getElementFilterList().getElementFilters(), empty());
    }

    @Test
    public void toElementFinder__equals_attribute() {
        // given
        CssEqualsOrHasAttributeCondition hasAttributeCondition = new CssEqualsOrHasAttributeCondition(new AstCssEqualsOrHasAttributeCondition("attrib", "valz"));
        ElementFinder previous = ElementFinderUtilsTest.UNIVERSAL_SELECTOR_FINDER;
        // when
        ElementFinder elementFinder = hasAttributeCondition.toElementFinder(previous);
        // then
        assertThat(elementFinder.toCssString(), is("[attrib='valz']"));
        assertThat(elementFinder.canFetchThroughCssAlone(), is(true));
        assertThat(elementFinder.getXPathExpression(), is(".//*[@*[translate(name(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = 'attrib']='valz']"));
        assertThat(elementFinder.getElementFilterList().getElementFilters(), empty());
    }

}
