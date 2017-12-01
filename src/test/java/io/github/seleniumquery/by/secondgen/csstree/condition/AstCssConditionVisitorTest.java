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

package io.github.seleniumquery.by.secondgen.csstree.condition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import io.github.seleniumquery.by.secondgen.parser.ast.condition.AstCssAndCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.AstCssCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.AstCssConditionVisitor;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssClassAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssContainsPrefixAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssContainsSubstringAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssContainsWordAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssEndsWithAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssEqualsOrHasAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssIdAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssStartsWithAttributeCondition;

public class AstCssConditionVisitorTest {

    private static final String ATTR = null;

    class AstCssConditionVisitorMock implements AstCssConditionVisitor<Void> {
        private Class<? extends AstCssCondition> visitedClass;
        private AstCssCondition visitedInstance;

        Class<? extends AstCssCondition> getVisitedClass() { return visitedClass; }
        AstCssCondition getVisitedInstance() { return visitedInstance; }

        void registerVisit(Class<? extends AstCssCondition> visitedClass, AstCssCondition visited) {
            if (this.visitedClass != null) fail("Visitor has already registered a visit: " + this.visitedClass);
            this.visitedClass = visitedClass;
            this.visitedInstance = visited;
        }

        @Override public Void visit(AstCssAndCondition astCssAndCondition) { registerVisit(AstCssAndCondition.class, astCssAndCondition); return null; }
        @Override public Void visit(AstCssClassAttributeCondition astCssClassAttributeCondition) { registerVisit(AstCssClassAttributeCondition.class, astCssClassAttributeCondition); return null; }
        @Override public Void visit(AstCssContainsPrefixAttributeCondition astCssContainsPrefixAttributeCondition) { registerVisit(AstCssContainsPrefixAttributeCondition.class, astCssContainsPrefixAttributeCondition); return null; }
        @Override public Void visit(AstCssContainsSubstringAttributeCondition astCssContainsSubstringAttributeCondition) { registerVisit(AstCssContainsSubstringAttributeCondition.class, astCssContainsSubstringAttributeCondition); return null; }
        @Override public Void visit(AstCssContainsWordAttributeCondition astCssContainsWordAttributeCondition) { registerVisit(AstCssContainsWordAttributeCondition.class, astCssContainsWordAttributeCondition); return null; }
        @Override public Void visit(AstCssEndsWithAttributeCondition astCssEndsWithAttributeCondition) { registerVisit(AstCssEndsWithAttributeCondition.class, astCssEndsWithAttributeCondition); return null; }
        @Override public Void visit(AstCssEqualsOrHasAttributeCondition astCssEqualsOrHasAttributeCondition) { registerVisit(AstCssEqualsOrHasAttributeCondition.class, astCssEqualsOrHasAttributeCondition); return null; }
        @Override public Void visit(AstCssIdAttributeCondition astCssIdAttributeCondition) { registerVisit(AstCssIdAttributeCondition.class, astCssIdAttributeCondition); return null; }
        @Override public Void visit(AstCssStartsWithAttributeCondition astCssStartsWithAttributeCondition) { registerVisit(AstCssStartsWithAttributeCondition.class, astCssStartsWithAttributeCondition); return null; }
    }

    private AstCssConditionVisitorTest.AstCssConditionVisitorMock visitor = new AstCssConditionVisitorTest.AstCssConditionVisitorMock();

    private void assertVisitorVisitsCorrectClass(AstCssCondition astCssCondition) {
        // given
        // argument passed
        // when
        astCssCondition.accept(visitor);
        // then
        assertEquals(astCssCondition.getClass(), visitor.getVisitedClass());
        assertEquals(astCssCondition, visitor.getVisitedInstance());
    }

    @Test
    public void visitAstCssAndCondition() {
        assertVisitorVisitsCorrectClass(new AstCssAndCondition(null, null));
    }

    @Test
    public void visitCssClassAttributeCondition() {
        assertVisitorVisitsCorrectClass(new AstCssClassAttributeCondition(ATTR));
    }

    @Test
    public void visitCssContainsPrefixAttributeCondition() {
        assertVisitorVisitsCorrectClass(new AstCssContainsPrefixAttributeCondition(ATTR, ATTR));
    }

    @Test
    public void visitCssContainsSubstringAttributeCondition() {
        assertVisitorVisitsCorrectClass(new AstCssContainsSubstringAttributeCondition(ATTR, ATTR));
    }

    @Test
    public void visitCssContainsWordAttributeCondition() {
        assertVisitorVisitsCorrectClass(new AstCssContainsWordAttributeCondition(ATTR, ATTR));
    }

    @Test
    public void visitCssEndsWithAttributeCondition() {
        assertVisitorVisitsCorrectClass(new AstCssEndsWithAttributeCondition(ATTR, ATTR));
    }

    @Test
    public void visitCssEqualsOrHasAttributeCondition() {
        assertVisitorVisitsCorrectClass(new AstCssEqualsOrHasAttributeCondition(ATTR));
    }

    @Test
    public void visitCssEqualsOrHasAttributeCondition__2nd_constructor() {
        assertVisitorVisitsCorrectClass(new AstCssEqualsOrHasAttributeCondition(ATTR, ATTR));
    }

    @Test
    public void visitCssIdAttributeCondition() {
        assertVisitorVisitsCorrectClass(new AstCssIdAttributeCondition(ATTR));
    }

    @Test
    public void visitCssStartsWithAttributeCondition() {
        assertVisitorVisitsCorrectClass(new AstCssStartsWithAttributeCondition(ATTR, ATTR));
    }

}
