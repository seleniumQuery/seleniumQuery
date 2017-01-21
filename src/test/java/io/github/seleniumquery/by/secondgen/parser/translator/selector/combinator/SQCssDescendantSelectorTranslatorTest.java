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

import io.github.seleniumquery.by.secondgen.csstree.condition.SQCssCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.SQCssClassAttributeCondition;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssConditionalSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssTagNameSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.combinator.CssDescendantSelector;
import io.github.seleniumquery.by.secondgen.parser.SQParseTreeBuilder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssDescendantSelectorTranslatorTest {

    @Test
    public void translate() {
        // given
        CssSelector cssSelector = SQParseTreeBuilder.parse("a b").firstSelector();
        assertThat(cssSelector, instanceOf(CssDescendantSelector.class));
        // when
        CssSelector ancestorSelector = ((CssDescendantSelector) cssSelector).getAncestorSelector();
        CssSelector descendantSelector = ((CssDescendantSelector) cssSelector).getDescendantSelector();
        // then
        assertThat(ancestorSelector, instanceOf(CssTagNameSelector.class));
        assertThat(((CssTagNameSelector) ancestorSelector).getTagName(), is("a"));

        assertThat(descendantSelector, instanceOf(CssTagNameSelector.class));
        assertThat(((CssTagNameSelector) descendantSelector).getTagName(), is("b"));
    }

    @Test
    public void translate_with_condition() {
        // given
        CssSelector cssSelector = SQParseTreeBuilder.parse("a b.condition").firstSelector();
        assertThat(cssSelector, instanceOf(CssDescendantSelector.class));
        // when
        CssSelector ancestorSelector = ((CssDescendantSelector) cssSelector).getAncestorSelector();
        CssSelector descendantSelector = ((CssDescendantSelector) cssSelector).getDescendantSelector();
        // then
        assertThat(ancestorSelector, instanceOf(CssTagNameSelector.class));
        assertThat(((CssTagNameSelector) ancestorSelector).getTagName(), is("a"));

        assertThat(descendantSelector, instanceOf(CssConditionalSelector.class));
        CssSelector sqCssSelector = ((CssConditionalSelector) descendantSelector).getCssSelector();
        SQCssCondition sqCssCondition = ((CssConditionalSelector) descendantSelector).getSqCssCondition();

        assertThat(sqCssSelector, instanceOf(CssTagNameSelector.class));
        assertThat(((CssTagNameSelector) sqCssSelector).getTagName(), is("b"));

        assertThat(sqCssCondition, instanceOf(SQCssClassAttributeCondition.class));
        assertThat(((SQCssClassAttributeCondition) sqCssCondition).getClassName(), is("condition"));
    }

    /**
     * "a b c" becomes:
     *
     *     /\
     *    /\ \
     *   a  b c
     */
    @Test
    public void translate_with_another_descendant() {
        // given
        CssSelector cssSelector = SQParseTreeBuilder.parse("a b c").firstSelector();
        assertThat(cssSelector, instanceOf(CssDescendantSelector.class));
        // when
        CssSelector ancestorSelector = ((CssDescendantSelector) cssSelector).getAncestorSelector();
        CssSelector descendantSelector = ((CssDescendantSelector) cssSelector).getDescendantSelector();
        // then
        assertThat(ancestorSelector, instanceOf(CssDescendantSelector.class));
        CssSelector ancestorAncestorSelector = ((CssDescendantSelector) ancestorSelector).getAncestorSelector();
        CssSelector ancestorDescendantSelector = ((CssDescendantSelector) ancestorSelector).getDescendantSelector();

        assertThat(ancestorAncestorSelector, instanceOf(CssTagNameSelector.class));
        assertThat(((CssTagNameSelector) ancestorAncestorSelector).getTagName(), is("a"));

        assertThat(ancestorAncestorSelector, instanceOf(CssTagNameSelector.class));
        assertThat(((CssTagNameSelector) ancestorDescendantSelector).getTagName(), is("b"));

        assertThat(descendantSelector, instanceOf(CssTagNameSelector.class));
        assertThat(((CssTagNameSelector) descendantSelector).getTagName(), is("c"));

    }

}
