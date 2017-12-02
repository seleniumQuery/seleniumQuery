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

package io.github.seleniumquery.by.firstgen.xpath;

import io.github.seleniumquery.by.firstgen.xpath.component.TagComponent;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of {@link io.github.seleniumquery.by.firstgen.xpath.component.XPathComponent}. In other words, multiple
 * XPath expressions
 * that should be "composed" or "merged" to become a single expression.
 *
 * @author acdcjunior
 * @author ricardo-sc
 * @since 0.9.0
 */
public class TagComponentList {

    private static final String XPATH_EXPRESSION_OR = " | ";
    private static final String XPATH_CONDITIONAL_OR_START = "(";
    private static final String XPATH_CONDITIONAL_OR_MIDDLE = ") or (";
    private static final String XPATH_CONDITIONAL_OR_END = ")";

    private List<TagComponent> tagComponents;

    TagComponentList(List<TagComponent> tagComponents) {
        this.tagComponents = tagComponents;
    }

    public List<WebElement> findWebElements(SearchContext context) {
        return tagComponents.stream()
            .flatMap(tagComponent -> tagComponent.findWebElements(context).stream())
            .distinct()
            .collect(Collectors.toList());
    }

    public String toXPath() {
        return tagComponents.stream()
            .map(TagComponent::toXPath)
            .collect(Collectors.joining(XPATH_EXPRESSION_OR));
    }

    public String toXPathCondition() {
        String conditionalTagExpressions = tagComponents.stream()
            .map(TagComponent::toXPathCondition)
            .collect(Collectors.joining(XPATH_CONDITIONAL_OR_MIDDLE));
        return XPATH_CONDITIONAL_OR_START + conditionalTagExpressions + XPATH_CONDITIONAL_OR_END;
    }

}
