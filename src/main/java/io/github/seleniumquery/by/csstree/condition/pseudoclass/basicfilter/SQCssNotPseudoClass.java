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

import com.google.common.base.Joiner;
import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.css.pseudoclasses.UnsupportedPseudoClassException;
import io.github.seleniumquery.by.csstree.SQCssSelectorList;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.SQCssFunctionalPseudoClassCondition;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.locatorgenerationstrategy.MaybeNativelySupportedPseudoClass;
import io.github.seleniumquery.by.csstree.selector.SQCssSelector;
import io.github.seleniumquery.by.locator.CSSFinder;
import io.github.seleniumquery.by.locator.XPathAndFilterFinder;
import io.github.seleniumquery.by.parser.SQParseTreeBuilder;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import java.util.LinkedList;
import java.util.List;

public class SQCssNotPseudoClass extends SQCssFunctionalPseudoClassCondition {

    // :not() are translated into :not-sq() by the pre-parser
    public static final String PSEUDO = "not-sq";

    /* when used without args, such as "div:not", the pre-parser does not translate it. it is invalid,
       but we still match it, so we can return a proper error message */
    public static final String PSEUDO_PURE_NOT = "not";

    public SQCssNotPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

    public MaybeNativelySupportedPseudoClass notPseudoClassLocatorGenerationStrategy = new MaybeNativelySupportedPseudoClass() {
        @Override
        public String pseudoClassForCSSNativeSupportCheck(WebDriver webDriver) {
            return ":"+PSEUDO_PURE_NOT+"(div)";
        }

        @Override
        public CSSFinder toCssWhenNativelySupported(WebDriver webDriver) {
            String cssString = toChainedNotSelectors(webDriver, getArgument());
            assertCssDoesNotContainUnsupportedSelectors(cssString);
            return new CSSFinder(cssString);
        }

        private String toChainedNotSelectors(WebDriver webDriver, String argument) {
            SQCssSelectorList parse = SQParseTreeBuilder.parse(argument);
            StringBuilder sb = new StringBuilder("");
            for (SQCssSelector sqCssSelector : parse) {
                sb.append(":"+PSEUDO_PURE_NOT+"(");
                sb.append(sqCssSelector.toElementFinder(webDriver).getCssFinder().toString());
                sb.append(")");
            }
            return sb.toString();
        }

        private void assertCssDoesNotContainUnsupportedSelectors(String cssString) {
            if (StringUtils.containsAny(cssString, ' ', '>', '~', '+')) {
                throw new UnsupportedPseudoClassException(":not() with descendant (a b, a>b) or sibling (a+b, a~b) selecors as argument is not yet supported.");
            }
        }

        @Override
        public XPathAndFilterFinder toXPath(WebDriver webDriver) {
            SQCssSelectorList parse = SQParseTreeBuilder.parse(getArgument());
            List<String> xPathExpressions = new LinkedList<String>();
            for (SQCssSelector sqCssSelector : parse) {
                xPathExpressions.add(sqCssSelector.toElementFinder(webDriver).getXPathAndFilterFinder().getRawXPathExpression());
            }
            String joinedXPathExps = Joiner.on(" | ").join(xPathExpressions);
            return XPathAndFilterFinder.pureXPath("not("+joinedXPathExps+")");
        }
    };

    @Override
    public MaybeNativelySupportedPseudoClass getSQCssLocatorGenerationStrategy() {
        return notPseudoClassLocatorGenerationStrategy;
    }

}