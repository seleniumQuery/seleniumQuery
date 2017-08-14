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

package io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass;

import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssAnimatedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssEqPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssEvenPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssFirstPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssGtPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssHeaderPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssLangPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssLastPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssLtPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssNotPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssNthPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssOddPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssRootPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssTargetPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssFirstChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssFirstOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssLastChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssLastOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssNthChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssNthLastChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssNthLastOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssNthOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssOnlyChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.CssOnlyOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssContainsPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssEmptyPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssHasPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.CssParentPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssButtonPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssCheckboxPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssCheckedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssDisabledPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssEnabledPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssFilePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssFocusPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssImagePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssInputPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssPasswordPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssRadioPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssResetPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssSelectedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssSubmitPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssTextPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.jqueryui.CssFocusablePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.jqueryui.CssTabbablePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssBlankPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssFilledPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssPresentPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.CssUncheckedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.visibility.CssHiddenPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.visibility.CssVisiblePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.AstCssPseudoClassConditionVisitor;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssAnimatedPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssEqPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssEvenPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssFirstPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssGtPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssHeaderPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssLangPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssLastPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssLtPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssNotPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssNthPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssOddPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssRootPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssTargetPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssFirstChildPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssFirstOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssLastChildPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssLastOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssNthChildPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssNthLastChildPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssNthLastOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssNthOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssOnlyChildPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssOnlyOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.contentfilter.AstCssContainsPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.contentfilter.AstCssEmptyPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.contentfilter.AstCssHasPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.contentfilter.AstCssParentPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssButtonPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssCheckboxPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssCheckedPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssDisabledPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssEnabledPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssFilePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssFocusPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssImagePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssInputPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssPasswordPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssRadioPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssResetPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssSelectedPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssSubmitPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssTextPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.jqueryui.AstCssFocusablePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.jqueryui.AstCssTabbablePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.seleniumquery.AstCssBlankPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.seleniumquery.AstCssFilledPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.seleniumquery.AstCssPresentPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.seleniumquery.AstCssUncheckedPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.visibility.AstCssHiddenPseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.visibility.AstCssVisiblePseudoClass;

public class CssPseudoClassConditionVisitor implements AstCssPseudoClassConditionVisitor<CssPseudoClassCondition> {

    @Override
    public CssPseudoClassCondition visit(AstCssAnimatedPseudoClass astCssAnimatedPseudoClass) {
        return new CssAnimatedPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssEqPseudoClass astCssEqPseudoClass) {
        return new CssEqPseudoClass(astCssEqPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssEvenPseudoClass astCssEvenPseudoClass) {
        return new CssEvenPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssFirstPseudoClass astCssFirstPseudoClass) {
        return new CssFirstPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssGtPseudoClass astCssGtPseudoClass) {
        return new CssGtPseudoClass(astCssGtPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssHeaderPseudoClass astCssHeaderPseudoClass) {
        return new CssHeaderPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssLangPseudoClass astCssLangPseudoClass) {
        return new CssLangPseudoClass(astCssLangPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssLastPseudoClass astCssLastPseudoClass) {
        return new CssLastPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssLtPseudoClass astCssLtPseudoClass) {
        return new CssLtPseudoClass(astCssLtPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssNotPseudoClass astCssNotPseudoClass) {
        return new CssNotPseudoClass(astCssNotPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssNthPseudoClass astCssNthPseudoClass) {
        return new CssNthPseudoClass(astCssNthPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssOddPseudoClass astCssOddPseudoClass) {
        return new CssOddPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssRootPseudoClass astCssRootPseudoClass) {
        return new CssRootPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssTargetPseudoClass astCssTargetPseudoClass) {
        return new CssTargetPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssFirstChildPseudoClass astCssFirstChildPseudoClass) {
        return new CssFirstChildPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssFirstOfTypePseudoClass astCssFirstOfTypePseudoClass) {
        return new CssFirstOfTypePseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssLastChildPseudoClass astCssLastChildPseudoClass) {
        return new CssLastChildPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssLastOfTypePseudoClass astCssLastOfTypePseudoClass) {
        return new CssLastOfTypePseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssNthChildPseudoClass astCssNthChildPseudoClass) {
        return new CssNthChildPseudoClass(astCssNthChildPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssNthLastChildPseudoClass astCssNthLastChildPseudoClass) {
        return new CssNthLastChildPseudoClass(astCssNthLastChildPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssNthLastOfTypePseudoClass astCssNthLastOfTypePseudoClass) {
        return new CssNthLastOfTypePseudoClass(astCssNthLastOfTypePseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssNthOfTypePseudoClass astCssNthOfTypePseudoClass) {
        return new CssNthOfTypePseudoClass(astCssNthOfTypePseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssOnlyChildPseudoClass astCssOnlyChildPseudoClass) {
        return new CssOnlyChildPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssOnlyOfTypePseudoClass astCssOnlyOfTypePseudoClass) {
        return new CssOnlyOfTypePseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssContainsPseudoClass astCssContainsPseudoClass) {
        return new CssContainsPseudoClass(astCssContainsPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssEmptyPseudoClass astCssEmptyPseudoClass) {
        return new CssEmptyPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssHasPseudoClass astCssHasPseudoClass) {
        return new CssHasPseudoClass(astCssHasPseudoClass);
    }

    @Override
    public CssPseudoClassCondition visit(AstCssParentPseudoClass astCssParentPseudoClass) {
        return new CssParentPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssButtonPseudoClass astCssButtonPseudoClass) {
        return new CssButtonPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssCheckboxPseudoClass astCssCheckboxPseudoClass) {
        return new CssCheckboxPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssCheckedPseudoClass astCssCheckedPseudoClass) {
        return new CssCheckedPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssDisabledPseudoClass astCssDisabledPseudoClass) {
        return new CssDisabledPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssInputPseudoClass astCssInputPseudoClass) {
        return new CssInputPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssFilePseudoClass astCssFilePseudoClass) {
        return new CssFilePseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssResetPseudoClass astCssResetPseudoClass) {
        return new CssResetPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssFocusPseudoClass astCssFocusPseudoClass) {
        return new CssFocusPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssRadioPseudoClass astCssRadioPseudoClass) {
        return new CssRadioPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssSelectedPseudoClass astCssSelectedPseudoClass) {
        return new CssSelectedPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssImagePseudoClass astCssImagePseudoClass) {
        return new CssImagePseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssEnabledPseudoClass astCssEnabledPseudoClass) {
        return new CssEnabledPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssPasswordPseudoClass astCssPasswordPseudoClass) {
        return new CssPasswordPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssSubmitPseudoClass astCssSubmitPseudoClass) {
        return new CssSubmitPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssTextPseudoClass astCssTextPseudoClass) {
        return new CssTextPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssFocusablePseudoClass astCssFocusablePseudoClass) {
        return new CssFocusablePseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssTabbablePseudoClass astCssTabbablePseudoClass) {
        return new CssTabbablePseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssBlankPseudoClass astCssBlankPseudoClass) {
        return new CssBlankPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssFilledPseudoClass astCssFilledPseudoClass) {
        return new CssFilledPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssPresentPseudoClass astCssPresentPseudoClass) {
        return new CssPresentPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssUncheckedPseudoClass astCssUncheckedPseudoClass) {
        return new CssUncheckedPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssHiddenPseudoClass astCssHiddenPseudoClass) {
        return new CssHiddenPseudoClass();
    }

    @Override
    public CssPseudoClassCondition visit(AstCssVisiblePseudoClass astCssVisiblePseudoClass) {
        return new CssVisiblePseudoClass();
    }

}
