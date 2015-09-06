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
import io.github.seleniumquery.by.secondgen.csstree.selector.SQCssConditionalSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.SQCssSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.SQCssTagNameSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.combinator.SQCssDescendantSelector;
import io.github.seleniumquery.by.secondgen.parser.SQParseTreeBuilder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssDescendantSelectorTranslatorTest {

    @Test
    public void translate() {
        // given
        SQCssSelector cssSelector = SQParseTreeBuilder.parse("a b").firstSelector();
        assertThat(cssSelector, instanceOf(SQCssDescendantSelector.class));
        // when
        SQCssSelector ancestorSelector = ((SQCssDescendantSelector) cssSelector).getAncestorSelector();
        SQCssSelector descendantSelector = ((SQCssDescendantSelector) cssSelector).getDescendantSelector();
        // then
        assertThat(ancestorSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) ancestorSelector).getTagName(), is("a"));

        assertThat(descendantSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) descendantSelector).getTagName(), is("b"));
    }

    @Test
    public void translate_with_condition() {
        // given
        SQCssSelector cssSelector = SQParseTreeBuilder.parse("a b.condition").firstSelector();
        assertThat(cssSelector, instanceOf(SQCssDescendantSelector.class));
        // when
        SQCssSelector ancestorSelector = ((SQCssDescendantSelector) cssSelector).getAncestorSelector();
        SQCssSelector descendantSelector = ((SQCssDescendantSelector) cssSelector).getDescendantSelector();
        // then
        assertThat(ancestorSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) ancestorSelector).getTagName(), is("a"));

        assertThat(descendantSelector, instanceOf(SQCssConditionalSelector.class));
        SQCssSelector sqCssSelector = ((SQCssConditionalSelector) descendantSelector).getSqCssSelector();
        SQCssCondition sqCssCondition = ((SQCssConditionalSelector) descendantSelector).getSqCssCondition();

        assertThat(sqCssSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) sqCssSelector).getTagName(), is("b"));

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
        SQCssSelector cssSelector = SQParseTreeBuilder.parse("a b c").firstSelector();
        assertThat(cssSelector, instanceOf(SQCssDescendantSelector.class));
        // when
        SQCssSelector ancestorSelector = ((SQCssDescendantSelector) cssSelector).getAncestorSelector();
        SQCssSelector descendantSelector = ((SQCssDescendantSelector) cssSelector).getDescendantSelector();
        // then
        assertThat(ancestorSelector, instanceOf(SQCssDescendantSelector.class));
        SQCssSelector ancestorAncestorSelector = ((SQCssDescendantSelector) ancestorSelector).getAncestorSelector();
        SQCssSelector ancestorDescendantSelector = ((SQCssDescendantSelector) ancestorSelector).getDescendantSelector();

        assertThat(ancestorAncestorSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) ancestorAncestorSelector).getTagName(), is("a"));

        assertThat(ancestorAncestorSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) ancestorDescendantSelector).getTagName(), is("b"));

        assertThat(descendantSelector, instanceOf(SQCssTagNameSelector.class));
        assertThat(((SQCssTagNameSelector) descendantSelector).getTagName(), is("c"));

    }

}