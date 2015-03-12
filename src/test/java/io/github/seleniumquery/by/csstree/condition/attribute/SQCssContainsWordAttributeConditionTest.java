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

package io.github.seleniumquery.by.csstree.condition.attribute;

import io.github.seleniumquery.by.locator.SQLocator;
import io.github.seleniumquery.by.locator.SQLocatorUtilsTest;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

public class SQCssContainsWordAttributeConditionTest {

    @Test
    public void toSQLocator() {
        // given
        SQCssContainsWordAttributeCondition containsWordAttributeCondition = new SQCssContainsWordAttributeCondition("values", "10");
        SQLocator previous = SQLocatorUtilsTest.TAG_ASTERISK;
        // when
        SQLocator locator = containsWordAttributeCondition.toSQLocator(previous);
        // then
        assertThat(locator.getCssSelector(), is("[values~='10']"));
        assertThat(locator.canFetchThroughCssAlone(), is(true));

        String attrName = "@*[translate(name(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = 'values']";
        assertThat(locator.getXPathExpression(), is(".//*[contains(concat(' ', normalize-space("+attrName+"), ' '), ' 10 ')]"));

        assertThat(locator.getElementFilterList().getElementFilters(), empty());
    }

}