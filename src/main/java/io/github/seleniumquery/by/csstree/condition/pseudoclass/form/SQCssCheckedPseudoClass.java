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
import io.github.seleniumquery.by.filter.ElementFilter;
import org.openqa.selenium.WebDriver;

/**
 * <p>
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:checked
 * </p>
 * #Cross-Driver
 *
 * <p>
 *    In HtmlUnitDriver, <code>document.querySelectorAll(":checked")</code> is not consistent, so we should consider it as
 *    not supported;
 * </p>
 * <p>
 *    In PhantomJSDriver, <code>document.querySelectorAll(":checked")</code> does not work for {@code <option>} tags, so we should
 *    consider it as not supported as well!
 *    <br>
 *    SEE: https://github.com/ariya/phantomjs/issues/11550
 *    <br>
 *    We have a test (integration.crossdriver.driverbugs.PhantomJSCheckedSelectorBugTest) that asserts the bug in
 *    PhantomJS continues to exist.
 * </p>
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssCheckedPseudoClass extends SQCssPseudoMaybeNativelySupported {

    public static final String PSEUDO = "checked";
    public static final String CHECKED_PSEUDO = ":checked";

    @Override
    public boolean isThisSelectorNativelySupportedOn(WebDriver webDriver) {
        return !DriverVersionUtils.getInstance().isPhantomJSDriver(webDriver) && super.isThisSelectorNativelySupportedOn(webDriver);
    }

    @Override
    public String toCssWhenNativelySupported() {
        return CHECKED_PSEUDO;
    }

    @Override
    public String toXPath() {
        return "((self::input and (@type = 'radio' or @type = 'checkbox')) or self::option)";
    }

    @Override
    public ElementFilter toElementFilter() {
        return CheckedPseudoClass.CHECKED_FILTER;
    }

}