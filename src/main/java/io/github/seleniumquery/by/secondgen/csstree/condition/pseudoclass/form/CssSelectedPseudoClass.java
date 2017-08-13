/*
 * Copyright (c) 2017 seleniumQuery authors
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

import org.openqa.selenium.WebDriver;

import io.github.seleniumquery.by.firstgen.css.pseudoclasses.SelectedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.AstCssPseudoClassCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.AstCssPseudoClassConditionVisitor;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.finderfactorystrategy.MaybeNativelySupportedPseudoClass;
import io.github.seleniumquery.by.secondgen.finder.CssFinder;
import io.github.seleniumquery.by.secondgen.finder.XPathAndFilterFinder;

/**
 * :selected
 * https://api.jquery.com/selected-selector/
 *
 * :selected is a "maybe natively supported" because it can be translated into option:checked (but
 * only if the browser supports - without bugs - the :checked pseudo).
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssSelectedPseudoClass implements AstCssPseudoClassCondition, MaybeNativelySupportedPseudoClass {

    public static final String PSEUDO = "selected";

    @Override
    public void accept(AstCssPseudoClassConditionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isThisCSSPseudoClassNativelySupportedOn(WebDriver webDriver) {
        return CssCheckedPseudoClass.isDriverWhereCheckedSelectorHasNoBugs(webDriver)
                && MaybeNativelySupportedPseudoClass.super.isThisCSSPseudoClassNativelySupportedOn(webDriver);
    }

    @Override
    public String pseudoClassForCSSNativeSupportCheck(WebDriver webDriver) {
        return CssCheckedPseudoClass.CHECKED_PSEUDO;
    }

    @Override
    public CssFinder toCssWhenNativelySupported(WebDriver webDriver) {
        return new CssFinder("option", ":checked");
    }

    @Override
    public XPathAndFilterFinder toXPath(WebDriver webDriver) {
        return new XPathAndFilterFinder("self::option", SelectedPseudoClass.SELECTED_FILTER);
    }

}
