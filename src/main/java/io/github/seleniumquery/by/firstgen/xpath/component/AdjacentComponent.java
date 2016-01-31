/*
 * Copyright (c) 2016 seleniumQuery authors
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

package io.github.seleniumquery.by.firstgen.xpath.component;

/**
 * cssA ~ cssB
 * cssA + cssB
 *
 * In XPath this becomes: "/following-sibling::"
 * This one will be used by the "General Adjacent" and the "Direct Adjacent" selectors
 * (in order to differentiate, the "Direct Adjacent" selector will itself add a [position()=1] to its expression)
 */
public class AdjacentComponent extends XPathComponent {

    public static TagComponent combine(TagComponent one, TagComponent other) {
        AdjacentComponent otherCopyWithModifiedType = new AdjacentComponent(other);
        return new TagComponent(one.xPathExpression,
                                ComponentUtils.joinComponents(one.combinatedComponents, otherCopyWithModifiedType),
                                ComponentUtils.joinFilters(one.elementFilterList, otherCopyWithModifiedType));
    }

    private AdjacentComponent(TagComponent other) {
        super(other.xPathExpression, other.combinatedComponents, other.elementFilterList);
    }

    @Override
    public String mergeIntoExpression(String sourceXPathExpression) {
        return sourceXPathExpression + "/following-sibling::*[self::" + this.xPathExpression+"]";
    }

    @Override
    public String mergeExpressionAsCondition(String sourceXPathExpression) {
        return mergeIntoExpression(sourceXPathExpression);
    }

}