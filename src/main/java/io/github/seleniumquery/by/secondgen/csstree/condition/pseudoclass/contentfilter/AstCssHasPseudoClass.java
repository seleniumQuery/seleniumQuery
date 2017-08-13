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

package io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.contentfilter;

import io.github.seleniumquery.by.secondgen.csstree.CssSelectorList;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.AstCssPseudoClassCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.AstCssPseudoClassConditionVisitor;

public class AstCssHasPseudoClass implements AstCssPseudoClassCondition {
    public static final String PSEUDO = "has";
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    protected final CssSelectorList argumentSelector;

    public AstCssHasPseudoClass(CssSelectorList argumentSelector) {
        this.argumentSelector = argumentSelector;
    }

    @Override
    public void accept(AstCssPseudoClassConditionVisitor visitor) {
        visitor.visit(this);
    }
}
