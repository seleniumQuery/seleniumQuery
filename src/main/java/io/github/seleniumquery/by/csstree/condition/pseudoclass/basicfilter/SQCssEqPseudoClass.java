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

package io.github.seleniumquery.by.csstree.condition.pseudoclass.basicfilter;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.SQCssFunctionalIndexArgumentPseudoClassCondition;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.locatorgenerationstrategy.NeverNativelySupportedPseudoClass;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.locatorgenerationstrategy.XPathMergeStrategy;
import io.github.seleniumquery.by.locator.SQLocatorXPath;

/**
 * :eq(index)
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssEqPseudoClass extends SQCssFunctionalIndexArgumentPseudoClassCondition {

    public static final String PSEUDO = "eq";

    public NeverNativelySupportedPseudoClass eqPseudoClassLocatorGenerationStrategy = new NeverNativelySupportedPseudoClass() {
        @Override
        public SQLocatorXPath toXPath() {
            int index = getArgumentAsIndex();
            if (index >= 0) {
                return SQLocatorXPath.pureXPath("position() = " + (index + 1));
            }
            int positionFromLast = -index - 1;
            if (positionFromLast == 0) {
                return SQLocatorXPath.pureXPath("position() = last()");
            }
            return SQLocatorXPath.pureXPath("position() = (last()-" + positionFromLast + ")");
        }
        @Override
        public XPathMergeStrategy xPathMergeStrategy() {
            return XPathMergeStrategy.CONDITIONAL_TO_ALL_XPATH_MERGE;
        }
    };

    public SQCssEqPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

    @Override
    public NeverNativelySupportedPseudoClass getSQCssLocatorGenerationStrategy() {
        return eqPseudoClassLocatorGenerationStrategy;
    }

    @Override
    protected String getPseudoClassName() {
        return PSEUDO;
    }

}