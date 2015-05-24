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

package io.github.seleniumquery.by.finder;

/**
 * Utilities for SQCss* classes.
 */
public class SQLocatorUtils {

    public static String conditionalSimpleXPathMerge(String leftXPathExpression, String rightXPathExpression) {
        if (leftXPathExpression == null || !leftXPathExpression.endsWith("]")) {
            throw new IllegalArgumentException("Left XPath expression is invalid.");
        }
        if (leftXPathExpression.endsWith("[true()]") || leftXPathExpression.endsWith(" and true()]")) {
            return leftXPathExpression.substring(0, leftXPathExpression.length()-7) + rightXPathExpression + "]";
        }
        // because the previous was merged as a conditional, and we are a conditional as well, so we just 'AND it
        return leftXPathExpression.substring(0, leftXPathExpression.length()-1) + " and " + rightXPathExpression + "]";
    }

    public static String conditionalToAllXPathMerge(String leftXPathExpression, String rightXPathExpression) {
        String resultingLeftXPathExpression = leftXPathExpression;
        if (leftXPathExpression.endsWith("[true()]")) {
            resultingLeftXPathExpression = leftXPathExpression.substring(0, leftXPathExpression.length()-8);
        }
        return "(" + resultingLeftXPathExpression + ")[" + rightXPathExpression + "]";
    }

}