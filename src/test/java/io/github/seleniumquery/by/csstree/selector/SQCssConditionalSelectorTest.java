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

package io.github.seleniumquery.by.csstree.selector;

import io.github.seleniumquery.by.csstree.condition.attribute.SQCssClassAttributeCondition;
import io.github.seleniumquery.by.csstree.selector.combinator.SQCssDescendantSelector;
import io.github.seleniumquery.by.locator.SQLocator;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class SQCssConditionalSelectorTest {

    @Test
    public void toSQLocator() {
        // given
        SQCssTagNameSelector tagNameSelector = new SQCssTagNameSelector("tagg");
        SQCssClassAttributeCondition classAttributeCondition = new SQCssClassAttributeCondition("clz");
        // tagg.clz
        SQCssConditionalSelector conditionalSelector = new SQCssConditionalSelector(tagNameSelector, classAttributeCondition);
        // when
        SQLocator locator = conditionalSelector.toSQLocator(mock(WebDriver.class));
        // then
        assertThat(locator.getCSSLocator().toString(), is("tagg.clz"));
        assertThat(locator.canFetchThroughCssAlone(), is(true));
        assertThat(locator.getXPathExpression(), is(".//*[self::tagg and contains(concat(' ', normalize-space(@class), ' '), ' clz ')]"));
        assertThat(locator.getElementFilterList().getElementFilters(), empty());
    }

    @Test
    public void toSQLocator__with_SQLocator_arg() {
        // given
        SQCssTagNameSelector aTagSelector = new SQCssTagNameSelector("a");
        SQCssTagNameSelector bTagSelector = new SQCssTagNameSelector("b");
        SQCssClassAttributeCondition classAttributeCondition = new SQCssClassAttributeCondition("condition");
        SQCssConditionalSelector conditionalSelector = new SQCssConditionalSelector(bTagSelector, classAttributeCondition);
        // a b.condition
        SQCssDescendantSelector descendantSelector = new SQCssDescendantSelector(aTagSelector, conditionalSelector);
        // when
        SQLocator locator = descendantSelector.toSQLocator(mock(WebDriver.class));
        // then
        assertThat(locator.getCSSLocator().toString(), is("a b.condition"));
        assertThat(locator.canFetchThroughCssAlone(), is(true));
        assertThat(locator.getXPathExpression(), is(".//*[self::a]//*[self::b and contains(concat(' ', normalize-space(@class), ' '), ' condition ')]"));
        assertThat(locator.getElementFilterList().getElementFilters(), empty());
    }

}