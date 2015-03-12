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
import io.github.seleniumquery.by.locator.SQLocator;
import io.github.seleniumquery.by.locator.SQLocatorCss;
import io.github.seleniumquery.by.locator.SQLocatorXPath;
import org.openqa.selenium.WebDriver;

import static io.github.seleniumquery.by.locator.SQLocatorCss.CSS_NOT_NATIVELY_SUPPORTED;

public abstract class SQCssPseudoMaybeNativelySupported extends SQCssPseudoClassCondition implements SQCssConditionImplementedLocators {

    @Override
    public SQLocator toSQLocator(SQLocator leftLocator) {
        if (isThisSelectorNativelySupportedOn(leftLocator.getWebDriver())) {
            return new SQLocator(
                    leftLocator.getWebDriver(),
                    leftLocator.getSQCssSelector().merge(toCssWhenNativelySupported()),
                    new SQLocatorXPath(
                        xPathMergeStrategy().mergeXPath(leftLocator, toXPath()), // TODO move this mergeXPath() to SQLocatorXPath, obviously
                        canPureXPath(),
                        SQLocatorXPath.mergeFilter(this, leftLocator)
                    ));
        } else {
            return new SQLocator(
                    leftLocator.getWebDriver(),
                    CSS_NOT_NATIVELY_SUPPORTED,
                    new SQLocatorXPath(
                        xPathMergeStrategy().mergeXPath(leftLocator, toXPath()),
                        canPureXPath(),
                        SQLocatorXPath.mergeFilter(this, leftLocator))
                    );
        }
    }

    public boolean isThisSelectorNativelySupportedOn(WebDriver webDriver) {
        return DriverVersionUtils.getInstance().hasNativeSupportForPseudo(webDriver, selectorForPseudoClassNativeSupportCheck());
    }

    /**
     * <p>Returns the pseudo-class selector that will be used to check if the driver supports this pseudo.</p>
     * <p>Example: {@code ":nth-child(1)"}</p>
     * @return The css selector to be appended to an id selector for checking if the selector is supported.
     */
    public String selectorForPseudoClassNativeSupportCheck() {
        return toCssWhenNativelySupported().toString();
    }

    public abstract SQLocatorCss toCssWhenNativelySupported();

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