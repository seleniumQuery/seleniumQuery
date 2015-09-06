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

package io.github.seleniumquery.by2.csstree.condition.attribute;

import io.github.seleniumquery.by2.finder.ElementFinder;
import io.github.seleniumquery.by2.finder.ElementFinderUtilsTest;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

public class SQCssEqualsOrHasAttributeConditionTest {

    @Test
    public void toElementFinder__has_attribute() {
        // given
        SQCssEqualsOrHasAttributeCondition hasAttributeCondition = new SQCssEqualsOrHasAttributeCondition("attrib");
        ElementFinder previous = ElementFinderUtilsTest.UNIVERSAL_SELECTOR_FINDER;
        // when
        ElementFinder elementFinder = hasAttributeCondition.toElementFinder(previous);
        // then
        assertThat(elementFinder.getCssFinder().toString(), is("[attrib]"));
        assertThat(elementFinder.canFetchThroughCssAlone(), is(true));
        assertThat(elementFinder.getXPathExpression(), is(".//*[@*[translate(name(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = 'attrib']]"));
        assertThat(elementFinder.getElementFilterList().getElementFilters(), empty());
    }

    @Test
    public void toElementFinder__equals_attribute() {
        // given
        SQCssEqualsOrHasAttributeCondition hasAttributeCondition = new SQCssEqualsOrHasAttributeCondition("attrib", "valz");
        ElementFinder previous = ElementFinderUtilsTest.UNIVERSAL_SELECTOR_FINDER;
        // when
        ElementFinder elementFinder = hasAttributeCondition.toElementFinder(previous);
        // then
        assertThat(elementFinder.getCssFinder().toString(), is("[attrib=valz]"));
        assertThat(elementFinder.canFetchThroughCssAlone(), is(true));
        assertThat(elementFinder.getXPathExpression(), is(".//*[@*[translate(name(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = 'attrib']='valz']"));
        assertThat(elementFinder.getElementFilterList().getElementFilters(), empty());
    }

}