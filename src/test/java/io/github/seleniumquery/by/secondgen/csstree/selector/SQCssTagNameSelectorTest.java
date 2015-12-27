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

package io.github.seleniumquery.by.secondgen.csstree.selector;

import io.github.seleniumquery.by.secondgen.finder.ElementFinder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;
import static testinfrastructure.testdouble.Dummies.createDummyWebDriver;

public class SQCssTagNameSelectorTest {

    @Test
    public void toElementFinder() {
        // given
        SQCssTagNameSelector tagNameSelector = new SQCssTagNameSelector("myTag");
        // when
        ElementFinder elementFinder = tagNameSelector.toElementFinder(createDummyWebDriver());
        // then
        assertThat(elementFinder.getCssFinder().toString(), is("myTag"));
        assertThat(elementFinder.canFetchThroughCssAlone(), is(true));
        assertThat(elementFinder.getXPathExpression(), is(".//*[self::myTag]"));
        assertThat(elementFinder.getElementFilterList().getElementFilters(), empty());
    }

    @Test
    public void toElementFinder__should_return_true_for_XPath_if_tag_is_universalSelector() {
        // given
        SQCssTagNameSelector tagNameSelector = new SQCssTagNameSelector("*");
        // when
        ElementFinder elementFinder = tagNameSelector.toElementFinder(createDummyWebDriver());
        // then
        assertThat(elementFinder.getXPathExpression(), is(".//*[true()]"));
    }

    @Test
    public void toElementFinder__should_return_ONLY_true_as_raw_XPathexpression() {
        // given
        SQCssTagNameSelector tagNameSelector = new SQCssTagNameSelector("*");
        // when
        ElementFinder elementFinder = tagNameSelector.toElementFinder(createDummyWebDriver());
        // then
        assertThat(elementFinder.getXPathAndFilterFinder().getRawXPathExpression(), is("true()"));
    }

}