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

package io.github.seleniumquery.by.secondgen.parser.translator.selector.combinator;

import io.github.seleniumquery.by.secondgen.csstree.selector.CssSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssTagNameSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.combinator.CssDirectDescendantSelector;
import io.github.seleniumquery.by.secondgen.parser.SQParseTreeBuilder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssDirectDescendantSelectorTranslatorTest {

    @Test
    public void translate() {
        // given
        CssSelector cssSelector = SQParseTreeBuilder.parse("a > b").firstSelector();
        assertThat(cssSelector, instanceOf(CssDirectDescendantSelector.class));
        // when
        CssSelector ancestorSelector = ((CssDirectDescendantSelector) cssSelector).getAncestorSelector();
        CssSelector descendantSelector = ((CssDirectDescendantSelector) cssSelector).getDescendantSelector();
        // then
        assertThat(ancestorSelector, instanceOf(CssTagNameSelector.class));
        assertThat(((CssTagNameSelector) ancestorSelector).getTagName(), is("a"));

        assertThat(descendantSelector, instanceOf(CssTagNameSelector.class));
        assertThat(((CssTagNameSelector) descendantSelector).getTagName(), is("b"));
    }

}
