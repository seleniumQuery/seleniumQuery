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

import static io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssNotPseudoClass.PSEUDO_PURE_NOT;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import com.google.common.base.Joiner;
import io.github.seleniumquery.by.firstgen.css.pseudoclasses.UnsupportedPseudoClassException;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.CssPseudoClassCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.finderfactorystrategy.MaybeNativelySupportedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssSelector;
import io.github.seleniumquery.by.secondgen.finder.CssFinder;
import io.github.seleniumquery.by.secondgen.finder.XPathAndFilterFinder;

/**
 * :not(selectorlist)
 *
 * @author acdcjunior
 * @since 0.17.0
 */
public class CssNotPseudoClass implements CssPseudoClassCondition, MaybeNativelySupportedPseudoClass {

    private final AstCssNotPseudoClass astCssNotPseudoClass;

    public CssNotPseudoClass(AstCssNotPseudoClass astCssNotPseudoClass) {
        this.astCssNotPseudoClass = astCssNotPseudoClass;
    }

    @Override
    public String pseudoClassForCSSNativeSupportCheck(WebDriver webDriver) {
        return ":"+PSEUDO_PURE_NOT+"(div)";
    }

    @Override
    public CssFinder toCssWhenNativelySupported(WebDriver webDriver) {
        String cssString = toChainedNotSelectors(webDriver);
        assertCssDoesNotContainUnsupportedSelectors(cssString);
        return new CssFinder(cssString);
    }

    private String toChainedNotSelectors(WebDriver webDriver) {
        StringBuilder chainedNotSelectors = new StringBuilder();
        for (CssSelector cssSelector : astCssNotPseudoClass.getNegatedSelector()) {
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
        List<String> xPathExpressions = new LinkedList<>();
        for (CssSelector cssSelector : astCssNotPseudoClass.getNegatedSelector()) {
            xPathExpressions.add(cssSelector.toElementFinder(webDriver).getXPathAndFilterFinder().getRawXPathExpression());
        }
        String joinedXPathExps = Joiner.on(" | ").join(xPathExpressions);
        return XPathAndFilterFinder.pureXPath("not("+joinedXPathExps+")");
    }

}
