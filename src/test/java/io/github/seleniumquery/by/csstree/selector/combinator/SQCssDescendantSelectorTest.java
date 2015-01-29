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

package io.github.seleniumquery.by.csstree.selector.combinator;

import io.github.seleniumquery.by.csstree.selector.SQCssTagNameSelector;
import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.locator.SQLocator;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class SQCssDescendantSelectorTest {

    @Test
    public void toSQLocator() {
        // given
        SQCssTagNameSelector aTagSelector = new SQCssTagNameSelector("a");
        SQCssTagNameSelector bTagSelector = new SQCssTagNameSelector("b");
        SQCssDescendantSelector descendantSelector = new SQCssDescendantSelector(aTagSelector, bTagSelector);
        // when
        SQLocator locator = descendantSelector.toSQLocator(mock(WebDriver.class));
        // then
        assertThat(locator.getCssSelector(), is("a b"));
        assertThat(locator.getXPathExpression(), is(".//*[self::a]//*[self::b]"));
        assertThat(locator.canPureCss(), is(true));
        assertThat(locator.canPureXPath(), is(true));
        assertThat(locator.getElementFilterList().getElementFilters(), contains(ElementFilter.FILTER_NOTHING));
    }

    @Test
    public void toSQLocator_multiple() {
        // given
        SQCssTagNameSelector firstSelector = new SQCssTagNameSelector("a");
        SQCssTagNameSelector secondSelector = new SQCssTagNameSelector("b");
        SQCssDescendantSelector firstAndSecondSelectors = new SQCssDescendantSelector(firstSelector, secondSelector);
        SQCssTagNameSelector thirdSelector = new SQCssTagNameSelector("c");
        SQCssDescendantSelector descendantSelector = new SQCssDescendantSelector(firstAndSecondSelectors, thirdSelector);
        // when
        SQLocator locator = descendantSelector.toSQLocator(mock(WebDriver.class));
        // then
        assertThat(locator.getCssSelector(), is("a b c"));
        assertThat(locator.getXPathExpression(), is(".//*[self::a]//*[self::b]//*[self::c]"));
        assertThat(locator.canPureCss(), is(true));
        assertThat(locator.canPureXPath(), is(true));
        assertThat(locator.getElementFilterList().getElementFilters(), contains(ElementFilter.FILTER_NOTHING));
    }

}