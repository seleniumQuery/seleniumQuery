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

import io.github.seleniumquery.by.DriverVersionUtils;
import io.github.seleniumquery.by.csstree.condition.SQCssConditionImplementedLocators;
import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.filter.ElementFilterList;
import io.github.seleniumquery.by.locator.SQLocator;
import io.github.seleniumquery.by.locator.SQLocatorCss;
import io.github.seleniumquery.by.locator.SQLocatorFactory;
import org.openqa.selenium.WebDriver;

import static io.github.seleniumquery.by.locator.SQLocatorCss.universalSelector;

public abstract class SQCssPseudoMaybeNativelySupported extends SQCssPseudoClassCondition implements SQCssConditionImplementedLocators {

    @Override
    public SQLocator toSQLocator(SQLocator leftLocator) {
        if (isThisSelectorNativelySupportedOn(leftLocator.getWebDriver())) {
            return new SQLocator(
                    leftLocator.getWebDriver(),
                    leftLocator.getSQCssSelector().merge(toCssWhenNativelySupported()),
                    xPathMergeStrategy().mergeXPath(leftLocator, toXPath()),
                    true,
                    canPureXPath(),
                    mergeFilter(leftLocator));
        } else {
            return new SQLocator(
                    leftLocator.getWebDriver(),
                    leftLocator.getSQCssSelector().merge(toCssWhenNotNativelySupported()),
                    xPathMergeStrategy().mergeXPath(leftLocator, toXPath()),
                    canPureCssWhenNotNativelySupported(), // this can still be true if, somehow, the CSS selector can be translated into another CSS
                    canPureXPath(),
                    mergeFilter(leftLocator));
        }
    }

    public boolean isThisSelectorNativelySupportedOn(WebDriver webDriver) {
        return DriverVersionUtils.getInstance().hasNativeSupportForPseudo(webDriver, getSelectorForPseudoClassNativeSupportTest());
    }

    public String getSelectorForPseudoClassNativeSupportTest() {
        return toCssWhenNativelySupported().toString();
    }

    private ElementFilterList mergeFilter(SQLocator leftLocator) {
        // If this locator can be evaluated by pure XPath, then it won't have any filters
        if (canPureXPath()) {
            return leftLocator.getElementFilterList();
        }
        ElementFilter filter = toElementFilter();
        return SQLocatorFactory.createPureFilterLocator(leftLocator, filter).getElementFilterList();
    }

    public boolean canPureCssWhenNotNativelySupported() {
        return false;
    }

    public SQLocatorCss toCssWhenNativelySupported() {
        return universalSelector();
    }

    public SQLocatorCss toCssWhenNotNativelySupported() {
        return universalSelector();
    }

    public boolean canPureXPath() {
        return false;
    }

    public String toXPath() {
        return "true()";
    }

    public XPathMergeStrategy xPathMergeStrategy() {
        return XPathMergeStrategy.CONDITIONAL_SIMPLE_XPATH_MERGE;
    }

    public ElementFilter toElementFilter() {
        return ElementFilter.FILTER_NOTHING;
    }

}