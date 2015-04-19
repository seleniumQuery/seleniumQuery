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

import io.github.seleniumquery.by.csstree.condition.pseudoclass.SQCssPseudoClassCondition;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.locatorgenerationstrategy.NeverNativelySupportedPseudoClass;
import io.github.seleniumquery.by.locator.XPathLocator;

/**
 * :header
 * https://api.jquery.com/header-selector/
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssHeaderPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "header";

    public static final String HEADER_XPATH_EXPRESSION = "(" +
            "self::h0 | self::h1 | self::h2 | self::h3 | self::h4 | " +
            "self::h5 | self::h6 | self::h7 | self::h8 | self::h9" +
        ")";

    public NeverNativelySupportedPseudoClass headerPseudoClassLocatorGenerationStrategy = new NeverNativelySupportedPseudoClass() {
        @Override
        public XPathLocator toXPath() {
            return XPathLocator.pureXPath(HEADER_XPATH_EXPRESSION);
        }
    };

    @Override
    public NeverNativelySupportedPseudoClass getSQCssLocatorGenerationStrategy() {
        return headerPseudoClassLocatorGenerationStrategy;
    }

}