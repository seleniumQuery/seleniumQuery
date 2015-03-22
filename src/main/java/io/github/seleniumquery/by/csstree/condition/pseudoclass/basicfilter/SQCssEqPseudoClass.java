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
import io.github.seleniumquery.by.csstree.condition.pseudoclass.SQCssFunctionalPseudoClassCondition;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.SQCssPseudoNeverNativelySupported;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.XPathMergeStrategy;
import io.github.seleniumquery.by.locator.SQLocatorXPath;
import org.openqa.selenium.InvalidSelectorException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * :eq(index)
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssEqPseudoClass extends SQCssFunctionalPseudoClassCondition {

    public static final String PSEUDO = "eq";
    public static final Pattern REGEX = Pattern.compile("^\\s*([+-]?\\d+)\\s*$");

    public SQCssPseudoNeverNativelySupported eqPseudoClassLocatorGenerationStrategy = new SQCssPseudoNeverNativelySupported() {
        @Override
        public SQLocatorXPath toXPath() {
            int index = getEqIndex();
            if (index >= 0) {
                return SQLocatorXPath.pureXPath("position() = " + (index + 1));
            }
            return SQLocatorXPath.pureXPath("position() = (last()-" + (-index - 1) + ")");
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
    public SQCssPseudoNeverNativelySupported getSQCssLocatorGenerationStrategy() {
        return eqPseudoClassLocatorGenerationStrategy;
    }

    private int getEqIndex() {
        String eqPseudoClassArgument = getArgument();
        Matcher m = REGEX.matcher(eqPseudoClassArgument);
        boolean isArgumentAnInteger = m.find();
        if (!isArgumentAnInteger) {
            throw new InvalidSelectorException("The :eq() pseudo-class requires an integer as argument but got: " + eqPseudoClassArgument);
        }
        String integerIndex = m.group(1);
        if (integerIndex.startsWith("+")) {
            integerIndex = integerIndex.substring(1);
        }
        return Integer.valueOf(integerIndex);
    }

}