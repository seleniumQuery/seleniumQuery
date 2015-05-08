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

package io.github.seleniumquery.by.csstree.condition.pseudoclass.finderfactorystrategy;

import io.github.seleniumquery.by.csstree.condition.SQCssConditionImplementedFinders;
import io.github.seleniumquery.by.finder.CssFinder;
import org.openqa.selenium.WebDriver;

/**
 * Pseudos extending this class will never ever even check for native support.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public abstract class NeverNativelySupportedPseudoClass extends MaybeNativelySupportedPseudoClass implements SQCssConditionImplementedFinders {

    @Override
    public boolean isThisCSSPseudoClassNativelySupportedOn(WebDriver webDriver) {
        return false;
    }

    /**
     * Due to the {@link NeverNativelySupportedPseudoClass#isThisCSSPseudoClassNativelySupportedOn(org.openqa.selenium.WebDriver)}
     * always returning false, this method will actually never be called.
     * I do know this smells like a violation of LSP, but I, for the love of Yoda, couldn't figure out a better way!
     * @param webDriver ignored
     */
    @Override
    public CssFinder toCssWhenNativelySupported(WebDriver webDriver) {
        throw new UnsupportedOperationException();
    }

}