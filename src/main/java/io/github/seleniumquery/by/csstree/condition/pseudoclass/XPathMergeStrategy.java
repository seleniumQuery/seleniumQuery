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

package io.github.seleniumquery.by.csstree.condition.pseudoclass;

import io.github.seleniumquery.by.locator.SQLocator;
import io.github.seleniumquery.by.locator.SQLocatorUtils;

/**
 * @author acdcjunior
 * @since 0.10.0
 */
public abstract class XPathMergeStrategy {

    public static final XPathMergeStrategy CONDITIONAL_SIMPLE_XPATH_MERGE = new XPathMergeStrategy() {
        @Override
        public String mergeXPath(SQLocator leftLocator, String xPathPart) {
            return SQLocatorUtils.conditionalSimpleXPathMerge(leftLocator.getXPathExpression(), xPathPart);
        }
    };

    public abstract String mergeXPath(SQLocator leftLocator, String xPathPart);

}