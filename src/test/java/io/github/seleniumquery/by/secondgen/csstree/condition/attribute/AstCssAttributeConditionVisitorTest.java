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

package io.github.seleniumquery.by.secondgen.csstree.condition.attribute;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class AstCssAttributeConditionVisitorTest {

    class AstCssAttributeConditionVisitorMock implements AstCssAttributeConditionVisitor<Void> {
        private Class<? extends AstCssAttributeCondition> visitedClass;
        private AstCssAttributeCondition visitedInstance;

        Class<? extends AstCssAttributeCondition> getVisitedClass() { return visitedClass; }
        AstCssAttributeCondition getVisitedInstance() { return visitedInstance; }

        void registerVisit(Class<? extends AstCssAttributeCondition> visitedClass, AstCssAttributeCondition visited) {
            if (this.visitedClass != null) fail("Visitor has already registered a visit: " + this.visitedClass);
            this.visitedClass = visitedClass;
            this.visitedInstance = visited;
        }

        @Override public Void visit(AstCssClassAttributeCondition astCssClassAttributeCondition) { registerVisit(AstCssClassAttributeCondition.class, astCssClassAttributeCondition); return null; }
        @Override public Void visit(AstCssContainsPrefixAttributeCondition astCssContainsPrefixAttributeCondition) { registerVisit(AstCssContainsPrefixAttributeCondition.class, astCssContainsPrefixAttributeCondition); return null; }
        @Override public Void visit(AstCssContainsSubstringAttributeCondition astCssContainsSubstringAttributeCondition) { registerVisit(AstCssContainsSubstringAttributeCondition.class, astCssContainsSubstringAttributeCondition); return null; }
        @Override public Void visit(AstCssContainsWordAttributeCondition astCssContainsWordAttributeCondition) { registerVisit(AstCssContainsWordAttributeCondition.class, astCssContainsWordAttributeCondition); return null; }
        @Override public Void visit(AstCssEndsWithAttributeCondition astCssEndsWithAttributeCondition) { registerVisit(AstCssEndsWithAttributeCondition.class, astCssEndsWithAttributeCondition); return null; }
        @Override public Void visit(AstCssEqualsOrHasAttributeCondition astCssEqualsOrHasAttributeCondition) { registerVisit(AstCssEqualsOrHasAttributeCondition.class, astCssEqualsOrHasAttributeCondition); return null; }
        @Override public Void visit(AstCssIdAttributeCondition astCssIdAttributeCondition) { registerVisit(AstCssIdAttributeCondition.class, astCssIdAttributeCondition); return null; }
        @Override public Void visit(AstCssStartsWithAttributeCondition astCssStartsWithAttributeCondition) { registerVisit(AstCssStartsWithAttributeCondition.class, astCssStartsWithAttributeCondition); return null; }
    }

    private AstCssAttributeConditionVisitorTest.AstCssAttributeConditionVisitorMock visitor = new AstCssAttributeConditionVisitorTest.AstCssAttributeConditionVisitorMock();

    private void assertVisitorVisitsCorrectClass(AstCssAttributeCondition astCssPseudoClassCondition) {
        // given
        // argument passed
        // when
        astCssPseudoClassCondition.accept(visitor);
        // then
        assertEquals(astCssPseudoClassCondition.getClass(), visitor.getVisitedClass());
        assertEquals(astCssPseudoClassCondition, visitor.getVisitedInstance());
    }

    @Test
    public void visitCssClassAttributeCondition() {
        assertVisitorVisitsCorrectClass(new AstCssClassAttributeCondition("attr"));
    }

    @Test
    public void visitCssContainsPrefixAttributeCondition() {
        assertVisitorVisitsCorrectClass(new AstCssContainsPrefixAttributeCondition("attr", "val"));
    }

    @Test
    public void visitCssContainsSubstringAttributeCondition() {
        assertVisitorVisitsCorrectClass(new AstCssContainsSubstringAttributeCondition("attr", "val"));
    }

    @Test
    public void visitCssContainsWordAttributeCondition() {
        assertVisitorVisitsCorrectClass(new AstCssContainsWordAttributeCondition("attr", "val"));
    }

    @Test
    public void visitCssEndsWithAttributeCondition() {
        assertVisitorVisitsCorrectClass(new AstCssEndsWithAttributeCondition("attr", "val"));
    }

    @Test
    public void visitCssEqualsOrHasAttributeCondition() {
        assertVisitorVisitsCorrectClass(new AstCssEqualsOrHasAttributeCondition("attr"));
    }

    @Test
    public void visitCssEqualsOrHasAttributeCondition__2nd_constructor() {
        assertVisitorVisitsCorrectClass(new AstCssEqualsOrHasAttributeCondition("attr", "val"));
    }

    @Test
    public void visitCssIdAttributeCondition() {
        assertVisitorVisitsCorrectClass(new AstCssIdAttributeCondition("id"));
    }

    @Test
    public void visitCssStartsWithAttributeCondition() {
        assertVisitorVisitsCorrectClass(new AstCssStartsWithAttributeCondition("attr", "val"));
    }

}
