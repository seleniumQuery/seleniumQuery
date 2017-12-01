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

package io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass;

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

public interface AstCssPseudoClassConditionVisitor<T> {

    T visit(AstCssAnimatedPseudoClass astCssAnimatedPseudoClass);
    T visit(AstCssEqPseudoClass astCssEqPseudoClass);
    T visit(AstCssEvenPseudoClass astCssEvenPseudoClass);
    T visit(AstCssFirstPseudoClass astCssFirstPseudoClass);
    T visit(AstCssGtPseudoClass astCssGtPseudoClass);
    T visit(AstCssHeaderPseudoClass astCssHeaderPseudoClass);
    T visit(AstCssLangPseudoClass astCssLangPseudoClass);
    T visit(AstCssLastPseudoClass astCssLastPseudoClass);
    T visit(AstCssLtPseudoClass astCssLtPseudoClass);
    T visit(AstCssNotPseudoClass astCssNotPseudoClass);
    T visit(AstCssNthPseudoClass astCssNthPseudoClass);
    T visit(AstCssOddPseudoClass astCssOddPseudoClass);
    T visit(AstCssRootPseudoClass astCssRootPseudoClass);
    T visit(AstCssTargetPseudoClass astCssTargetPseudoClass);

    T visit(AstCssFirstChildPseudoClass astCssFirstChildPseudoClass);
    T visit(AstCssFirstOfTypePseudoClass astCssFirstOfTypePseudoClass);
    T visit(AstCssLastChildPseudoClass astCssLastChildPseudoClass);
    T visit(AstCssLastOfTypePseudoClass astCssLastOfTypePseudoClass);
    T visit(AstCssNthChildPseudoClass astCssNthChildPseudoClass);
    T visit(AstCssNthLastChildPseudoClass astCssNthLastChildPseudoClass);
    T visit(AstCssNthLastOfTypePseudoClass astCssNthLastOfTypePseudoClass);
    T visit(AstCssNthOfTypePseudoClass astCssNthOfTypePseudoClass);
    T visit(AstCssOnlyChildPseudoClass astCssOnlyChildPseudoClass);
    T visit(AstCssOnlyOfTypePseudoClass astCssOnlyOfTypePseudoClass);

    T visit(AstCssContainsPseudoClass astCssContainsPseudoClass);
    T visit(AstCssEmptyPseudoClass astCssEmptyPseudoClass);
    T visit(AstCssHasPseudoClass astCssHasPseudoClass);
    T visit(AstCssParentPseudoClass astCssParentPseudoClass);

    T visit(AstCssButtonPseudoClass astCssButtonPseudoClass);
    T visit(AstCssCheckboxPseudoClass astCssCheckboxPseudoClass);
    T visit(AstCssCheckedPseudoClass astCssCheckedPseudoClass);
    T visit(AstCssDisabledPseudoClass astCssDisabledPseudoClass);
    T visit(AstCssInputPseudoClass astCssInputPseudoClass);
    T visit(AstCssFilePseudoClass astCssFilePseudoClass);
    T visit(AstCssResetPseudoClass astCssResetPseudoClass);
    T visit(AstCssFocusPseudoClass astCssFocusPseudoClass);
    T visit(AstCssRadioPseudoClass astCssRadioPseudoClass);
    T visit(AstCssSelectedPseudoClass astCssSelectedPseudoClass);
    T visit(AstCssImagePseudoClass astCssImagePseudoClass);
    T visit(AstCssEnabledPseudoClass astCssEnabledPseudoClass);
    T visit(AstCssPasswordPseudoClass astCssPasswordPseudoClass);
    T visit(AstCssSubmitPseudoClass astCssSubmitPseudoClass);
    T visit(AstCssTextPseudoClass astCssTextPseudoClass);

    T visit(AstCssFocusablePseudoClass astCssFocusablePseudoClass);
    T visit(AstCssTabbablePseudoClass astCssTabbablePseudoClass);

    T visit(AstCssBlankPseudoClass astCssBlankPseudoClass);
    T visit(AstCssFilledPseudoClass astCssFilledPseudoClass);
    T visit(AstCssPresentPseudoClass astCssPresentPseudoClass);
    T visit(AstCssUncheckedPseudoClass astCssUncheckedPseudoClass);

    T visit(AstCssHiddenPseudoClass astCssHiddenPseudoClass);
    T visit(AstCssVisiblePseudoClass astCssVisiblePseudoClass);

}
