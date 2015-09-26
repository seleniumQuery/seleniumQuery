/*
 * Copyright (c) 2015 seleniumQuery authors
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

import io.github.seleniumquery.by.secondgen.csstree.condition.SQCssCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.SQCssConditionImplementedFinders;
import io.github.seleniumquery.by.secondgen.finder.CssFinder;
import io.github.seleniumquery.by.secondgen.finder.ElementFinder;
import io.github.seleniumquery.by.secondgen.finder.ElementFinderUtils;
import org.unbescape.css.CssEscape;

/**
 * .class
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssClassAttributeCondition implements SQCssCondition, SQCssConditionImplementedFinders {

    private String unescapedClassName;

    public SQCssClassAttributeCondition(String unescapedClassName) {
        this.unescapedClassName = unescapedClassName;
    }

    public String getClassName() {
        return unescapedClassName;
    }

    @Override
    public ElementFinder toElementFinder(ElementFinder leftFinder) {
        CssFinder newCssSelector = leftFinder.getCssFinder().merge(toCSS());
        String newXPathExpression = ElementFinderUtils.conditionalSimpleXPathMerge(leftFinder.getXPathExpression(), toXPath());
        return new ElementFinder(newCssSelector, newXPathExpression, leftFinder);
    }

    private CssFinder toCSS() {
        return new CssFinder("." + CssEscape.escapeCssIdentifier(this.unescapedClassName));
    }

    private String toXPath() {
        return "contains(concat(' ', normalize-space(@class), ' '), ' " + unescapedClassName + " ')";
    }

}