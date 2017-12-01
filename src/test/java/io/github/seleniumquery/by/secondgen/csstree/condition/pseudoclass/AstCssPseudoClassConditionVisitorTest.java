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

import io.github.seleniumquery.by.secondgen.parser.ParseTreeBuilder;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.AstCssPseudoClassCondition;
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

public class AstCssPseudoClassConditionVisitorTest {

    class AstCssPseudoClassConditionVisitorMock implements AstCssPseudoClassConditionVisitor<Void> {
        private Class<? extends AstCssPseudoClassCondition> visitedClass;
        private AstCssPseudoClassCondition visitedInstance;

        Class<? extends AstCssPseudoClassCondition> getVisitedClass() { return visitedClass; }
        AstCssPseudoClassCondition getVisitedInstance() { return visitedInstance; }

        void registerVisit(Class<? extends AstCssPseudoClassCondition> visitedClass, AstCssPseudoClassCondition visited) {
            if (this.visitedClass != null) fail("Visitor has already registered a visit: " + this.visitedClass);
            this.visitedClass = visitedClass;
            this.visitedInstance = visited;
        }

        @Override public Void visit(AstCssAnimatedPseudoClass astCssAnimatedPseudoClass) { registerVisit(AstCssAnimatedPseudoClass.class, astCssAnimatedPseudoClass); return null; }
        @Override public Void visit(AstCssEqPseudoClass astCssEqPseudoClass) { registerVisit(AstCssEqPseudoClass.class, astCssEqPseudoClass); return null; }
        @Override public Void visit(AstCssEvenPseudoClass astCssEvenPseudoClass) { registerVisit(AstCssEvenPseudoClass.class, astCssEvenPseudoClass); return null; }
        @Override public Void visit(AstCssFirstPseudoClass astCssFirstPseudoClass) { registerVisit(AstCssFirstPseudoClass.class, astCssFirstPseudoClass); return null; }
        @Override public Void visit(AstCssGtPseudoClass astCssGtPseudoClass) { registerVisit(AstCssGtPseudoClass.class, astCssGtPseudoClass); return null; }
        @Override public Void visit(AstCssHeaderPseudoClass astCssHeaderPseudoClass) { registerVisit(AstCssHeaderPseudoClass.class, astCssHeaderPseudoClass); return null; }
        @Override public Void visit(AstCssLangPseudoClass astCssLangPseudoClass) { registerVisit(AstCssLangPseudoClass.class, astCssLangPseudoClass); return null; }
        @Override public Void visit(AstCssLastPseudoClass astCssLastPseudoClass) { registerVisit(AstCssLastPseudoClass.class, astCssLastPseudoClass); return null; }
        @Override public Void visit(AstCssLtPseudoClass astCssLtPseudoClass) { registerVisit(AstCssLtPseudoClass.class, astCssLtPseudoClass); return null; }
        @Override public Void visit(AstCssNotPseudoClass astCssNotPseudoClass) { registerVisit(AstCssNotPseudoClass.class, astCssNotPseudoClass); return null; }
        @Override public Void visit(AstCssNthPseudoClass astCssNthPseudoClass) { registerVisit(AstCssNthPseudoClass.class, astCssNthPseudoClass); return null; }
        @Override public Void visit(AstCssOddPseudoClass astCssOddPseudoClass) { registerVisit(AstCssOddPseudoClass.class, astCssOddPseudoClass); return null; }
        @Override public Void visit(AstCssRootPseudoClass astCssRootPseudoClass) { registerVisit(AstCssRootPseudoClass.class, astCssRootPseudoClass); return null; }
        @Override public Void visit(AstCssTargetPseudoClass astCssTargetPseudoClass) { registerVisit(AstCssTargetPseudoClass.class, astCssTargetPseudoClass); return null; }
        @Override public Void visit(AstCssFirstChildPseudoClass astCssFirstChildPseudoClass) { registerVisit(AstCssFirstChildPseudoClass.class, astCssFirstChildPseudoClass); return null; }
        @Override public Void visit(AstCssFirstOfTypePseudoClass astCssFirstOfTypePseudoClass) { registerVisit(AstCssFirstOfTypePseudoClass.class, astCssFirstOfTypePseudoClass); return null; }
        @Override public Void visit(AstCssLastChildPseudoClass astCssLastChildPseudoClass) { registerVisit(AstCssLastChildPseudoClass.class, astCssLastChildPseudoClass); return null; }
        @Override public Void visit(AstCssLastOfTypePseudoClass astCssLastOfTypePseudoClass) { registerVisit(AstCssLastOfTypePseudoClass.class, astCssLastOfTypePseudoClass); return null; }
        @Override public Void visit(AstCssNthChildPseudoClass astCssNthChildPseudoClass) { registerVisit(AstCssNthChildPseudoClass.class, astCssNthChildPseudoClass); return null; }
        @Override public Void visit(AstCssNthLastChildPseudoClass astCssNthLastChildPseudoClass) { registerVisit(AstCssNthLastChildPseudoClass.class, astCssNthLastChildPseudoClass); return null; }
        @Override public Void visit(AstCssNthLastOfTypePseudoClass astCssNthLastOfTypePseudoClass) { registerVisit(AstCssNthLastOfTypePseudoClass.class, astCssNthLastOfTypePseudoClass); return null; }
        @Override public Void visit(AstCssNthOfTypePseudoClass astCssNthOfTypePseudoClass) { registerVisit(AstCssNthOfTypePseudoClass.class, astCssNthOfTypePseudoClass); return null; }
        @Override public Void visit(AstCssOnlyChildPseudoClass astCssOnlyChildPseudoClass) { registerVisit(AstCssOnlyChildPseudoClass.class, astCssOnlyChildPseudoClass); return null; }
        @Override public Void visit(AstCssOnlyOfTypePseudoClass astCssOnlyOfTypePseudoClass) { registerVisit(AstCssOnlyOfTypePseudoClass.class, astCssOnlyOfTypePseudoClass); return null; }
        @Override public Void visit(AstCssContainsPseudoClass astCssContainsPseudoClass) { registerVisit(AstCssContainsPseudoClass.class, astCssContainsPseudoClass); return null; }
        @Override public Void visit(AstCssEmptyPseudoClass astCssEmptyPseudoClass) { registerVisit(AstCssEmptyPseudoClass.class, astCssEmptyPseudoClass); return null; }
        @Override public Void visit(AstCssHasPseudoClass astCssHasPseudoClass) { registerVisit(AstCssHasPseudoClass.class, astCssHasPseudoClass); return null; }
        @Override public Void visit(AstCssParentPseudoClass astCssParentPseudoClass) { registerVisit(AstCssParentPseudoClass.class, astCssParentPseudoClass); return null; }
        @Override public Void visit(AstCssButtonPseudoClass astCssButtonPseudoClass) { registerVisit(AstCssButtonPseudoClass.class, astCssButtonPseudoClass); return null; }
        @Override public Void visit(AstCssCheckboxPseudoClass astCssCheckboxPseudoClass) { registerVisit(AstCssCheckboxPseudoClass.class, astCssCheckboxPseudoClass); return null; }
        @Override public Void visit(AstCssCheckedPseudoClass astCssCheckedPseudoClass) { registerVisit(AstCssCheckedPseudoClass.class, astCssCheckedPseudoClass); return null; }
        @Override public Void visit(AstCssDisabledPseudoClass astCssDisabledPseudoClass) { registerVisit(AstCssDisabledPseudoClass.class, astCssDisabledPseudoClass); return null; }
        @Override public Void visit(AstCssInputPseudoClass astCssInputPseudoClass) { registerVisit(AstCssInputPseudoClass.class, astCssInputPseudoClass); return null; }
        @Override public Void visit(AstCssFilePseudoClass astCssFilePseudoClass) { registerVisit(AstCssFilePseudoClass.class, astCssFilePseudoClass); return null; }
        @Override public Void visit(AstCssResetPseudoClass astCssResetPseudoClass) { registerVisit(AstCssResetPseudoClass.class, astCssResetPseudoClass); return null; }
        @Override public Void visit(AstCssFocusPseudoClass astCssFocusPseudoClass) { registerVisit(AstCssFocusPseudoClass.class, astCssFocusPseudoClass); return null; }
        @Override public Void visit(AstCssRadioPseudoClass astCssRadioPseudoClass) { registerVisit(AstCssRadioPseudoClass.class, astCssRadioPseudoClass); return null; }
        @Override public Void visit(AstCssSelectedPseudoClass astCssSelectedPseudoClass) { registerVisit(AstCssSelectedPseudoClass.class, astCssSelectedPseudoClass); return null; }
        @Override public Void visit(AstCssImagePseudoClass astCssImagePseudoClass) { registerVisit(AstCssImagePseudoClass.class, astCssImagePseudoClass); return null; }
        @Override public Void visit(AstCssEnabledPseudoClass astCssEnabledPseudoClass) { registerVisit(AstCssEnabledPseudoClass.class, astCssEnabledPseudoClass); return null; }
        @Override public Void visit(AstCssPasswordPseudoClass astCssPasswordPseudoClass) { registerVisit(AstCssPasswordPseudoClass.class, astCssPasswordPseudoClass); return null; }
        @Override public Void visit(AstCssSubmitPseudoClass astCssSubmitPseudoClass) { registerVisit(AstCssSubmitPseudoClass.class, astCssSubmitPseudoClass); return null; }
        @Override public Void visit(AstCssTextPseudoClass astCssTextPseudoClass) { registerVisit(AstCssTextPseudoClass.class, astCssTextPseudoClass); return null; }
        @Override public Void visit(AstCssFocusablePseudoClass astCssFocusablePseudoClass) { registerVisit(AstCssFocusablePseudoClass.class, astCssFocusablePseudoClass); return null; }
        @Override public Void visit(AstCssTabbablePseudoClass astCssTabbablePseudoClass) { registerVisit(AstCssTabbablePseudoClass.class, astCssTabbablePseudoClass); return null; }
        @Override public Void visit(AstCssBlankPseudoClass astCssBlankPseudoClass) { registerVisit(AstCssBlankPseudoClass.class, astCssBlankPseudoClass); return null; }
        @Override public Void visit(AstCssFilledPseudoClass astCssFilledPseudoClass) { registerVisit(AstCssFilledPseudoClass.class, astCssFilledPseudoClass); return null; }
        @Override public Void visit(AstCssPresentPseudoClass astCssPresentPseudoClass) { registerVisit(AstCssPresentPseudoClass.class, astCssPresentPseudoClass); return null; }
        @Override public Void visit(AstCssUncheckedPseudoClass astCssUncheckedPseudoClass) { registerVisit(AstCssUncheckedPseudoClass.class, astCssUncheckedPseudoClass); return null; }
        @Override public Void visit(AstCssHiddenPseudoClass astCssHiddenPseudoClass) { registerVisit(AstCssHiddenPseudoClass.class, astCssHiddenPseudoClass); return null; }
        @Override public Void visit(AstCssVisiblePseudoClass astCssVisiblePseudoClass) { registerVisit(AstCssVisiblePseudoClass.class, astCssVisiblePseudoClass); return null; }
    }

    private AstCssPseudoClassConditionVisitorMock visitor = new AstCssPseudoClassConditionVisitorMock();

    private void assertVisitorVisitsCorrectClass(AstCssPseudoClassCondition astCssPseudoClassCondition) {
        // given
        // argument passed
        // when
        astCssPseudoClassCondition.accept(visitor);
        // then
        assertEquals(astCssPseudoClassCondition.getClass(), visitor.getVisitedClass());
        assertEquals(astCssPseudoClassCondition, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssAnimatedPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssAnimatedPseudoClass());
    }

    @Test
    public void visitCssEqPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssEqPseudoClass(99));
    }

    @Test
    public void visitCssEvenPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssEvenPseudoClass());
    }

    @Test
    public void visitCssFirstPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssFirstPseudoClass());
    }

    @Test
    public void visitCssGtPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssGtPseudoClass(99));
    }

    @Test
    public void visitCssHeaderPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssHeaderPseudoClass());
    }

    @Test
    public void visitCssLangPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssLangPseudoClass("br"));
    }

    @Test
    public void visitCssLastPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssLastPseudoClass());
    }

    @Test
    public void visitCssLtPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssLtPseudoClass(55));
    }

    @Test
    public void visitCssNotPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssNotPseudoClass(ParseTreeBuilder.parse(".clazz")));
    }

    @Test
    public void visitCssNthPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssNthPseudoClass(55));
    }

    @Test
    public void visitCssOddPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssOddPseudoClass());
    }

    @Test
    public void visitCssRootPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssRootPseudoClass());
    }

    @Test
    public void visitCssTargetPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssTargetPseudoClass());
    }

    @Test
    public void visitCssFirstChildPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssFirstChildPseudoClass());
    }

    @Test
    public void visitCssFirstOfTypePseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssFirstOfTypePseudoClass());
    }

    @Test
    public void visitCssLastChildPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssLastChildPseudoClass());
    }

    @Test
    public void visitCssLastOfTypePseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssLastOfTypePseudoClass());
    }

    @Test
    public void visitCssNthChildPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssNthChildPseudoClass("66"));
    }

    @Test
    public void visitCssNthLastChildPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssNthLastChildPseudoClass("77"));
    }

    @Test
    public void visitCssNthLastOfTypePseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssNthLastOfTypePseudoClass("33"));
    }

    @Test
    public void visitCssNthOfTypePseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssNthOfTypePseudoClass("88"));
    }

    @Test
    public void visitCssOnlyChildPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssOnlyChildPseudoClass());
    }

    @Test
    public void visitCssOnlyOfTypePseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssOnlyOfTypePseudoClass());
    }

    @Test
    public void visitCssContainsPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssContainsPseudoClass("stuff"));
    }

    @Test
    public void visitCssEmptyPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssEmptyPseudoClass());
    }

    @Test
    public void visitCssHasPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssHasPseudoClass(ParseTreeBuilder.parse(".clazz")));
    }

    @Test
    public void visitCssParentPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssParentPseudoClass());
    }

    @Test
    public void visitCssButtonPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssButtonPseudoClass());
    }

    @Test
    public void visitCssCheckboxPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssCheckboxPseudoClass());
    }

    @Test
    public void visitCssCheckedPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssCheckedPseudoClass());
    }

    @Test
    public void visitCssDisabledPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssDisabledPseudoClass());
    }

    @Test
    public void visitCssInputPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssInputPseudoClass());
    }

    @Test
    public void visitCssFilePseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssFilePseudoClass());
    }

    @Test
    public void visitCssResetPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssResetPseudoClass());
    }

    @Test
    public void visitCssFocusPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssFocusPseudoClass());
    }

    @Test
    public void visitCssRadioPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssRadioPseudoClass());
    }

    @Test
    public void visitCssSelectedPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssSelectedPseudoClass());
    }

    @Test
    public void visitCssImagePseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssImagePseudoClass());
    }

    @Test
    public void visitCssEnabledPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssEnabledPseudoClass());
    }

    @Test
    public void visitCssPasswordPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssPasswordPseudoClass());
    }

    @Test
    public void visitCssSubmitPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssSubmitPseudoClass());
    }

    @Test
    public void visitCssTextPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssTextPseudoClass());
    }

    @Test
    public void visitCssFocusablePseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssFocusablePseudoClass());
    }

    @Test
    public void visitCssTabbablePseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssTabbablePseudoClass());
    }

    @Test
    public void visitCssBlankPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssBlankPseudoClass());
    }

    @Test
    public void visitCssFilledPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssFilledPseudoClass());
    }

    @Test
    public void visitCssPresentPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssPresentPseudoClass());
    }

    @Test
    public void visitCssUncheckedPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssUncheckedPseudoClass());
    }

    @Test
    public void visitCssHiddenPseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssHiddenPseudoClass());
    }

    @Test
    public void visitCssVisiblePseudoClass() {
        assertVisitorVisitsCorrectClass(new AstCssVisiblePseudoClass());
    }

}
