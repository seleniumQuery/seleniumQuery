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
import io.github.seleniumquery.by.finder.XPathAndFilterFinder;
import org.openqa.selenium.WebDriver;

/**
 * :even
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssEvenPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "even";

    public NeverNativelySupportedPseudoClass evenPseudoClassLocatorGenerationStrategy = new NeverNativelySupportedPseudoClass() {
        @Override
        public XPathAndFilterFinder toXPath(WebDriver webDriver) {
            return XPathAndFilterFinder.pureXPath("(position() mod 2) = 1");
        }
    };

    @Override
    public NeverNativelySupportedPseudoClass getSQCssLocatorGenerationStrategy() {
        return evenPseudoClassLocatorGenerationStrategy;
    }

}