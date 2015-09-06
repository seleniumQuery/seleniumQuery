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

package io.github.seleniumquery.by.csstree.condition.pseudoclass.basicfilter;

import io.github.seleniumquery.by.css.pseudoclasses.UnsupportedPseudoClassException;
import io.github.seleniumquery.by.csstree.condition.SQCssConditionImplementedFinders;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.SQCssPseudoClassCondition;
import io.github.seleniumquery.by2.finder.ElementFinder;

/**
 * To implement this selector, we would need to use JavaScript and access jQuery's internals.
 * Considering the utility of this selector (from the user's point of view) is minimal, we aren't including it.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssAnimatedPseudoClass extends SQCssPseudoClassCondition implements SQCssConditionImplementedFinders {

    public static final String PSEUDO = "animated";

    @Override
    public ElementFinder toElementFinder(ElementFinder leftFinder) {
        throw new UnsupportedPseudoClassException(":animated", "This selector uses internals of jQuery that nor seleniumQuery, " +
                "neither the user should access.");
    }

}