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

package io.github.seleniumquery.by.secondgen.csstree.selector;

import io.github.seleniumquery.by.secondgen.csstree.condition.CssAndCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.CssCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.CssClassAttributeCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.CssContainsPrefixAttributeCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.CssContainsSubstringAttributeCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.CssContainsWordAttributeCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.CssEndsWithAttributeCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.CssEqualsOrHasAttributeCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.CssIdAttributeCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.CssStartsWithAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.AstCssAndCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.AstCssConditionVisitor;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssClassAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssContainsPrefixAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssContainsSubstringAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssContainsWordAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssEndsWithAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssEqualsOrHasAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssIdAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssStartsWithAttributeCondition;

public class CssConditionVisitor implements AstCssConditionVisitor<CssCondition> {

    @Override
    public CssCondition visit(AstCssAndCondition astCssAndCondition) {
        return new CssAndCondition(astCssAndCondition);
    }

    @Override
    public CssCondition visit(AstCssClassAttributeCondition astCssClassAttributeCondition) {
        return new CssClassAttributeCondition(astCssClassAttributeCondition);
    }

    @Override
    public CssCondition visit(AstCssContainsPrefixAttributeCondition astCssContainsPrefixAttributeCondition) {
        return new CssContainsPrefixAttributeCondition(astCssContainsPrefixAttributeCondition);
    }

    @Override
    public CssCondition visit(AstCssContainsSubstringAttributeCondition astCssContainsSubstringAttributeCondition) {
        return new CssContainsSubstringAttributeCondition(astCssContainsSubstringAttributeCondition);
    }

    @Override
    public CssCondition visit(AstCssContainsWordAttributeCondition astCssContainsWordAttributeCondition) {
        return new CssContainsWordAttributeCondition(astCssContainsWordAttributeCondition);
    }

    @Override
    public CssCondition visit(AstCssEndsWithAttributeCondition astCssEndsWithAttributeCondition) {
        return new CssEndsWithAttributeCondition(astCssEndsWithAttributeCondition);
    }

    @Override
    public CssCondition visit(AstCssEqualsOrHasAttributeCondition astCssEqualsOrHasAttributeCondition) {
        return new CssEqualsOrHasAttributeCondition(astCssEqualsOrHasAttributeCondition);
    }

    @Override
    public CssCondition visit(AstCssIdAttributeCondition astCssIdAttributeCondition) {
        return new CssIdAttributeCondition(astCssIdAttributeCondition);
    }

    @Override
    public CssCondition visit(AstCssStartsWithAttributeCondition astCssStartsWithAttributeCondition) {
        return new CssStartsWithAttributeCondition(astCssStartsWithAttributeCondition);
    }

}
