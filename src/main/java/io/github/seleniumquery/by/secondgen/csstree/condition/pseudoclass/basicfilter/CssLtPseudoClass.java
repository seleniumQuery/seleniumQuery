/*
 * Copyright (c) 2017 seleniumQuery authors
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

package io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter;

import org.openqa.selenium.WebDriver;

import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.finderfactorystrategy.NeverNativelySupportedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.finderfactorystrategy.XPathMergeStrategy;
import io.github.seleniumquery.by.secondgen.finder.XPathAndFilterFinder;

/**
 * :lt(index)
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssLtPseudoClass implements NeverNativelySupportedPseudoClass {

    private final AstCssLtPseudoClass astCssLtPseudoClass;

    public CssLtPseudoClass(AstCssLtPseudoClass astCssLtPseudoClass) {
        this.astCssLtPseudoClass = astCssLtPseudoClass;
    }

    @Override
    public XPathAndFilterFinder toXPath(WebDriver webDriver) {
        int index = astCssLtPseudoClass.getArgument();
        if (index >= 0) {
            return XPathAndFilterFinder.pureXPath("position() < " + (index + 1));
        }
        return XPathAndFilterFinder.pureXPath("position() < (last()-" + (-index - 1) + ")");
    }

    @Override
    public XPathMergeStrategy xPathMergeStrategy() {
        return XPathMergeStrategy.CONDITIONAL_TO_ALL_XPATH_MERGE;
    }

}
