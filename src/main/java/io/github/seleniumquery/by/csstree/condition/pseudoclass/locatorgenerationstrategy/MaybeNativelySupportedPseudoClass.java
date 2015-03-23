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

package io.github.seleniumquery.by.csstree.condition.pseudoclass.locatorgenerationstrategy;

import io.github.seleniumquery.by.DriverVersionUtils;
import io.github.seleniumquery.by.csstree.condition.SQCssConditionImplementedLocators;
import io.github.seleniumquery.by.locator.CSSLocator;
import io.github.seleniumquery.by.locator.SQLocator;
import io.github.seleniumquery.by.locator.XPathLocator;
import org.openqa.selenium.WebDriver;

import static io.github.seleniumquery.by.locator.CSSLocator.CSS_NOT_NATIVELY_SUPPORTED;

/**
 * Represents a strategy where the selector may or may not be natively supported by the driver.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public abstract class MaybeNativelySupportedPseudoClass implements SQCssConditionImplementedLocators {

    @Override
    public SQLocator toSQLocator(SQLocator leftLocator) {
        if (isThisCSSPseudoClassNativelySupportedOn(leftLocator.getWebDriver())) {
            return new SQLocator(
                    leftLocator.getWebDriver(),
                    leftLocator.getCSSLocator().merge(toCssWhenNativelySupported()),
                    leftLocator.getXPathLocator().merge(toXPath(), xPathMergeStrategy())
            );
        } else {
            return new SQLocator(
                    leftLocator.getWebDriver(),
                    CSS_NOT_NATIVELY_SUPPORTED,
                    leftLocator.getXPathLocator().merge(toXPath(), xPathMergeStrategy())
            );
        }
    }

    public boolean isThisCSSPseudoClassNativelySupportedOn(WebDriver webDriver) {
        return DriverVersionUtils.getInstance().hasNativeSupportForPseudo(webDriver, pseudoClassForCSSNativeSupportCheck());
    }

    /**
     * <p>Returns the pseudo-class selector that will be used to check if the driver supports this pseudo.</p>
     * <p>Example: {@code ":nth-child(1)"}</p>
     * <p>This will be appended to an id selector for the checking, e.g., if the value returned for this
     * method is {@code ":my-crazy-pseudo(1)"}, then we will send to the browser something like
     * {@code "#randomId:my-crazy-pseudo(1)"} which will be considered supported if no exception is thrown.</p>
     * @return The css selector to be appended to an id selector for checking if the selector is supported.
     */
    public String pseudoClassForCSSNativeSupportCheck() {
        return toCssWhenNativelySupported().toString();
    }

    public abstract CSSLocator toCssWhenNativelySupported();

    public XPathMergeStrategy xPathMergeStrategy() {
        return XPathMergeStrategy.CONDITIONAL_SIMPLE_XPATH_MERGE;
    }

    public abstract XPathLocator toXPath();

}