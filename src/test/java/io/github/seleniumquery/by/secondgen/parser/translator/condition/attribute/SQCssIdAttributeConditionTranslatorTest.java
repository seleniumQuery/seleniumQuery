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

package io.github.seleniumquery.by.secondgen.parser.translator.condition.attribute;

import io.github.seleniumquery.by.secondgen.csstree.condition.SQCssCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.SQCssConditionImplementedFinders;
import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.SQCssIdAttributeCondition;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssConditionalSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssSelector;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssTagNameSelector;
import io.github.seleniumquery.by.secondgen.parser.SQParseTreeBuilder;
import org.junit.Test;

import static io.github.seleniumquery.by.secondgen.parser.translator.condition.attribute.TranslatorsTestUtils.parseAndAssertFirstCssCondition;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssIdAttributeConditionTranslatorTest {

    @Test
    public void translate() {
        // given
        CssSelector cssSelector = SQParseTreeBuilder.parse("#ball").firstSelector();
        assertThat(cssSelector, instanceOf(CssConditionalSelector.class));
        // when
        CssSelector sqCssSelector = ((CssConditionalSelector) cssSelector).getCssSelector();
        SQCssCondition sqCssCondition = ((CssConditionalSelector) cssSelector).getSqCssCondition();
        // then
        assertThat(sqCssSelector, instanceOf(CssTagNameSelector.class));
        assertThat(((CssTagNameSelector) sqCssSelector).getTagName(), is("*"));

        assertThat(sqCssCondition, instanceOf(SQCssIdAttributeCondition.class));
        assertThat(((SQCssIdAttributeCondition) sqCssCondition).getId(), is("ball"));
    }

    @Test
    public void translate__should_translate_regular_and_escaped_ids() {
        new IdVerifier().verify();
    }

    @SuppressWarnings("deprecation")
    static class IdVerifier extends ConditionTranslatorVerifier {
        public IdVerifier() { super("#"); }
        @Override
        public SQCssConditionImplementedFinders verifyTranslation(String actualSelector, String expectedId) {
            // given
            // selector arg
            // when
            SQCssIdAttributeCondition cssCondition = parseAndAssertFirstCssCondition(prefix + actualSelector, SQCssIdAttributeCondition.class);
            // then
            assertThat(cssCondition.getId(), is(expectedId));
            return cssCondition;
        }
    }

}
