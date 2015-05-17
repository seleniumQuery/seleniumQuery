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

import io.github.seleniumquery.by.csstree.condition.pseudoclass.SQCssPseudoClassCondition;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.locatorgenerationstrategy.AlwaysNativelySupportedPseudoClass;
import io.github.seleniumquery.by.locator.CSSFinder;
import io.github.seleniumquery.by.locator.XPathLocator;
import org.openqa.selenium.WebDriver;

import static io.github.seleniumquery.by.css.attributes.AttributeEvaluatorUtils.TYPE_ATTR_LC_VAL;

/**
 * This represents the pseudoclasses that check for the type attribute, such as
 * <code>:password</code>, that is equivalent to <code>[type="password"]</code>.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
abstract class SQCssInputTypeAttributePseudoClass extends SQCssPseudoClassCondition {

    private String typeAttributeValue;

    public AlwaysNativelySupportedPseudoClass inputTypePseudoClassLocatorGenerationStrategy = new AlwaysNativelySupportedPseudoClass() {
        @Override
        public CSSFinder toCssWhenNativelySupported(WebDriver webDriver) {
            return new CSSFinder("input", "[type=\"" + typeAttributeValue + "\"]");
        }

        @Override
        public XPathLocator toXPath(WebDriver webDriver) {
            return XPathLocator.pureXPath("(self::input and " + TYPE_ATTR_LC_VAL + " = '" + typeAttributeValue + "')");
        }
    };

    protected SQCssInputTypeAttributePseudoClass(String typeAttributeValue) {
        this.typeAttributeValue = typeAttributeValue;
    }

    @Override
    public AlwaysNativelySupportedPseudoClass getSQCssLocatorGenerationStrategy() {
        return inputTypePseudoClassLocatorGenerationStrategy;
    }

}