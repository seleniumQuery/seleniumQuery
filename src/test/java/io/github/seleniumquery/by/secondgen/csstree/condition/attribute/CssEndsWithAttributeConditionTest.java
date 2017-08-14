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

public class CssEndsWithAttributeConditionTest {

    @Test
    public void toElementFinder() {
        // given
        AstCssEndsWithAttributeCondition endsWithAttributeCondition = new CssEndsWithAttributeCondition("attribute", "stringToEnd");
        ElementFinder previous = ElementFinderUtilsTest.UNIVERSAL_SELECTOR_FINDER;
        // when
        ElementFinder elementFinder = endsWithAttributeCondition.toElementFinder(previous);
        // then
        assertThat(elementFinder.toCssString(), is("[attribute$='stringToEnd']"));
        assertThat(elementFinder.canFetchThroughCssAlone(), is(true));

        String attrName = "@*[translate(name(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = 'attribute']";
        assertThat(elementFinder.getXPathExpression(), is(".//*[substring("+attrName+", string-length("+attrName+")-10) = 'stringToEnd']"));

        assertThat(elementFinder.getElementFilterList().getElementFilters(), empty());
    }

}
