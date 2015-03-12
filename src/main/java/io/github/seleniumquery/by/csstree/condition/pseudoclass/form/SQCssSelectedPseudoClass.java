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

import io.github.seleniumquery.by.css.pseudoclasses.SelectedPseudoClass;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.SQCssPseudoMaybeNativelySupported;
import io.github.seleniumquery.by.locator.SQLocatorCss;
import io.github.seleniumquery.by.locator.SQLocatorXPath;

/**
 * :selected can be translated into option:checked.
 * We need to expand how SQLocator handles the CSS selector, as we needed to override the previously set tag.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssSelectedPseudoClass extends SQCssPseudoMaybeNativelySupported {

    public static final String PSEUDO = "selected";

    @Override
    public String pseudoClassForCSSNativeSupportCheck() {
        return SQCssCheckedPseudoClass.CHECKED_PSEUDO;
    }

    @Override
    public SQLocatorCss toCssWhenNativelySupported() {
        return new SQLocatorCss("option", ":checked");
    }

    @Override
    public SQLocatorXPath toXPath() {
        return new SQLocatorXPath("self::option", SelectedPseudoClass.SELECTED_FILTER);
    }

}