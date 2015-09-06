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

package io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form;

import io.github.seleniumquery.by.DriverVersionUtils;
import io.github.seleniumquery.by.firstgen.css.pseudoclasses.CheckedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.SQCssPseudoClassCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.finderfactorystrategy.MaybeNativelySupportedPseudoClass;
import io.github.seleniumquery.by.secondgen.finder.CssFinder;
import io.github.seleniumquery.by.secondgen.finder.XPathAndFilterFinder;
import org.openqa.selenium.WebDriver;

import static io.github.seleniumquery.by.common.AttributeEvaluatorUtils.TYPE_ATTR_LC_VAL;

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
 *    We have a test (endtoend.crossdriver.driverbugs.PhantomJSAndHtmlUnitCheckedSelectorBugTest) that asserts these
 *    bugs continue to exist.
 * </p>
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssCheckedPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "checked";
    public static final String CHECKED_PSEUDO = ":checked";

    public MaybeNativelySupportedPseudoClass checkedPseudoClassFinderFactoryStrategy = new MaybeNativelySupportedPseudoClass() {
        @Override
        public boolean isThisCSSPseudoClassNativelySupportedOn(WebDriver webDriver) {
            return isDriverWhereCheckedSelectorHasNoBugs(webDriver) && super.isThisCSSPseudoClassNativelySupportedOn(webDriver);
        }

        @Override
        public CssFinder toCssWhenNativelySupported(WebDriver webDriver) {
            return new CssFinder(CHECKED_PSEUDO);
        }

        @Override
        public XPathAndFilterFinder toXPath(WebDriver webDriver) {
            return new XPathAndFilterFinder(xPathExpression(), CheckedPseudoClass.CHECKED_FILTER);
        }

        private String xPathExpression() {
            return "((self::input and ("+ TYPE_ATTR_LC_VAL +" = 'radio' or "+ TYPE_ATTR_LC_VAL +" = 'checkbox')) or self::option)";
        }
    };

    @Override
    public MaybeNativelySupportedPseudoClass getElementFinderFactoryStrategy() {
        return checkedPseudoClassFinderFactoryStrategy;
    }

    public static boolean isDriverWhereCheckedSelectorHasNoBugs(WebDriver webDriver) {
        DriverVersionUtils driverVsUtils = DriverVersionUtils.getInstance();
        return !driverVsUtils.isPhantomJSDriver(webDriver) && !driverVsUtils.isHtmlUnitDriver(webDriver);
    }

}