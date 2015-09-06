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

package io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter;

import io.github.seleniumquery.by.firstgen.css.pseudoclasses.PseudoClassSelector;

/**
 * :first-child
 * https://api.jquery.com/first-child-selector/
 * https://developer.mozilla.org/pt-BR/docs/Web/CSS/:first-child
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssFirstChildPseudoClass extends SQCssNthChildPseudoClass {

    public static final String PSEUDO = "first-child";

    public SQCssFirstChildPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

    @Override
    public String getArgument() {
        return "1";
    }

}