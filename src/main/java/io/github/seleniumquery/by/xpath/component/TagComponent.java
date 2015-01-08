package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.filter.ElementFilterList;
import io.github.seleniumquery.by.xpath.CssCombinationType;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TagComponent extends XPathComponent {

    public TagComponent(String xPathExpression) {
        super(xPathExpression, ComponentUtils.toElementFilterList(ElementFilter.FILTER_NOTHING));
    }

    TagComponent(String xPathExpression, List<XPathComponent> combinatedComponents, ElementFilterList elementFilterList) {
        super(xPathExpression, combinatedComponents, elementFilterList);
    }

    public List<WebElement> findWebElements(SearchContext context) {
        String finalXPathExpression = this.toXPath();
        List<WebElement> elements = new By.ByXPath(finalXPathExpression).findElements(context);
        WebDriver driver = SelectorUtils.getWebDriver(context);
        return elementFilterList.filter(driver, elements);
    }

    public String toXPath() {
        String xPathExpression;
        if ("*".equals(this.xPathExpression)) {
            xPathExpression = ".//*";
        } else {
            xPathExpression = ".//*[self::" + this.xPathExpression+"]";
        }
        ElementFilterList elementFilterList = this.elementFilterList; // should be a copy

        for (XPathComponent other : combinatedComponents) {
            elementFilterList = other.mergeIntoFilter(elementFilterList);
            xPathExpression = other.mergeIntoExpression(xPathExpression);
        }
        return "(" + xPathExpression + ")";
    }

    public String toXPathCondition() {
        String xPathExpression;
        if ("*".equals(this.xPathExpression)) {
            xPathExpression = CssCombinationType.MATCH_EVERYTHING_XPATH_CONDITIONAL;
        } else {
            xPathExpression = "local-name() = '"+this.xPathExpression+"'";
        }
        ElementFilterList elementFilterList = this.elementFilterList; // should be a copy

        for (XPathComponent other : combinatedComponents) {
            elementFilterList = other.mergeFilterAsCondition(elementFilterList);
            xPathExpression = other.mergeExpressionAsCondition(xPathExpression);
        }
        return xPathExpression;
    }

    @Override
    public String mergeIntoExpression(String sourceXPathExpression) {
        return CssCombinationType.TAG.merge(sourceXPathExpression, this.xPathExpression);
    }

    @Override
    public String mergeExpressionAsCondition(String sourceXPathExpression) {
        return CssCombinationType.TAG.mergeAsCondition(sourceXPathExpression, this.xPathExpression);
    }

}