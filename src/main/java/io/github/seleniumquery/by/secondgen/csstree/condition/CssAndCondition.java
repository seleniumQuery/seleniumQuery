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

package io.github.seleniumquery.by.secondgen.csstree.condition;

import io.github.seleniumquery.by.secondgen.finder.ElementFinder;

/**
 * Chains conditions.
 * Example:
 * - tag.A.B
 * will be parsed/translated into
 * - tag[AndCondition(ClassCondition("A"), ClassCondition("B"))]
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssAndCondition implements CssCondition, CssConditionImplementedFinders {

    private CssCondition firstCondition;
    private CssCondition secondCondition;

    public CssAndCondition(CssCondition firstCondition, CssCondition secondCondition) {
        this.firstCondition = firstCondition;
        this.secondCondition = secondCondition;
    }

    public CssCondition getFirstCondition() {
        return firstCondition;
    }

    public CssCondition getSecondCondition() {
        return secondCondition;
    }

    @Override
    public ElementFinder toElementFinder(ElementFinder leftFinder) {
        CssConditionImplementedFinders firstCondition = (CssConditionImplementedFinders) this.firstCondition;
        CssConditionImplementedFinders secondCondition = (CssConditionImplementedFinders) this.secondCondition;

        ElementFinder elementFinder = firstCondition.toElementFinder(leftFinder);
        return secondCondition.toElementFinder(elementFinder);
    }

}
