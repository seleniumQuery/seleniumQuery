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

package io.github.seleniumquery.by.secondgen.parser.ast.condition;

import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssClassAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssContainsPrefixAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssContainsSubstringAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssContainsWordAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssEndsWithAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssEqualsOrHasAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssIdAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssStartsWithAttributeCondition;

public interface AstCssConditionVisitor<T> {

    T visit(AstCssAndCondition astCssAndCondition);

    T visit(AstCssClassAttributeCondition astCssClassAttributeCondition);
    T visit(AstCssContainsPrefixAttributeCondition astCssContainsPrefixAttributeCondition);
    T visit(AstCssContainsSubstringAttributeCondition astCssContainsSubstringAttributeCondition);
    T visit(AstCssContainsWordAttributeCondition astCssContainsWordAttributeCondition);
    T visit(AstCssEndsWithAttributeCondition astCssEndsWithAttributeCondition);
    T visit(AstCssEqualsOrHasAttributeCondition astCssEqualsOrHasAttributeCondition);
    T visit(AstCssIdAttributeCondition astCssIdAttributeCondition);
    T visit(AstCssStartsWithAttributeCondition astCssStartsWithAttributeCondition);

}
