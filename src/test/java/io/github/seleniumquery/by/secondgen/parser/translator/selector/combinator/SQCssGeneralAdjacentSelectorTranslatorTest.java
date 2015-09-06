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

package io.github.seleniumquery.by2.parser.translator.selector.combinator;

import io.github.seleniumquery.by2.csstree.selector.SQCssSelector;
import io.github.seleniumquery.by2.csstree.selector.SQCssTagNameSelector;
import io.github.seleniumquery.by2.csstree.selector.combinator.SQCssGeneralAdjacentSelector;
import io.github.seleniumquery.by2.parser.SQParseTreeBuilder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssGeneralAdjacentSelectorTranslatorTest {

    @Test
    public void translate() {
        // given
        SQCssSelector cssSelector = SQParseTreeBuilder.parse("a ~ b").firstSelector();
        assertThat(cssSelector, instanceOf(SQCssGeneralAdjacentSelector.class));
        // when
        SQCssSelector ancestorSelector = ((SQCssGeneralAdjacentSelector) cssSelector).getLeftSideSelector();
        SQCssSelector descendantSelector = ((SQCssGeneralAdjacentSelector) cssSelector).getRightSideSelector();
        // then
        assertThat(ancestorSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) ancestorSelector).getTagName(), is("a"));

        assertThat(descendantSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) descendantSelector).getTagName(), is("b"));
    }

}