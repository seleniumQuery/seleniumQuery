package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.filter.ElementFilterList;

import java.util.List;

public abstract class ConditionComponent extends XPathComponent {
    ConditionComponent(String xPathExpression, ElementFilterList elementFilterList) {
        super(xPathExpression, elementFilterList);
    }
    public ConditionComponent(String xPathExpression, List<XPathComponent> combinatedComponents, ElementFilterList elementFilterList) {
        super(xPathExpression, combinatedComponents, elementFilterList);
    }
}