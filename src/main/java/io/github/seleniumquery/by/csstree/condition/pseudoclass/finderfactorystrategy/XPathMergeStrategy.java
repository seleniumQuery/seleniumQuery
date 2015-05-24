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

package io.github.seleniumquery.by.csstree.condition.pseudoclass.finderfactorystrategy;

import io.github.seleniumquery.by.finder.ElementFinderUtils;

/**
 * @author acdcjunior
 * @since 0.10.0
 */
public abstract class XPathMergeStrategy {

    // TODO these methods do not have unit tests specific to them
    // I'm leaving them without unit tests right now because I get a feeling they
    // will be moved or the method they call will be inlined.
    // Still, do something about it when you move them or inline those.
    public static final XPathMergeStrategy CONDITIONAL_SIMPLE_XPATH_MERGE = new XPathMergeStrategy() {
        @Override
        public String mergeXPath(String leftXPathExpression, String rightXPathExpression) {
            return ElementFinderUtils.conditionalSimpleXPathMerge(leftXPathExpression, rightXPathExpression);
        }
    };

    public static final XPathMergeStrategy CONDITIONAL_TO_ALL_XPATH_MERGE = new XPathMergeStrategy() {
        @Override
        public String mergeXPath(String leftXPathExpression, String rightXPathExpression) {
            return ElementFinderUtils.conditionalToAllXPathMerge(leftXPathExpression, rightXPathExpression);
        }
    };

    public abstract String mergeXPath(String leftXPathExpression, String rightXPathExpression);

}