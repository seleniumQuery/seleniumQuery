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

import io.github.seleniumquery.by.csstree.condition.pseudoclass.SQCssPseudoNeverNativelySupported;
import io.github.seleniumquery.by.locator.SQLocatorXPath;

import static io.github.seleniumquery.by.css.attributes.AttributeEvaluatorUtils.TYPE_ATTR_LC_VAL;

/**
 * :reset selector
 * http://api.jquery.com/reset-selector/
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssResetPseudoClass extends SQCssPseudoNeverNativelySupported {

    public static final String PSEUDO = "reset";

    @Override
    public SQLocatorXPath toXPath() {
        return SQLocatorXPath.pureXPath("((self::input or self::button) and "+ TYPE_ATTR_LC_VAL +" = 'reset')");
    }

}