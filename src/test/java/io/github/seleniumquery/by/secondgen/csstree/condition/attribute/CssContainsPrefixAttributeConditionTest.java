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

package io.github.seleniumquery.by.secondgen.csstree.condition.attribute;

import io.github.seleniumquery.by.secondgen.finder.ElementFinder;
import io.github.seleniumquery.by.secondgen.finder.ElementFinderUtilsTest;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

public class CssContainsPrefixAttributeConditionTest {

    @Test
    public void toElementFinder() {
        // given
        CssContainsPrefixAttributeCondition containsPrefixAttributeCondition = new CssContainsPrefixAttributeCondition("hreflang", "en");
        ElementFinder previous = ElementFinderUtilsTest.UNIVERSAL_SELECTOR_FINDER;
        // when
        ElementFinder elementFinder = containsPrefixAttributeCondition.toElementFinder(previous);
        // then
        assertThat(elementFinder.toCssString(), is("[hreflang|='en']"));
        assertThat(elementFinder.canFetchThroughCssAlone(), is(true));

        String hreflang = "@*[translate(name(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = 'hreflang']";
        assertThat(elementFinder.getXPathExpression(), is(String.format(".//*[(%s = 'en' or starts-with(%s, 'en-'))]", hreflang, hreflang)));

        assertThat(elementFinder.getElementFilterList().getElementFilters(), empty());
    }

}
