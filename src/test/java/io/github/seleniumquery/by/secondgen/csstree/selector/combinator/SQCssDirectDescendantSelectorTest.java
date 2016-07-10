/*
 * Copyright (c) 2016 seleniumQuery authors
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

package io.github.seleniumquery.by.secondgen.csstree.selector.combinator;

import io.github.seleniumquery.by.secondgen.csstree.selector.SQCssTagNameSelector;
import io.github.seleniumquery.by.secondgen.finder.ElementFinder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;
import static testinfrastructure.testdouble.org.openqa.selenium.WebDriverDummy.createWebDriverDummy;

public class SQCssDirectDescendantSelectorTest {

    @Test
    public void toElementFinder() {
        // given
        SQCssTagNameSelector aTagSelector = new SQCssTagNameSelector("a");
        SQCssTagNameSelector bTagSelector = new SQCssTagNameSelector("b");
        SQCssDirectDescendantSelector directDescendantSelector = new SQCssDirectDescendantSelector(aTagSelector, bTagSelector);
        // when
        ElementFinder elementFinder = directDescendantSelector.toElementFinder(createWebDriverDummy());
        // then
        assertThat(elementFinder.toCssString(), is("a>b"));
        assertThat(elementFinder.canFetchThroughCssAlone(), is(true));
        assertThat(elementFinder.getXPathExpression(), is(".//*[self::a]/*[self::b]"));
        assertThat(elementFinder.getElementFilterList().getElementFilters(), empty());
    }

}
