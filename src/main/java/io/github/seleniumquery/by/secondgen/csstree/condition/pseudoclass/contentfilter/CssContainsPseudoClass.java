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

import org.openqa.selenium.WebDriver;

import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.AstCssPseudoClassCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.AstCssPseudoClassConditionVisitor;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.CssFunctionalPseudoClassCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.finderfactorystrategy.NeverNativelySupportedPseudoClass;
import io.github.seleniumquery.by.secondgen.finder.XPathAndFilterFinder;
import io.github.seleniumquery.utils.SelectorUtils;

/**
 * :contains()
 * https://api.jquery.com/contains-selector/
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssContainsPseudoClass extends CssFunctionalPseudoClassCondition implements AstCssPseudoClassCondition,  NeverNativelySupportedPseudoClass {

    public static final String PSEUDO = "contains";

    public CssContainsPseudoClass(String pseudoClassArgument) {
        super(pseudoClassArgument);
    }

    @Override
    public void accept(AstCssPseudoClassConditionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public XPathAndFilterFinder toXPath(WebDriver webDriver) {
        String textToContain = getArgument().getArgumentAsString();
        textToContain = SelectorUtils.unescapeString(textToContain);
        String wantedTextToContain = SelectorUtils.intoEscapedXPathString(textToContain);
        return XPathAndFilterFinder.pureXPath("contains(string(.), " + wantedTextToContain + ")");
    }

}
