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

package io.github.seleniumquery.by.csstree.condition.pseudoclass.form;

import io.github.seleniumquery.by.DriverVersionUtils;
import io.github.seleniumquery.by.css.pseudoclasses.CheckedPseudoClass;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.SQCssPseudoMaybeNativelySupported;
import io.github.seleniumquery.by.locator.SQLocatorCss;
import io.github.seleniumquery.by.locator.SQLocatorXPath;
import org.openqa.selenium.WebDriver;

/**
 * <p>
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:checked
 * </p>
 *
 * <p>
 *    <h1>#Cross-Driver</h1>
 *    In PhantomJSDriver and HtmlUnitDriver, <code>document.querySelectorAll(":checked")</code> does not work
 *    for {@code <option>} tags, so we should consider it as not supported!
 *    <br>
 *    Issue in PhantomJS: https://github.com/ariya/phantomjs/issues/11550
 *    <br>
 *    We have a test (integration.crossdriver.driverbugs.PhantomJSAndHtmlUnitCheckedSelectorBugTest) that asserts these
 *    bugs continue to exist.
 * </p>
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssCheckedPseudoClass extends SQCssPseudoMaybeNativelySupported {

    public static final String PSEUDO = "checked";
    public static final String CHECKED_PSEUDO = ":checked";

    @Override
    public boolean isThisCSSPseudoClassNativelySupportedOn(WebDriver webDriver) {
        return isDriverWhereCheckedSelectorHasNoBugs(webDriver) && super.isThisCSSPseudoClassNativelySupportedOn(webDriver);
    }

    private boolean isDriverWhereCheckedSelectorHasNoBugs(WebDriver webDriver) {
        DriverVersionUtils driverVersionUtils = DriverVersionUtils.getInstance();
        return !driverVersionUtils.isPhantomJSDriver(webDriver) && !driverVersionUtils.isHtmlUnitDriver(webDriver);
    }

    @Override
    public SQLocatorCss toCssWhenNativelySupported() {
        return new SQLocatorCss(CHECKED_PSEUDO);
    }

    @Override
    public SQLocatorXPath toXPath() {
        return new SQLocatorXPath(xPathExpression(), CheckedPseudoClass.CHECKED_FILTER);
    }

    private String xPathExpression() {
        return "((self::input and (@type = 'radio' or @type = 'checkbox')) or self::option)";
    }

}