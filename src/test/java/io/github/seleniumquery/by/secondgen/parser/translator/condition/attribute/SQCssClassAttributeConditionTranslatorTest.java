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

package io.github.seleniumquery.by.secondgen.parser.translator.condition.attribute;

import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.SQCssClassAttributeCondition;
import org.junit.Test;

import static io.github.seleniumquery.by.secondgen.parser.translator.condition.attribute.TranslatorsTestUtils.parseAndAssertFirstCssCondition;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SQCssClassAttributeConditionTranslatorTest {

    @Test
    public void translate__should_translate_regular_and_escaped_classes() {
        new ClassNameVerifier().verify();
    }

    @SuppressWarnings("deprecation")
    private static class ClassNameVerifier extends ConditionTranslatorVerifier {
        ClassNameVerifier() { super("."); }
        @Override
        public SQCssClassAttributeCondition verifyTranslation(String actualSelector, String expectedClassName) {
            // given
            // selector arg
            // when
            SQCssClassAttributeCondition cssCondition = parseAndAssertFirstCssCondition(prefix + actualSelector, SQCssClassAttributeCondition.class);
            // then
            assertThat(cssCondition.getClassName(), is(expectedClassName));
            return cssCondition;
        }
    }

}