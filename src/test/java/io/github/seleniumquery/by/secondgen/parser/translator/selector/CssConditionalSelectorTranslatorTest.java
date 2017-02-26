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

package io.github.seleniumquery.by.secondgen.parser.translator.selector;

import io.github.seleniumquery.by.secondgen.csstree.condition.CssAndCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.CssCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.CssClassAttributeCondition;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssConditionalSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssTagNameSelector;
import io.github.seleniumquery.by.secondgen.parser.ParseTreeBuilder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CssConditionalSelectorTranslatorTest {

    @Test
    public void translate__simple_condition() {
        // given
        String simpleConditionSelector = "a.condition";
        // when
        CssSelector cssSelector = ParseTreeBuilder.parse(simpleConditionSelector).firstSelector();
        // then
        assertThat(cssSelector, instanceOf(CssConditionalSelector.class));
        CssSelector sqCssSelector = ((CssConditionalSelector) cssSelector).getCssSelector();
        CssCondition cssCondition = ((CssConditionalSelector) cssSelector).getCssCondition();

        assertThat(sqCssSelector, instanceOf(CssTagNameSelector.class));
        assertThat(((CssTagNameSelector) sqCssSelector).getTagName(), is("a"));

        assertThat(cssCondition, instanceOf(CssClassAttributeCondition.class));
        assertThat(((CssClassAttributeCondition) cssCondition).getClassName(), is("condition"));
    }

    @Test
    public void translate__compound_condition() {
        // given
        String compoundConditionSelector = "a.conditionA.conditionB";
        // when
        CssSelector cssSelector = ParseTreeBuilder.parse(compoundConditionSelector).firstSelector();
        // then
        assertThat(cssSelector, instanceOf(CssConditionalSelector.class));

        CssSelector sqCssSelector = ((CssConditionalSelector) cssSelector).getCssSelector();
        CssCondition cssCondition = ((CssConditionalSelector) cssSelector).getCssCondition();

        assertThat(sqCssSelector, instanceOf(CssTagNameSelector.class));
        assertThat(((CssTagNameSelector) sqCssSelector).getTagName(), is("a"));

        assertThat(cssCondition, instanceOf(CssAndCondition.class));
        CssCondition sqCssFirstCondition = ((CssAndCondition) cssCondition).getFirstCondition();
        CssCondition sqCssSecondCondition = ((CssAndCondition) cssCondition).getSecondCondition();

        assertThat(sqCssFirstCondition, instanceOf(CssClassAttributeCondition.class));
        assertThat(((CssClassAttributeCondition) sqCssFirstCondition).getClassName(), is("conditionA"));

        assertThat(sqCssSecondCondition, instanceOf(CssClassAttributeCondition.class));
        assertThat(((CssClassAttributeCondition) sqCssSecondCondition).getClassName(), is("conditionB"));
    }

}
