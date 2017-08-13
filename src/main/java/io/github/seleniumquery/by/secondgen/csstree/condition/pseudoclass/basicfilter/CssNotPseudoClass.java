/*
 * Copyright (c) 2016 seleniumQuery authors
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

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import com.google.common.base.Joiner;
import io.github.seleniumquery.by.firstgen.css.pseudoclasses.UnsupportedPseudoClassException;
import io.github.seleniumquery.by.secondgen.csstree.CssSelectorList;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.CssFunctionalPseudoClassCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.SqCssFunctionalPseudoClassArgument;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.finderfactorystrategy.MaybeNativelySupportedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssSelector;
import io.github.seleniumquery.by.secondgen.finder.CssFinder;
import io.github.seleniumquery.by.secondgen.finder.XPathAndFilterFinder;
import io.github.seleniumquery.by.secondgen.parser.ParseTreeBuilder;

/**
 * :not(selectorlist)
 *
 * Notice that the preparser turns :not() into :not-sq(), because SACCssParser doesn't accept commas
 * inside :not().
 *
 * @author acdcjunior
 * @since 0.17.0
 */
public class CssNotPseudoClass extends CssFunctionalPseudoClassCondition implements MaybeNativelySupportedPseudoClass {

    // :not() are translated into :not-sq() by the pre-parser
    public static final String PSEUDO = "not-sq";

    /* when used without args, such as "div:not", the pre-parser does not translate it. it is invalid,
       but we still match it, so we can return a proper error message */
    public static final String PSEUDO_PURE_NOT = "not";

    public CssNotPseudoClass(String pseudoClassArgument) {
        super(pseudoClassArgument);
    }

    @Override
    public String pseudoClassForCSSNativeSupportCheck(WebDriver webDriver) {
        return ":"+PSEUDO_PURE_NOT+"(div)";
    }

    @Override
    public CssFinder toCssWhenNativelySupported(WebDriver webDriver) {
        String cssString = toChainedNotSelectors(webDriver, getArgument());
        assertCssDoesNotContainUnsupportedSelectors(cssString);
        return new CssFinder(cssString);
    }

    private String toChainedNotSelectors(WebDriver webDriver, SqCssFunctionalPseudoClassArgument functionalPseudoClassArgument) {
        CssSelectorList parsedNotPseudoClassArgument = ParseTreeBuilder.parse(functionalPseudoClassArgument.getArgumentAsString());
        StringBuilder chainedNotSelectors = new StringBuilder();
        for (CssSelector cssSelector : parsedNotPseudoClassArgument) {
            chainedNotSelectors.append(":").append(PSEUDO_PURE_NOT).append("(").append(cssSelector.toElementFinder(webDriver).toCssString()).append(")");
        }
        return chainedNotSelectors.toString();
    }

    private void assertCssDoesNotContainUnsupportedSelectors(String cssString) {
        if (StringUtils.containsAny(cssString, ' ', '>', '~', '+')) {
            throw new UnsupportedPseudoClassException(":not() with descendant (a b, a>b) or sibling (a+b, a~b) selectors as argument is not yet supported.");
        }
    }

    @Override
    public XPathAndFilterFinder toXPath(WebDriver webDriver) {
        CssSelectorList parsedNotPseudoClassArgument = ParseTreeBuilder.parse(getArgument().getArgumentAsString());
        List<String> xPathExpressions = new LinkedList<>();
        for (CssSelector cssSelector : parsedNotPseudoClassArgument) {
            xPathExpressions.add(cssSelector.toElementFinder(webDriver).getXPathAndFilterFinder().getRawXPathExpression());
        }
        String joinedXPathExps = Joiner.on(" | ").join(xPathExpressions);
        return XPathAndFilterFinder.pureXPath("not("+joinedXPathExps+")");
    }

}
