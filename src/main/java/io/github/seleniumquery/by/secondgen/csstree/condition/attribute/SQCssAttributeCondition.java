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
 * A class that holds an attribute name and a wanted value.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public abstract class SQCssAttributeCondition implements SQCssCondition, SQCssConditionImplementedFinders {

    protected String attributeName;
    protected String wantedValue;

    protected SQCssAttributeCondition(String attributeName, String wantedValue) {
        this.attributeName = attributeName;
        this.wantedValue = wantedValue;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getWantedValue() {
        return wantedValue;
    }

    @Override
    public ElementFinder toElementFinder(ElementFinder leftFinder) {
        CssFinder newCssSelector = leftFinder.getCssFinder().merge(toCSS());
        String newXPathExpression = ElementFinderUtils.conditionalSimpleXPathMerge(leftFinder.getXPathExpression(), toXPath());
        return new ElementFinder(newCssSelector, newXPathExpression, leftFinder);
    }

    protected CssFinder toCSS() {
        return new CssFinder("[" + getCssEscapedAttributeName() + symbol() + "'" + CssEscape.escapeCssString(this.wantedValue) + "']");
    }

    protected String getCssEscapedAttributeName() {
        return CssEscape.escapeCssIdentifier(this.attributeName);
    }

    protected abstract String symbol();

    protected abstract String toXPath();

}