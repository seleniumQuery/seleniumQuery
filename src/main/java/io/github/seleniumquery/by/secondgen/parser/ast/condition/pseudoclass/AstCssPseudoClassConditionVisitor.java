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

public interface AstCssPseudoClassConditionVisitor {

    void visit(AstCssAnimatedPseudoClass astCssAnimatedPseudoClass);
    void visit(AstCssEqPseudoClass astCssEqPseudoClass);
    void visit(AstCssEvenPseudoClass astCssEvenPseudoClass);
    void visit(AstCssFirstPseudoClass astCssFirstPseudoClass);
    void visit(AstCssGtPseudoClass astCssGtPseudoClass);
    void visit(AstCssHeaderPseudoClass astCssHeaderPseudoClass);
    void visit(AstCssLangPseudoClass astCssLangPseudoClass);
    void visit(AstCssLastPseudoClass astCssLastPseudoClass);
    void visit(AstCssLtPseudoClass astCssLtPseudoClass);
    void visit(AstCssNotPseudoClass astCssNotPseudoClass);
    void visit(AstCssNthPseudoClass astCssNthPseudoClass);
    void visit(AstCssOddPseudoClass astCssOddPseudoClass);
    void visit(AstCssRootPseudoClass astCssRootPseudoClass);
    void visit(AstCssTargetPseudoClass astCssTargetPseudoClass);

    void visit(AstCssFirstChildPseudoClass astCssFirstChildPseudoClass);
    void visit(AstCssFirstOfTypePseudoClass astCssFirstOfTypePseudoClass);
    void visit(AstCssLastChildPseudoClass astCssLastChildPseudoClass);
    void visit(AstCssLastOfTypePseudoClass astCssLastOfTypePseudoClass);
    void visit(AstCssNthChildPseudoClass astCssNthChildPseudoClass);
    void visit(AstCssNthLastChildPseudoClass astCssNthLastChildPseudoClass);
    void visit(AstCssNthLastOfTypePseudoClass astCssNthLastOfTypePseudoClass);
    void visit(AstCssNthOfTypePseudoClass astCssNthOfTypePseudoClass);
    void visit(AstCssOnlyChildPseudoClass astCssOnlyChildPseudoClass);
    void visit(AstCssOnlyOfTypePseudoClass astCssOnlyOfTypePseudoClass);

    void visit(AstCssContainsPseudoClass astCssContainsPseudoClass);
    void visit(AstCssEmptyPseudoClass astCssEmptyPseudoClass);
    void visit(AstCssHasPseudoClass astCssHasPseudoClass);
    void visit(AstCssParentPseudoClass astCssParentPseudoClass);

    void visit(AstCssButtonPseudoClass astCssButtonPseudoClass);
    void visit(AstCssCheckboxPseudoClass astCssCheckboxPseudoClass);
    void visit(AstCssCheckedPseudoClass astCssCheckedPseudoClass);
    void visit(AstCssDisabledPseudoClass astCssDisabledPseudoClass);
    void visit(AstCssInputPseudoClass astCssInputPseudoClass);
    void visit(AstCssFilePseudoClass astCssFilePseudoClass);
    void visit(AstCssResetPseudoClass astCssResetPseudoClass);
    void visit(AstCssFocusPseudoClass astCssFocusPseudoClass);
    void visit(AstCssRadioPseudoClass astCssRadioPseudoClass);
    void visit(AstCssSelectedPseudoClass astCssSelectedPseudoClass);
    void visit(AstCssImagePseudoClass astCssImagePseudoClass);
    void visit(AstCssEnabledPseudoClass astCssEnabledPseudoClass);
    void visit(AstCssPasswordPseudoClass astCssPasswordPseudoClass);
    void visit(AstCssSubmitPseudoClass astCssSubmitPseudoClass);
    void visit(AstCssTextPseudoClass astCssTextPseudoClass);

    void visit(AstCssFocusablePseudoClass astCssFocusablePseudoClass);
    void visit(AstCssTabbablePseudoClass astCssTabbablePseudoClass);

    void visit(AstCssBlankPseudoClass astCssBlankPseudoClass);
    void visit(AstCssFilledPseudoClass astCssFilledPseudoClass);
    void visit(AstCssPresentPseudoClass astCssPresentPseudoClass);
    void visit(AstCssUncheckedPseudoClass astCssUncheckedPseudoClass);

    void visit(AstCssHiddenPseudoClass astCssHiddenPseudoClass);
    void visit(AstCssVisiblePseudoClass astCssVisiblePseudoClass);

}
