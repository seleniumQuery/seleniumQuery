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

package io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.finderfactorystrategy;

import static io.github.seleniumquery.by.secondgen.finder.CssFinder.CSS_NOT_NATIVELY_SUPPORTED;

import org.openqa.selenium.WebDriver;

import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.CssPseudoClassCondition;
import io.github.seleniumquery.by.secondgen.finder.CssFinder;
import io.github.seleniumquery.by.secondgen.finder.ElementFinder;
import io.github.seleniumquery.by.secondgen.finder.XPathAndFilterFinder;
import io.github.seleniumquery.utils.DriverVersionUtils;

/**
 * Represents a strategy where the selector may or may not be natively supported by the driver.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public interface MaybeNativelySupportedPseudoClass extends CssPseudoClassCondition {

    @Override
    default ElementFinder toElementFinder(ElementFinder leftFinder) {
        WebDriver webDriver = leftFinder.getWebDriver();
        if (isThisCSSPseudoClassNativelySupportedOn(webDriver)) {
            return __createFinderForNativelySupportedPseudo(leftFinder, webDriver);
        } else {
            return __createFinderForUnsupportedPseudo(leftFinder, webDriver);
        }
    }

    // should be private, but java doesn't allow it yet
    default ElementFinder __createFinderForNativelySupportedPseudo(ElementFinder leftFinder, WebDriver webDriver) {
        return new ElementFinder(
                webDriver,
                leftFinder.getCssFinder().merge(toCssWhenNativelySupported(webDriver)),
                leftFinder.getXPathAndFilterFinder().merge(toXPath(webDriver), xPathMergeStrategy())
        );
    }

    // should be private, but java doesn't allow it yet
    default ElementFinder __createFinderForUnsupportedPseudo(ElementFinder leftFinder, WebDriver webDriver) {
        return new ElementFinder(
                webDriver,
                CSS_NOT_NATIVELY_SUPPORTED,
                leftFinder.getXPathAndFilterFinder().merge(toXPath(webDriver), xPathMergeStrategy())
        );
    }

    default boolean isThisCSSPseudoClassNativelySupportedOn(WebDriver webDriver) {
        return DriverVersionUtils.getInstance().hasNativeSupportForPseudo(webDriver, pseudoClassForCSSNativeSupportCheck(webDriver));
    }

    /**
     * <p>Everytime sQ executes a selector, it has to check if it has native support or not by the WebDriver.</p>
     *
     * <p>This method returns the pseudo-class selector that will be used to check if the driver supports this pseudo.</p>
     *
     * <p>Example: return ":nth-child(1)"</p>
     *
     * <p>The value returned will be appended to an id selector for the checking, e.g., if the value returned for this
     * method is {@code ":my-crazy-pseudo(1)"}, then we will send to the driver something like
     * {@code "#randomId:my-crazy-pseudo(1)"} which will be considered supported if no exception is thrown.</p>
     *
     * @param webDriver The webDriver that should be used to generate the CSS.
     * @return The css selector to be appended to an id selector for checking if the selector is supported.
     */
    default String pseudoClassForCSSNativeSupportCheck(WebDriver webDriver) {
        return toCssWhenNativelySupported(webDriver).toString();
    }

    CssFinder toCssWhenNativelySupported(WebDriver webDriver);

    default XPathMergeStrategy xPathMergeStrategy() {
        return XPathMergeStrategy.CONDITIONAL_SIMPLE_XPATH_MERGE;
    }

    XPathAndFilterFinder toXPath(WebDriver webDriver);

}
