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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

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
import io.github.seleniumquery.by.secondgen.parser.ParseTreeBuilder;

public class CssPseudoClassConditionVisitorTest {

    class CssPseudoClassConditionVisitorMock implements CssPseudoClassConditionVisitor {
        private Class<? extends CssPseudoClassCondition> visitedClass;
        private CssPseudoClassCondition visitedInstance;

        Class<? extends CssPseudoClassCondition> getVisitedClass() { return visitedClass; }
        CssPseudoClassCondition getVisitedInstance() { return visitedInstance; }

        void registerVisit(Class<? extends CssPseudoClassCondition> visitedClass, CssPseudoClassCondition visited) {
            if (this.visitedClass != null) fail("Visitor has already registered a visit: " + this.visitedClass);
            this.visitedClass = visitedClass;
            this.visitedInstance = visited;
        }

        @Override public void visit(CssAnimatedPseudoClass cssAnimatedPseudoClass) { registerVisit(CssAnimatedPseudoClass.class, cssAnimatedPseudoClass); }
        @Override public void visit(CssEqPseudoClass cssEqPseudoClass) { registerVisit(CssEqPseudoClass.class, cssEqPseudoClass); }
        @Override public void visit(CssEvenPseudoClass cssEvenPseudoClass) { registerVisit(CssEvenPseudoClass.class, cssEvenPseudoClass); }
        @Override public void visit(CssFirstPseudoClass cssFirstPseudoClass) { registerVisit(CssFirstPseudoClass.class, cssFirstPseudoClass); }
        @Override public void visit(CssGtPseudoClass cssGtPseudoClass) { registerVisit(CssGtPseudoClass.class, cssGtPseudoClass); }
        @Override public void visit(CssHeaderPseudoClass cssHeaderPseudoClass) { registerVisit(CssHeaderPseudoClass.class, cssHeaderPseudoClass); }
        @Override public void visit(CssLangPseudoClass cssLangPseudoClass) { registerVisit(CssLangPseudoClass.class, cssLangPseudoClass); }
        @Override public void visit(CssLastPseudoClass cssLastPseudoClass) { registerVisit(CssLastPseudoClass.class, cssLastPseudoClass); }
        @Override public void visit(CssLtPseudoClass cssLtPseudoClass) { registerVisit(CssLtPseudoClass.class, cssLtPseudoClass); }
        @Override public void visit(CssNotPseudoClass cssNotPseudoClass) { registerVisit(CssNotPseudoClass.class, cssNotPseudoClass); }
        @Override public void visit(CssNthPseudoClass cssNthPseudoClass) { registerVisit(CssNthPseudoClass.class, cssNthPseudoClass); }
        @Override public void visit(CssOddPseudoClass cssOddPseudoClass) { registerVisit(CssOddPseudoClass.class, cssOddPseudoClass); }
        @Override public void visit(CssRootPseudoClass cssRootPseudoClass) { registerVisit(CssRootPseudoClass.class, cssRootPseudoClass); }
        @Override public void visit(CssTargetPseudoClass cssTargetPseudoClass) { registerVisit(CssTargetPseudoClass.class, cssTargetPseudoClass); }
        @Override public void visit(CssFirstChildPseudoClass cssFirstChildPseudoClass) { registerVisit(CssFirstChildPseudoClass.class, cssFirstChildPseudoClass); }
        @Override public void visit(CssFirstOfTypePseudoClass cssFirstOfTypePseudoClass) { registerVisit(CssFirstOfTypePseudoClass.class, cssFirstOfTypePseudoClass); }
        @Override public void visit(CssLastChildPseudoClass cssLastChildPseudoClass) { registerVisit(CssLastChildPseudoClass.class, cssLastChildPseudoClass); }
        @Override public void visit(CssLastOfTypePseudoClass cssLastOfTypePseudoClass) { registerVisit(CssLastOfTypePseudoClass.class, cssLastOfTypePseudoClass); }
        @Override public void visit(CssNthChildPseudoClass cssNthChildPseudoClass) { registerVisit(CssNthChildPseudoClass.class, cssNthChildPseudoClass); }
        @Override public void visit(CssNthLastChildPseudoClass cssNthLastChildPseudoClass) { registerVisit(CssNthLastChildPseudoClass.class, cssNthLastChildPseudoClass); }
        @Override public void visit(CssNthLastOfTypePseudoClass cssNthLastOfTypePseudoClass) { registerVisit(CssNthLastOfTypePseudoClass.class, cssNthLastOfTypePseudoClass); }
        @Override public void visit(CssNthOfTypePseudoClass cssNthOfTypePseudoClass) { registerVisit(CssNthOfTypePseudoClass.class, cssNthOfTypePseudoClass); }
        @Override public void visit(CssOnlyChildPseudoClass cssOnlyChildPseudoClass) { registerVisit(CssOnlyChildPseudoClass.class, cssOnlyChildPseudoClass); }
        @Override public void visit(CssOnlyOfTypePseudoClass cssOnlyOfTypePseudoClass) { registerVisit(CssOnlyOfTypePseudoClass.class, cssOnlyOfTypePseudoClass); }
        @Override public void visit(CssContainsPseudoClass cssContainsPseudoClass) { registerVisit(CssContainsPseudoClass.class, cssContainsPseudoClass); }
        @Override public void visit(CssEmptyPseudoClass cssEmptyPseudoClass) { registerVisit(CssEmptyPseudoClass.class, cssEmptyPseudoClass); }
        @Override public void visit(CssHasPseudoClass cssHasPseudoClass) { registerVisit(CssHasPseudoClass.class, cssHasPseudoClass); }
        @Override public void visit(CssParentPseudoClass cssParentPseudoClass) { registerVisit(CssParentPseudoClass.class, cssParentPseudoClass); }
        @Override public void visit(CssButtonPseudoClass cssButtonPseudoClass) { registerVisit(CssButtonPseudoClass.class, cssButtonPseudoClass); }
        @Override public void visit(CssCheckboxPseudoClass cssCheckboxPseudoClass) { registerVisit(CssCheckboxPseudoClass.class, cssCheckboxPseudoClass); }
        @Override public void visit(CssCheckedPseudoClass cssCheckedPseudoClass) { registerVisit(CssCheckedPseudoClass.class, cssCheckedPseudoClass); }
        @Override public void visit(CssDisabledPseudoClass cssDisabledPseudoClass) { registerVisit(CssDisabledPseudoClass.class, cssDisabledPseudoClass); }
        @Override public void visit(CssInputPseudoClass cssInputPseudoClass) { registerVisit(CssInputPseudoClass.class, cssInputPseudoClass); }
        @Override public void visit(CssFilePseudoClass cssFilePseudoClass) { registerVisit(CssFilePseudoClass.class, cssFilePseudoClass); }
        @Override public void visit(CssResetPseudoClass cssResetPseudoClass) { registerVisit(CssResetPseudoClass.class, cssResetPseudoClass); }
        @Override public void visit(CssFocusPseudoClass cssFocusPseudoClass) { registerVisit(CssFocusPseudoClass.class, cssFocusPseudoClass); }
        @Override public void visit(CssRadioPseudoClass cssRadioPseudoClass) { registerVisit(CssRadioPseudoClass.class, cssRadioPseudoClass); }
        @Override public void visit(CssSelectedPseudoClass cssSelectedPseudoClass) { registerVisit(CssSelectedPseudoClass.class, cssSelectedPseudoClass); }
        @Override public void visit(CssImagePseudoClass cssImagePseudoClass) { registerVisit(CssImagePseudoClass.class, cssImagePseudoClass); }
        @Override public void visit(CssEnabledPseudoClass cssEnabledPseudoClass) { registerVisit(CssEnabledPseudoClass.class, cssEnabledPseudoClass); }
        @Override public void visit(CssPasswordPseudoClass cssPasswordPseudoClass) { registerVisit(CssPasswordPseudoClass.class, cssPasswordPseudoClass); }
        @Override public void visit(CssSubmitPseudoClass cssSubmitPseudoClass) { registerVisit(CssSubmitPseudoClass.class, cssSubmitPseudoClass); }
        @Override public void visit(CssTextPseudoClass cssTextPseudoClass) { registerVisit(CssTextPseudoClass.class, cssTextPseudoClass); }
        @Override public void visit(CssFocusablePseudoClass cssFocusablePseudoClass) { registerVisit(CssFocusablePseudoClass.class, cssFocusablePseudoClass); }
        @Override public void visit(CssTabbablePseudoClass cssTabbablePseudoClass) { registerVisit(CssTabbablePseudoClass.class, cssTabbablePseudoClass); }
        @Override public void visit(CssBlankPseudoClass cssBlankPseudoClass) { registerVisit(CssBlankPseudoClass.class, cssBlankPseudoClass); }
        @Override public void visit(CssFilledPseudoClass cssFilledPseudoClass) { registerVisit(CssFilledPseudoClass.class, cssFilledPseudoClass); }
        @Override public void visit(CssPresentPseudoClass cssPresentPseudoClass) { registerVisit(CssPresentPseudoClass.class, cssPresentPseudoClass); }
        @Override public void visit(CssUncheckedPseudoClass cssUncheckedPseudoClass) { registerVisit(CssUncheckedPseudoClass.class, cssUncheckedPseudoClass); }
        @Override public void visit(CssHiddenPseudoClass cssHiddenPseudoClass) { registerVisit(CssHiddenPseudoClass.class, cssHiddenPseudoClass); }
        @Override public void visit(CssVisiblePseudoClass cssVisiblePseudoClass) { registerVisit(CssVisiblePseudoClass.class, cssVisiblePseudoClass); }
    }

    private CssPseudoClassConditionVisitorMock visitor = new CssPseudoClassConditionVisitorMock();

    @Test
    public void visitCssAnimatedPseudoClass() {
        // given
        CssAnimatedPseudoClass cssAnimatedPseudoClass = new CssAnimatedPseudoClass();
        // when
        cssAnimatedPseudoClass.accept(visitor);
        // then
        assertEquals(CssAnimatedPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssAnimatedPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssEqPseudoClass() {
        // given
        CssEqPseudoClass cssEqPseudoClass = new CssEqPseudoClass("99");
        // when
        cssEqPseudoClass.accept(visitor);
        // then
        assertEquals(CssEqPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssEqPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssEvenPseudoClass() {
        // given
        CssEvenPseudoClass cssEvenPseudoClass = new CssEvenPseudoClass();
        // when
        cssEvenPseudoClass.accept(visitor);
        // then
        assertEquals(CssEvenPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssEvenPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssFirstPseudoClass() {
        // given
        CssFirstPseudoClass cssFirstPseudoClass = new CssFirstPseudoClass();
        // when
        cssFirstPseudoClass.accept(visitor);
        // then
        assertEquals(CssFirstPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssFirstPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssGtPseudoClass() {
        // given
        CssGtPseudoClass cssGtPseudoClass = new CssGtPseudoClass("99");
        // when
        cssGtPseudoClass.accept(visitor);
        // then
        assertEquals(CssGtPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssGtPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssHeaderPseudoClass() {
        // given
        CssHeaderPseudoClass cssHeaderPseudoClass = new CssHeaderPseudoClass();
        // when
        cssHeaderPseudoClass.accept(visitor);
        // then
        assertEquals(CssHeaderPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssHeaderPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssLangPseudoClass() {
        // given
        CssLangPseudoClass cssLangPseudoClass = new CssLangPseudoClass("br");
        // when
        cssLangPseudoClass.accept(visitor);
        // then
        assertEquals(CssLangPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssLangPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssLastPseudoClass() {
        // given
        CssLastPseudoClass cssLastPseudoClass = new CssLastPseudoClass();
        // when
        cssLastPseudoClass.accept(visitor);
        // then
        assertEquals(CssLastPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssLastPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssLtPseudoClass() {
        // given
        CssLtPseudoClass cssLtPseudoClass = new CssLtPseudoClass("55");
        // when
        cssLtPseudoClass.accept(visitor);
        // then
        assertEquals(CssLtPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssLtPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssNotPseudoClass() {
        // given
        CssNotPseudoClass cssNotPseudoClass = new CssNotPseudoClass(ParseTreeBuilder.parse(".class"));
        // when
        cssNotPseudoClass.accept(visitor);
        // then
        assertEquals(CssNotPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssNotPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssNthPseudoClass() {
        // given
        CssNthPseudoClass cssNthPseudoClass = new CssNthPseudoClass("55");
        // when
        cssNthPseudoClass.accept(visitor);
        // then
        assertEquals(CssNthPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssNthPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssOddPseudoClass() {
        // given
        CssOddPseudoClass cssOddPseudoClass = new CssOddPseudoClass();
        // when
        cssOddPseudoClass.accept(visitor);
        // then
        assertEquals(CssOddPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssOddPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssRootPseudoClass() {
        // given
        CssRootPseudoClass cssRootPseudoClass = new CssRootPseudoClass();
        // when
        cssRootPseudoClass.accept(visitor);
        // then
        assertEquals(CssRootPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssRootPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssTargetPseudoClass() {
        // given
        CssTargetPseudoClass cssTargetPseudoClass = new CssTargetPseudoClass();
        // when
        cssTargetPseudoClass.accept(visitor);
        // then
        assertEquals(CssTargetPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssTargetPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssFirstChildPseudoClass() {
        // given
        CssFirstChildPseudoClass cssFirstChildPseudoClass = new CssFirstChildPseudoClass();
        // when
        cssFirstChildPseudoClass.accept(visitor);
        // then
        assertEquals(CssFirstChildPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssFirstChildPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssFirstOfTypePseudoClass() {
        // given
        CssFirstOfTypePseudoClass cssFirstOfTypePseudoClass = new CssFirstOfTypePseudoClass();
        // when
        cssFirstOfTypePseudoClass.accept(visitor);
        // then
        assertEquals(CssFirstOfTypePseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssFirstOfTypePseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssLastChildPseudoClass() {
        // given
        CssLastChildPseudoClass cssLastChildPseudoClass = new CssLastChildPseudoClass();
        // when
        cssLastChildPseudoClass.accept(visitor);
        // then
        assertEquals(CssLastChildPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssLastChildPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssLastOfTypePseudoClass() {
        // given
        CssLastOfTypePseudoClass cssLastOfTypePseudoClass = new CssLastOfTypePseudoClass();
        // when
        cssLastOfTypePseudoClass.accept(visitor);
        // then
        assertEquals(CssLastOfTypePseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssLastOfTypePseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssNthChildPseudoClass() {
        // given
        CssNthChildPseudoClass cssNthChildPseudoClass = new CssNthChildPseudoClass("66");
        // when
        cssNthChildPseudoClass.accept(visitor);
        // then
        assertEquals(CssNthChildPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssNthChildPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssNthLastChildPseudoClass() {
        // given
        CssNthLastChildPseudoClass cssNthLastChildPseudoClass = new CssNthLastChildPseudoClass("77");
        // when
        cssNthLastChildPseudoClass.accept(visitor);
        // then
        assertEquals(CssNthLastChildPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssNthLastChildPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssNthLastOfTypePseudoClass() {
        // given
        CssNthLastOfTypePseudoClass cssNthLastOfTypePseudoClass = new CssNthLastOfTypePseudoClass("33");
        // when
        cssNthLastOfTypePseudoClass.accept(visitor);
        // then
        assertEquals(CssNthLastOfTypePseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssNthLastOfTypePseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssNthOfTypePseudoClass() {
        // given
        CssNthOfTypePseudoClass cssNthOfTypePseudoClass = new CssNthOfTypePseudoClass("88");
        // when
        cssNthOfTypePseudoClass.accept(visitor);
        // then
        assertEquals(CssNthOfTypePseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssNthOfTypePseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssOnlyChildPseudoClass() {
        // given
        CssOnlyChildPseudoClass cssOnlyChildPseudoClass = new CssOnlyChildPseudoClass();
        // when
        cssOnlyChildPseudoClass.accept(visitor);
        // then
        assertEquals(CssOnlyChildPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssOnlyChildPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssOnlyOfTypePseudoClass() {
        // given
        CssOnlyOfTypePseudoClass cssOnlyOfTypePseudoClass = new CssOnlyOfTypePseudoClass();
        // when
        cssOnlyOfTypePseudoClass.accept(visitor);
        // then
        assertEquals(CssOnlyOfTypePseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssOnlyOfTypePseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssContainsPseudoClass() {
        // given
        CssContainsPseudoClass cssContainsPseudoClass = new CssContainsPseudoClass("stuff");
        // when
        cssContainsPseudoClass.accept(visitor);
        // then
        assertEquals(CssContainsPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssContainsPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssEmptyPseudoClass() {
        // given
        CssEmptyPseudoClass cssEmptyPseudoClass = new CssEmptyPseudoClass();
        // when
        cssEmptyPseudoClass.accept(visitor);
        // then
        assertEquals(CssEmptyPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssEmptyPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssHasPseudoClass() {
        // given
        CssHasPseudoClass cssHasPseudoClass = new CssHasPseudoClass(ParseTreeBuilder.parse(".class"));
        // when
        cssHasPseudoClass.accept(visitor);
        // then
        assertEquals(CssHasPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssHasPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssParentPseudoClass() {
        // given
        CssParentPseudoClass cssParentPseudoClass = new CssParentPseudoClass();
        // when
        cssParentPseudoClass.accept(visitor);
        // then
        assertEquals(CssParentPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssParentPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssButtonPseudoClass() {
        // given
        CssButtonPseudoClass cssButtonPseudoClass = new CssButtonPseudoClass();
        // when
        cssButtonPseudoClass.accept(visitor);
        // then
        assertEquals(CssButtonPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssButtonPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssCheckboxPseudoClass() {
        // given
        CssCheckboxPseudoClass cssCheckboxPseudoClass = new CssCheckboxPseudoClass();
        // when
        cssCheckboxPseudoClass.accept(visitor);
        // then
        assertEquals(CssCheckboxPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssCheckboxPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssCheckedPseudoClass() {
        // given
        CssCheckedPseudoClass cssCheckedPseudoClass = new CssCheckedPseudoClass();
        // when
        cssCheckedPseudoClass.accept(visitor);
        // then
        assertEquals(CssCheckedPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssCheckedPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssDisabledPseudoClass() {
        // given
        CssDisabledPseudoClass cssDisabledPseudoClass = new CssDisabledPseudoClass();
        // when
        cssDisabledPseudoClass.accept(visitor);
        // then
        assertEquals(CssDisabledPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssDisabledPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssInputPseudoClass() {
        // given
        CssInputPseudoClass cssInputPseudoClass = new CssInputPseudoClass();
        // when
        cssInputPseudoClass.accept(visitor);
        // then
        assertEquals(CssInputPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssInputPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssFilePseudoClass() {
        // given
        CssFilePseudoClass cssFilePseudoClass = new CssFilePseudoClass();
        // when
        cssFilePseudoClass.accept(visitor);
        // then
        assertEquals(CssFilePseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssFilePseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssResetPseudoClass() {
        // given
        CssResetPseudoClass cssResetPseudoClass = new CssResetPseudoClass();
        // when
        cssResetPseudoClass.accept(visitor);
        // then
        assertEquals(CssResetPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssResetPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssFocusPseudoClass() {
        // given
        CssFocusPseudoClass cssFocusPseudoClass = new CssFocusPseudoClass();
        // when
        cssFocusPseudoClass.accept(visitor);
        // then
        assertEquals(CssFocusPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssFocusPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssRadioPseudoClass() {
        // given
        CssRadioPseudoClass cssRadioPseudoClass = new CssRadioPseudoClass();
        // when
        cssRadioPseudoClass.accept(visitor);
        // then
        assertEquals(CssRadioPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssRadioPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssSelectedPseudoClass() {
        // given
        CssSelectedPseudoClass cssSelectedPseudoClass = new CssSelectedPseudoClass();
        // when
        cssSelectedPseudoClass.accept(visitor);
        // then
        assertEquals(CssSelectedPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssSelectedPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssImagePseudoClass() {
        // given
        CssImagePseudoClass cssImagePseudoClass = new CssImagePseudoClass();
        // when
        cssImagePseudoClass.accept(visitor);
        // then
        assertEquals(CssImagePseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssImagePseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssEnabledPseudoClass() {
        // given
        CssEnabledPseudoClass cssEnabledPseudoClass = new CssEnabledPseudoClass();
        // when
        cssEnabledPseudoClass.accept(visitor);
        // then
        assertEquals(CssEnabledPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssEnabledPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssPasswordPseudoClass() {
        // given
        CssPasswordPseudoClass cssPasswordPseudoClass = new CssPasswordPseudoClass();
        // when
        cssPasswordPseudoClass.accept(visitor);
        // then
        assertEquals(CssPasswordPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssPasswordPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssSubmitPseudoClass() {
        // given
        CssSubmitPseudoClass cssSubmitPseudoClass = new CssSubmitPseudoClass();
        // when
        cssSubmitPseudoClass.accept(visitor);
        // then
        assertEquals(CssSubmitPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssSubmitPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssTextPseudoClass() {
        // given
        CssTextPseudoClass cssTextPseudoClass = new CssTextPseudoClass();
        // when
        cssTextPseudoClass.accept(visitor);
        // then
        assertEquals(CssTextPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssTextPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssFocusablePseudoClass() {
        // given
        CssFocusablePseudoClass cssFocusablePseudoClass = new CssFocusablePseudoClass();
        // when
        cssFocusablePseudoClass.accept(visitor);
        // then
        assertEquals(CssFocusablePseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssFocusablePseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssTabbablePseudoClass() {
        // given
        CssTabbablePseudoClass cssTabbablePseudoClass = new CssTabbablePseudoClass();
        // when
        cssTabbablePseudoClass.accept(visitor);
        // then
        assertEquals(CssTabbablePseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssTabbablePseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssBlankPseudoClass() {
        // given
        CssBlankPseudoClass cssBlankPseudoClass = new CssBlankPseudoClass();
        // when
        cssBlankPseudoClass.accept(visitor);
        // then
        assertEquals(CssBlankPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssBlankPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssFilledPseudoClass() {
        // given
        CssFilledPseudoClass cssFilledPseudoClass = new CssFilledPseudoClass();
        // when
        cssFilledPseudoClass.accept(visitor);
        // then
        assertEquals(CssFilledPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssFilledPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssPresentPseudoClass() {
        // given
        CssPresentPseudoClass cssPresentPseudoClass = new CssPresentPseudoClass();
        // when
        cssPresentPseudoClass.accept(visitor);
        // then
        assertEquals(CssPresentPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssPresentPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssUncheckedPseudoClass() {
        // given
        CssUncheckedPseudoClass cssUncheckedPseudoClass = new CssUncheckedPseudoClass();
        // when
        cssUncheckedPseudoClass.accept(visitor);
        // then
        assertEquals(CssUncheckedPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssUncheckedPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssHiddenPseudoClass() {
        // given
        CssHiddenPseudoClass cssHiddenPseudoClass = new CssHiddenPseudoClass();
        // when
        cssHiddenPseudoClass.accept(visitor);
        // then
        assertEquals(CssHiddenPseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssHiddenPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssVisiblePseudoClass() {
        // given
        CssVisiblePseudoClass cssVisiblePseudoClass = new CssVisiblePseudoClass();
        // when
        cssVisiblePseudoClass.accept(visitor);
        // then
        assertEquals(CssVisiblePseudoClass.class, visitor.getVisitedClass());
        assertEquals(cssVisiblePseudoClass, visitor.getVisitedInstance());
    }

}
