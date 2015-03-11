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

package io.github.seleniumquery.by.locator;

import org.junit.Test;

import static io.github.seleniumquery.by.locator.SQLocatorCss.fromTag;
import static io.github.seleniumquery.by.locator.SQLocatorCss.universalSelector;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQLocatorCssTest {

    @Test(expected = IllegalArgumentException.class)
    public void cssMerge__should_throw_exception_if_both_css_havent_universalSelector_as_tag() {
        fromTag("div").mergeUsingCurrentNativeness(fromTag("span"));
    }

    @Test
    public void cssMerge__should_let_it_be_if_both_tags_are_not_universalSelector__but_are_equal() {
        SQLocatorCss mergedCss = fromTag("div").mergeUsingCurrentNativeness(fromTag("div"));
        assertThat(mergedCss.toString(), is("div"));
    }

    @Test
    public void cssMerge__should_merge() {
        SQLocatorCss mergedCss = fromTag("div").mergeUsingCurrentNativeness(new SQLocatorCss(".clz", SQLocatorCss.CanFetchAllElementsOfTheQueryByItself.YES));
        assertThat(mergedCss.toString(), is("div.clz"));
    }

    @Test
    public void cssMerge__should_remove_asterisk() {
        SQLocatorCss mergedCss = universalSelector().mergeUsingCurrentNativeness(new SQLocatorCss(".clz", SQLocatorCss.CanFetchAllElementsOfTheQueryByItself.YES));
        assertThat(mergedCss.toString(), is(".clz"));
    }

}