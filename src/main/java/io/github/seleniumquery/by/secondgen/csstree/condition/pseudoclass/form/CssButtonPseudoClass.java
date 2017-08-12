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

import static io.github.seleniumquery.by.common.AttributeEvaluatorUtils.TYPE_ATTR_LC_VAL;

import org.openqa.selenium.WebDriver;

import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.finderfactorystrategy.NeverNativelySupportedPseudoClass;
import io.github.seleniumquery.by.secondgen.finder.XPathAndFilterFinder;

/**
 * :button
 *
 * Currently, it is implemented as never natively supported.
 * In the future we can try to chain to tag seletctor using not+not. Something like:
 *
 * .clazz:button
 * that becomes
 * (input[type=button],button).clazz
 * which is INVALID, that becomes
 * .clazz:not(:not(input[type=button]):not(button))
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssButtonPseudoClass implements NeverNativelySupportedPseudoClass {

    public static final String PSEUDO = "button";

    @Override
    public XPathAndFilterFinder toXPath(WebDriver webDriver) {
        return XPathAndFilterFinder.pureXPath("((self::input and " + TYPE_ATTR_LC_VAL + " = 'button') or self::button)");
    }

}
