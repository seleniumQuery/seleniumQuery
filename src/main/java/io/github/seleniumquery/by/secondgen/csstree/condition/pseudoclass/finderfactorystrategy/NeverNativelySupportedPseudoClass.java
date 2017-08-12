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

import org.openqa.selenium.WebDriver;

import io.github.seleniumquery.by.secondgen.csstree.condition.CssConditionImplementedFinders;
import io.github.seleniumquery.by.secondgen.finder.CssFinder;

/**
 * Pseudos extending this class will never ever even check for native support.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public interface NeverNativelySupportedPseudoClass extends MaybeNativelySupportedPseudoClass, CssConditionImplementedFinders {

    @Override
    default boolean isThisCSSPseudoClassNativelySupportedOn(WebDriver webDriver) {
        return false;
    }

    /**
     * Due to the {@link NeverNativelySupportedPseudoClass#isThisCSSPseudoClassNativelySupportedOn(org.openqa.selenium.WebDriver)}
     * always returning false, this method will actually never be called.
     * @param ignored unused
     */
    @Override
    default CssFinder toCssWhenNativelySupported(WebDriver ignored) {
        throw new UnsupportedOperationException();
    }

}
