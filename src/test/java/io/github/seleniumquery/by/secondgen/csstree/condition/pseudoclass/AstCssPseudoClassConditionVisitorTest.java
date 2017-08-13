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

import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssAnimatedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssEqPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssEvenPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssFirstPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssGtPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssHeaderPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssLangPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssLastPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssLtPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssNotPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssNthPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssOddPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssRootPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.AstCssTargetPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssAnimatedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssEvenPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssGtPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssHeaderPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssLangPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssLtPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssOddPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssRootPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter.CssTargetPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssFirstChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssFirstOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssLastChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssLastOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssNthChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssNthLastChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssNthLastOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssNthOfTypePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssOnlyChildPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter.AstCssOnlyOfTypePseudoClass;
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
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.AstCssContainsPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.AstCssEmptyPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.AstCssHasPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter.AstCssParentPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssButtonPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssCheckboxPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssCheckedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssDisabledPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssEnabledPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssFilePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssFocusPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssImagePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssInputPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssPasswordPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssRadioPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssResetPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssSelectedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssSubmitPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.AstCssTextPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.jqueryui.AstCssFocusablePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.jqueryui.AstCssTabbablePseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.AstCssBlankPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.AstCssFilledPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.AstCssPresentPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery.AstCssUncheckedPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.visibility.AstCssHiddenPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.visibility.AstCssVisiblePseudoClass;
import io.github.seleniumquery.by.secondgen.parser.ParseTreeBuilder;

public class AstCssPseudoClassConditionVisitorTest {

    class AstCssPseudoClassConditionVisitorMock implements AstCssPseudoClassConditionVisitor {
        private Class<? extends AstCssPseudoClassCondition> visitedClass;
        private AstCssPseudoClassCondition visitedInstance;

        Class<? extends AstCssPseudoClassCondition> getVisitedClass() { return visitedClass; }
        AstCssPseudoClassCondition getVisitedInstance() { return visitedInstance; }

        void registerVisit(Class<? extends AstCssPseudoClassCondition> visitedClass, AstCssPseudoClassCondition visited) {
            if (this.visitedClass != null) fail("Visitor has already registered a visit: " + this.visitedClass);
            this.visitedClass = visitedClass;
            this.visitedInstance = visited;
        }

        @Override public void visit(AstCssAnimatedPseudoClass astCssAnimatedPseudoClass) { registerVisit(CssAnimatedPseudoClass.class, astCssAnimatedPseudoClass); }
        @Override public void visit(AstCssEqPseudoClass astCssEqPseudoClass) { registerVisit(AstCssEqPseudoClass.class, astCssEqPseudoClass); }
        @Override public void visit(AstCssEvenPseudoClass astCssEvenPseudoClass) { registerVisit(CssEvenPseudoClass.class, astCssEvenPseudoClass); }
        @Override public void visit(AstCssFirstPseudoClass astCssFirstPseudoClass) { registerVisit(AstCssFirstPseudoClass.class, astCssFirstPseudoClass); }
        @Override public void visit(AstCssGtPseudoClass astCssGtPseudoClass) { registerVisit(CssGtPseudoClass.class,
            astCssGtPseudoClass); }
        @Override public void visit(AstCssHeaderPseudoClass astCssHeaderPseudoClass) { registerVisit(CssHeaderPseudoClass.class, astCssHeaderPseudoClass); }
        @Override public void visit(AstCssLangPseudoClass astCssLangPseudoClass) { registerVisit(CssLangPseudoClass.class,
            astCssLangPseudoClass); }
        @Override public void visit(AstCssLastPseudoClass astCssLastPseudoClass) { registerVisit(AstCssLastPseudoClass.class, astCssLastPseudoClass); }
        @Override public void visit(AstCssLtPseudoClass astCssLtPseudoClass) { registerVisit(CssLtPseudoClass.class,
            astCssLtPseudoClass); }
        @Override public void visit(AstCssNotPseudoClass astCssNotPseudoClass) { registerVisit(AstCssNotPseudoClass.class, astCssNotPseudoClass); }
        @Override public void visit(AstCssNthPseudoClass astCssNthPseudoClass) { registerVisit(AstCssNthPseudoClass.class, astCssNthPseudoClass); }
        @Override public void visit(AstCssOddPseudoClass astCssOddPseudoClass) { registerVisit(CssOddPseudoClass.class,
            astCssOddPseudoClass); }
        @Override public void visit(AstCssRootPseudoClass astCssRootPseudoClass) { registerVisit(CssRootPseudoClass.class,
            astCssRootPseudoClass); }
        @Override public void visit(AstCssTargetPseudoClass astCssTargetPseudoClass) { registerVisit(CssTargetPseudoClass.class, astCssTargetPseudoClass); }
        @Override public void visit(AstCssFirstChildPseudoClass astCssFirstChildPseudoClass) { registerVisit(CssFirstChildPseudoClass.class, astCssFirstChildPseudoClass); }
        @Override public void visit(AstCssFirstOfTypePseudoClass astCssFirstOfTypePseudoClass) { registerVisit(CssFirstOfTypePseudoClass.class, astCssFirstOfTypePseudoClass); }
        @Override public void visit(AstCssLastChildPseudoClass astCssLastChildPseudoClass) { registerVisit(CssLastChildPseudoClass.class, astCssLastChildPseudoClass); }
        @Override public void visit(AstCssLastOfTypePseudoClass astCssLastOfTypePseudoClass) { registerVisit(CssLastOfTypePseudoClass.class, astCssLastOfTypePseudoClass); }
        @Override public void visit(AstCssNthChildPseudoClass astCssNthChildPseudoClass) { registerVisit(CssNthChildPseudoClass.class, astCssNthChildPseudoClass); }
        @Override public void visit(AstCssNthLastChildPseudoClass astCssNthLastChildPseudoClass) { registerVisit(CssNthLastChildPseudoClass.class, astCssNthLastChildPseudoClass); }
        @Override public void visit(AstCssNthLastOfTypePseudoClass astCssNthLastOfTypePseudoClass) { registerVisit(CssNthLastOfTypePseudoClass.class, astCssNthLastOfTypePseudoClass); }
        @Override public void visit(AstCssNthOfTypePseudoClass astCssNthOfTypePseudoClass) { registerVisit(CssNthOfTypePseudoClass.class, astCssNthOfTypePseudoClass); }
        @Override public void visit(AstCssOnlyChildPseudoClass astCssOnlyChildPseudoClass) { registerVisit(CssOnlyChildPseudoClass.class, astCssOnlyChildPseudoClass); }
        @Override public void visit(AstCssOnlyOfTypePseudoClass astCssOnlyOfTypePseudoClass) { registerVisit(CssOnlyOfTypePseudoClass.class, astCssOnlyOfTypePseudoClass); }
        @Override public void visit(AstCssContainsPseudoClass astCssContainsPseudoClass) { registerVisit(AstCssContainsPseudoClass.class, astCssContainsPseudoClass); }
        @Override public void visit(AstCssEmptyPseudoClass astCssEmptyPseudoClass) { registerVisit(AstCssEmptyPseudoClass.class, astCssEmptyPseudoClass); }
        @Override public void visit(AstCssHasPseudoClass astCssHasPseudoClass) { registerVisit(AstCssHasPseudoClass.class, astCssHasPseudoClass); }
        @Override public void visit(AstCssParentPseudoClass astCssParentPseudoClass) { registerVisit(AstCssParentPseudoClass.class, astCssParentPseudoClass); }
        @Override public void visit(AstCssButtonPseudoClass astCssButtonPseudoClass) { registerVisit(AstCssButtonPseudoClass.class, astCssButtonPseudoClass); }
        @Override public void visit(AstCssCheckboxPseudoClass astCssCheckboxPseudoClass) { registerVisit(AstCssCheckboxPseudoClass.class, astCssCheckboxPseudoClass); }
        @Override public void visit(AstCssCheckedPseudoClass astCssCheckedPseudoClass) { registerVisit(AstCssCheckedPseudoClass.class, astCssCheckedPseudoClass); }
        @Override public void visit(AstCssDisabledPseudoClass astCssDisabledPseudoClass) { registerVisit(AstCssDisabledPseudoClass.class, astCssDisabledPseudoClass); }
        @Override public void visit(AstCssInputPseudoClass astCssInputPseudoClass) { registerVisit(AstCssInputPseudoClass.class,
            astCssInputPseudoClass); }
        @Override public void visit(AstCssFilePseudoClass astCssFilePseudoClass) { registerVisit(AstCssFilePseudoClass.class,
            astCssFilePseudoClass); }
        @Override public void visit(AstCssResetPseudoClass astCssResetPseudoClass) { registerVisit(AstCssResetPseudoClass.class,
            astCssResetPseudoClass); }
        @Override public void visit(AstCssFocusPseudoClass astCssFocusPseudoClass) { registerVisit(AstCssFocusPseudoClass.class,
            astCssFocusPseudoClass); }
        @Override public void visit(AstCssRadioPseudoClass astCssRadioPseudoClass) { registerVisit(AstCssRadioPseudoClass.class,
            astCssRadioPseudoClass); }
        @Override public void visit(AstCssSelectedPseudoClass astCssSelectedPseudoClass) { registerVisit(AstCssSelectedPseudoClass.class, astCssSelectedPseudoClass); }
        @Override public void visit(AstCssImagePseudoClass astCssImagePseudoClass) { registerVisit(AstCssImagePseudoClass.class,
            astCssImagePseudoClass); }
        @Override public void visit(AstCssEnabledPseudoClass astCssEnabledPseudoClass) { registerVisit(AstCssEnabledPseudoClass.class, astCssEnabledPseudoClass); }
        @Override public void visit(AstCssPasswordPseudoClass astCssPasswordPseudoClass) { registerVisit(AstCssPasswordPseudoClass.class, astCssPasswordPseudoClass); }
        @Override public void visit(AstCssSubmitPseudoClass astCssSubmitPseudoClass) { registerVisit(AstCssSubmitPseudoClass.class, astCssSubmitPseudoClass); }
        @Override public void visit(AstCssTextPseudoClass astCssTextPseudoClass) { registerVisit(AstCssTextPseudoClass.class,
            astCssTextPseudoClass); }
        @Override public void visit(AstCssFocusablePseudoClass astCssFocusablePseudoClass) { registerVisit(AstCssFocusablePseudoClass.class, astCssFocusablePseudoClass); }
        @Override public void visit(AstCssTabbablePseudoClass astCssTabbablePseudoClass) { registerVisit(AstCssTabbablePseudoClass.class, astCssTabbablePseudoClass); }
        @Override public void visit(AstCssBlankPseudoClass astCssBlankPseudoClass) { registerVisit(AstCssBlankPseudoClass.class,
            astCssBlankPseudoClass); }
        @Override public void visit(AstCssFilledPseudoClass astCssFilledPseudoClass) { registerVisit(AstCssFilledPseudoClass.class, astCssFilledPseudoClass); }
        @Override public void visit(AstCssPresentPseudoClass astCssPresentPseudoClass) { registerVisit(AstCssPresentPseudoClass.class, astCssPresentPseudoClass); }
        @Override public void visit(AstCssUncheckedPseudoClass astCssUncheckedPseudoClass) { registerVisit(AstCssUncheckedPseudoClass.class, astCssUncheckedPseudoClass); }
        @Override public void visit(AstCssHiddenPseudoClass astCssHiddenPseudoClass) { registerVisit(AstCssHiddenPseudoClass.class, astCssHiddenPseudoClass); }
        @Override public void visit(AstCssVisiblePseudoClass astCssVisiblePseudoClass) { registerVisit(AstCssVisiblePseudoClass.class, astCssVisiblePseudoClass); }
    }

    private AstCssPseudoClassConditionVisitorMock visitor = new AstCssPseudoClassConditionVisitorMock();

    @Test
    public void visitCssAnimatedPseudoClass() {
        // given
        AstCssAnimatedPseudoClass astCssAnimatedPseudoClass = new CssAnimatedPseudoClass();
        // when
        astCssAnimatedPseudoClass.accept(visitor);
        // then
        assertEquals(CssAnimatedPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssAnimatedPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssEqPseudoClass() {
        // given
        AstCssEqPseudoClass astCssEqPseudoClass = new AstCssEqPseudoClass(99);
        // when
        astCssEqPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssEqPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssEqPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssEvenPseudoClass() {
        // given
        AstCssEvenPseudoClass astCssEvenPseudoClass = new CssEvenPseudoClass();
        // when
        astCssEvenPseudoClass.accept(visitor);
        // then
        assertEquals(CssEvenPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssEvenPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssFirstPseudoClass() {
        // given
        AstCssFirstPseudoClass astCssFirstPseudoClass = new AstCssFirstPseudoClass();
        // when
        astCssFirstPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssFirstPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssFirstPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssGtPseudoClass() {
        // given
        AstCssGtPseudoClass astCssGtPseudoClass = new CssGtPseudoClass(99);
        // when
        astCssGtPseudoClass.accept(visitor);
        // then
        assertEquals(CssGtPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssGtPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssHeaderPseudoClass() {
        // given
        AstCssHeaderPseudoClass astCssHeaderPseudoClass = new CssHeaderPseudoClass();
        // when
        astCssHeaderPseudoClass.accept(visitor);
        // then
        assertEquals(CssHeaderPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssHeaderPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssLangPseudoClass() {
        // given
        AstCssLangPseudoClass astCssLangPseudoClass = new CssLangPseudoClass("br");
        // when
        astCssLangPseudoClass.accept(visitor);
        // then
        assertEquals(CssLangPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssLangPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssLastPseudoClass() {
        // given
        AstCssLastPseudoClass astCssLastPseudoClass = new AstCssLastPseudoClass();
        // when
        astCssLastPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssLastPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssLastPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssLtPseudoClass() {
        // given
        AstCssLtPseudoClass astCssLtPseudoClass = new CssLtPseudoClass(55);
        // when
        astCssLtPseudoClass.accept(visitor);
        // then
        assertEquals(CssLtPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssLtPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssNotPseudoClass() {
        // given
        AstCssNotPseudoClass astCssNotPseudoClass = new AstCssNotPseudoClass(ParseTreeBuilder.parse(".class"));
        // when
        astCssNotPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssNotPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssNotPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssNthPseudoClass() {
        // given
        AstCssNthPseudoClass astCssNthPseudoClass = new AstCssNthPseudoClass(55);
        // when
        astCssNthPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssNthPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssNthPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssOddPseudoClass() {
        // given
        AstCssOddPseudoClass astCssOddPseudoClass = new CssOddPseudoClass();
        // when
        astCssOddPseudoClass.accept(visitor);
        // then
        assertEquals(CssOddPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssOddPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssRootPseudoClass() {
        // given
        AstCssRootPseudoClass astCssRootPseudoClass = new CssRootPseudoClass();
        // when
        astCssRootPseudoClass.accept(visitor);
        // then
        assertEquals(CssRootPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssRootPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssTargetPseudoClass() {
        // given
        AstCssTargetPseudoClass astCssTargetPseudoClass = new CssTargetPseudoClass();
        // when
        astCssTargetPseudoClass.accept(visitor);
        // then
        assertEquals(CssTargetPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssTargetPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssFirstChildPseudoClass() {
        // given
        AstCssFirstChildPseudoClass astCssFirstChildPseudoClass = new CssFirstChildPseudoClass();
        // when
        astCssFirstChildPseudoClass.accept(visitor);
        // then
        assertEquals(CssFirstChildPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssFirstChildPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssFirstOfTypePseudoClass() {
        // given
        AstCssFirstOfTypePseudoClass astCssFirstOfTypePseudoClass = new CssFirstOfTypePseudoClass();
        // when
        astCssFirstOfTypePseudoClass.accept(visitor);
        // then
        assertEquals(CssFirstOfTypePseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssFirstOfTypePseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssLastChildPseudoClass() {
        // given
        AstCssLastChildPseudoClass astCssLastChildPseudoClass = new CssLastChildPseudoClass();
        // when
        astCssLastChildPseudoClass.accept(visitor);
        // then
        assertEquals(CssLastChildPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssLastChildPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssLastOfTypePseudoClass() {
        // given
        AstCssLastOfTypePseudoClass astCssLastOfTypePseudoClass = new CssLastOfTypePseudoClass();
        // when
        astCssLastOfTypePseudoClass.accept(visitor);
        // then
        assertEquals(CssLastOfTypePseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssLastOfTypePseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssNthChildPseudoClass() {
        // given
        AstCssNthChildPseudoClass astCssNthChildPseudoClass = new CssNthChildPseudoClass("66");
        // when
        astCssNthChildPseudoClass.accept(visitor);
        // then
        assertEquals(CssNthChildPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssNthChildPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssNthLastChildPseudoClass() {
        // given
        AstCssNthLastChildPseudoClass astCssNthLastChildPseudoClass = new CssNthLastChildPseudoClass("77");
        // when
        astCssNthLastChildPseudoClass.accept(visitor);
        // then
        assertEquals(CssNthLastChildPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssNthLastChildPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssNthLastOfTypePseudoClass() {
        // given
        AstCssNthLastOfTypePseudoClass astCssNthLastOfTypePseudoClass = new CssNthLastOfTypePseudoClass("33");
        // when
        astCssNthLastOfTypePseudoClass.accept(visitor);
        // then
        assertEquals(CssNthLastOfTypePseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssNthLastOfTypePseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssNthOfTypePseudoClass() {
        // given
        AstCssNthOfTypePseudoClass astCssNthOfTypePseudoClass = new CssNthOfTypePseudoClass("88");
        // when
        astCssNthOfTypePseudoClass.accept(visitor);
        // then
        assertEquals(CssNthOfTypePseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssNthOfTypePseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssOnlyChildPseudoClass() {
        // given
        AstCssOnlyChildPseudoClass astCssOnlyChildPseudoClass = new CssOnlyChildPseudoClass();
        // when
        astCssOnlyChildPseudoClass.accept(visitor);
        // then
        assertEquals(CssOnlyChildPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssOnlyChildPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssOnlyOfTypePseudoClass() {
        // given
        AstCssOnlyOfTypePseudoClass astCssOnlyOfTypePseudoClass = new CssOnlyOfTypePseudoClass();
        // when
        astCssOnlyOfTypePseudoClass.accept(visitor);
        // then
        assertEquals(CssOnlyOfTypePseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssOnlyOfTypePseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssContainsPseudoClass() {
        // given
        AstCssContainsPseudoClass astCssContainsPseudoClass = new AstCssContainsPseudoClass("stuff");
        // when
        astCssContainsPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssContainsPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssContainsPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssEmptyPseudoClass() {
        // given
        AstCssEmptyPseudoClass astCssEmptyPseudoClass = new AstCssEmptyPseudoClass();
        // when
        astCssEmptyPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssEmptyPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssEmptyPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssHasPseudoClass() {
        // given
        AstCssHasPseudoClass astCssHasPseudoClass = new AstCssHasPseudoClass(ParseTreeBuilder.parse(".class"));
        // when
        astCssHasPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssHasPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssHasPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssParentPseudoClass() {
        // given
        AstCssParentPseudoClass astCssParentPseudoClass = new AstCssParentPseudoClass();
        // when
        astCssParentPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssParentPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssParentPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssButtonPseudoClass() {
        // given
        AstCssButtonPseudoClass astCssButtonPseudoClass = new AstCssButtonPseudoClass();
        // when
        astCssButtonPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssButtonPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssButtonPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssCheckboxPseudoClass() {
        // given
        AstCssCheckboxPseudoClass astCssCheckboxPseudoClass = new AstCssCheckboxPseudoClass();
        // when
        astCssCheckboxPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssCheckboxPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssCheckboxPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssCheckedPseudoClass() {
        // given
        AstCssCheckedPseudoClass astCssCheckedPseudoClass = new AstCssCheckedPseudoClass();
        // when
        astCssCheckedPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssCheckedPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssCheckedPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssDisabledPseudoClass() {
        // given
        AstCssDisabledPseudoClass astCssDisabledPseudoClass = new AstCssDisabledPseudoClass();
        // when
        astCssDisabledPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssDisabledPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssDisabledPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssInputPseudoClass() {
        // given
        AstCssInputPseudoClass astCssInputPseudoClass = new AstCssInputPseudoClass();
        // when
        astCssInputPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssInputPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssInputPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssFilePseudoClass() {
        // given
        AstCssFilePseudoClass astCssFilePseudoClass = new AstCssFilePseudoClass();
        // when
        astCssFilePseudoClass.accept(visitor);
        // then
        assertEquals(AstCssFilePseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssFilePseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssResetPseudoClass() {
        // given
        AstCssResetPseudoClass astCssResetPseudoClass = new AstCssResetPseudoClass();
        // when
        astCssResetPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssResetPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssResetPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssFocusPseudoClass() {
        // given
        AstCssFocusPseudoClass astCssFocusPseudoClass = new AstCssFocusPseudoClass();
        // when
        astCssFocusPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssFocusPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssFocusPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssRadioPseudoClass() {
        // given
        AstCssRadioPseudoClass astCssRadioPseudoClass = new AstCssRadioPseudoClass();
        // when
        astCssRadioPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssRadioPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssRadioPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssSelectedPseudoClass() {
        // given
        AstCssSelectedPseudoClass astCssSelectedPseudoClass = new AstCssSelectedPseudoClass();
        // when
        astCssSelectedPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssSelectedPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssSelectedPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssImagePseudoClass() {
        // given
        AstCssImagePseudoClass astCssImagePseudoClass = new AstCssImagePseudoClass();
        // when
        astCssImagePseudoClass.accept(visitor);
        // then
        assertEquals(AstCssImagePseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssImagePseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssEnabledPseudoClass() {
        // given
        AstCssEnabledPseudoClass astCssEnabledPseudoClass = new AstCssEnabledPseudoClass();
        // when
        astCssEnabledPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssEnabledPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssEnabledPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssPasswordPseudoClass() {
        // given
        AstCssPasswordPseudoClass astCssPasswordPseudoClass = new AstCssPasswordPseudoClass();
        // when
        astCssPasswordPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssPasswordPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssPasswordPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssSubmitPseudoClass() {
        // given
        AstCssSubmitPseudoClass astCssSubmitPseudoClass = new AstCssSubmitPseudoClass();
        // when
        astCssSubmitPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssSubmitPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssSubmitPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssTextPseudoClass() {
        // given
        AstCssTextPseudoClass astCssTextPseudoClass = new AstCssTextPseudoClass();
        // when
        astCssTextPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssTextPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssTextPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssFocusablePseudoClass() {
        // given
        AstCssFocusablePseudoClass astCssFocusablePseudoClass = new AstCssFocusablePseudoClass();
        // when
        astCssFocusablePseudoClass.accept(visitor);
        // then
        assertEquals(AstCssFocusablePseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssFocusablePseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssTabbablePseudoClass() {
        // given
        AstCssTabbablePseudoClass astCssTabbablePseudoClass = new AstCssTabbablePseudoClass();
        // when
        astCssTabbablePseudoClass.accept(visitor);
        // then
        assertEquals(AstCssTabbablePseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssTabbablePseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssBlankPseudoClass() {
        // given
        AstCssBlankPseudoClass astCssBlankPseudoClass = new AstCssBlankPseudoClass();
        // when
        astCssBlankPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssBlankPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssBlankPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssFilledPseudoClass() {
        // given
        AstCssFilledPseudoClass astCssFilledPseudoClass = new AstCssFilledPseudoClass();
        // when
        astCssFilledPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssFilledPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssFilledPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssPresentPseudoClass() {
        // given
        AstCssPresentPseudoClass astCssPresentPseudoClass = new AstCssPresentPseudoClass();
        // when
        astCssPresentPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssPresentPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssPresentPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssUncheckedPseudoClass() {
        // given
        AstCssUncheckedPseudoClass astCssUncheckedPseudoClass = new AstCssUncheckedPseudoClass();
        // when
        astCssUncheckedPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssUncheckedPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssUncheckedPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssHiddenPseudoClass() {
        // given
        AstCssHiddenPseudoClass astCssHiddenPseudoClass = new AstCssHiddenPseudoClass();
        // when
        astCssHiddenPseudoClass.accept(visitor);
        // then
        assertEquals(AstCssHiddenPseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssHiddenPseudoClass, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssVisiblePseudoClass() {
        // given
        AstCssVisiblePseudoClass astCssVisiblePseudoClass = new AstCssVisiblePseudoClass();
        // when
        astCssVisiblePseudoClass.accept(visitor);
        // then
        assertEquals(AstCssVisiblePseudoClass.class, visitor.getVisitedClass());
        assertEquals(astCssVisiblePseudoClass, visitor.getVisitedInstance());
    }

}
