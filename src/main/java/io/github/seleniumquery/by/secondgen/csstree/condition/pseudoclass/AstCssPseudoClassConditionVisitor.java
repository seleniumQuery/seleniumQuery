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

import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssNotPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssAnimatedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssEqPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssEvenPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssFirstPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssGtPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssHeaderPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssLangPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssLastPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssLtPseudoClass;
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

public interface AstCssPseudoClassConditionVisitor {

    void visit(CssAnimatedPseudoClass cssAnimatedPseudoClass);
    void visit(CssEqPseudoClass cssEqPseudoClass);
    void visit(CssEvenPseudoClass cssEvenPseudoClass);
    void visit(CssFirstPseudoClass cssFirstPseudoClass);
    void visit(CssGtPseudoClass cssGtPseudoClass);
    void visit(CssHeaderPseudoClass cssHeaderPseudoClass);
    void visit(CssLangPseudoClass cssLangPseudoClass);
    void visit(CssLastPseudoClass cssLastPseudoClass);
    void visit(CssLtPseudoClass cssLtPseudoClass);
    void visit(AstCssNotPseudoClass astCssNotPseudoClass);
    void visit(CssNthPseudoClass cssNthPseudoClass);
    void visit(CssOddPseudoClass cssOddPseudoClass);
    void visit(CssRootPseudoClass cssRootPseudoClass);
    void visit(CssTargetPseudoClass cssTargetPseudoClass);

    void visit(CssFirstChildPseudoClass cssFirstChildPseudoClass);
    void visit(CssFirstOfTypePseudoClass cssFirstOfTypePseudoClass);
    void visit(CssLastChildPseudoClass cssLastChildPseudoClass);
    void visit(CssLastOfTypePseudoClass cssLastOfTypePseudoClass);
    void visit(CssNthChildPseudoClass cssNthChildPseudoClass);
    void visit(CssNthLastChildPseudoClass cssNthLastChildPseudoClass);
    void visit(CssNthLastOfTypePseudoClass cssNthLastOfTypePseudoClass);
    void visit(CssNthOfTypePseudoClass cssNthOfTypePseudoClass);
    void visit(CssOnlyChildPseudoClass cssOnlyChildPseudoClass);
    void visit(CssOnlyOfTypePseudoClass cssOnlyOfTypePseudoClass);

    void visit(CssContainsPseudoClass cssContainsPseudoClass);
    void visit(CssEmptyPseudoClass cssEmptyPseudoClass);
    void visit(CssHasPseudoClass cssHasPseudoClass);
    void visit(CssParentPseudoClass cssParentPseudoClass);

    void visit(CssButtonPseudoClass cssButtonPseudoClass);
    void visit(CssCheckboxPseudoClass cssCheckboxPseudoClass);
    void visit(CssCheckedPseudoClass cssCheckedPseudoClass);
    void visit(CssDisabledPseudoClass cssDisabledPseudoClass);
    void visit(CssInputPseudoClass cssInputPseudoClass);
    void visit(CssFilePseudoClass cssFilePseudoClass);
    void visit(CssResetPseudoClass cssResetPseudoClass);
    void visit(CssFocusPseudoClass cssFocusPseudoClass);
    void visit(CssRadioPseudoClass cssRadioPseudoClass);
    void visit(CssSelectedPseudoClass cssSelectedPseudoClass);
    void visit(CssImagePseudoClass cssImagePseudoClass);
    void visit(CssEnabledPseudoClass cssEnabledPseudoClass);
    void visit(CssPasswordPseudoClass cssPasswordPseudoClass);
    void visit(CssSubmitPseudoClass cssSubmitPseudoClass);
    void visit(CssTextPseudoClass cssTextPseudoClass);

    void visit(CssFocusablePseudoClass cssFocusablePseudoClass);
    void visit(CssTabbablePseudoClass cssTabbablePseudoClass);

    void visit(CssBlankPseudoClass cssBlankPseudoClass);
    void visit(CssFilledPseudoClass cssFilledPseudoClass);
    void visit(CssPresentPseudoClass cssPresentPseudoClass);
    void visit(CssUncheckedPseudoClass cssUncheckedPseudoClass);

    void visit(CssHiddenPseudoClass cssHiddenPseudoClass);
    void visit(CssVisiblePseudoClass cssVisiblePseudoClass);

}
