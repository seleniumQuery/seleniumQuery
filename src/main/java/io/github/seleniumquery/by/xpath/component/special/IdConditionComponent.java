package io.github.seleniumquery.by.xpath.component.special;

import io.github.seleniumquery.by.filter.ElementFilterList;
import io.github.seleniumquery.by.xpath.component.ComponentUtils;
import io.github.seleniumquery.by.xpath.component.SimpleConditionalComponent;
import io.github.seleniumquery.by.xpath.component.XPathComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class IdConditionComponent extends SimpleConditionalComponent {

    private final String wantedId;

    public IdConditionComponent(String id) {
        super(idXpath(id));
        this.wantedId = id;
    }

    private static String idXpath(String id) {
        return "[@id = '" + id + "']";
    }

    private IdConditionComponent(String id, List<XPathComponent> combinatedComponents, ElementFilterList elementFilterList) {
        super(idXpath(id), combinatedComponents, elementFilterList);
        this.wantedId = id;
    }

    public IdConditionComponent cloneComponent() {
        return new IdConditionComponent(this.wantedId);
    }

    public List<WebElement> findWebElements(SearchContext context) {
        ArrayList<WebElement> webElements = new ArrayList<WebElement>();
        try {
            webElements.add(By.id(wantedId).findElement(context));
        } catch (NoSuchElementException ignored) { }
        return webElements;
    }

    public IdConditionComponent cloneAndCombine(XPathComponent other) {
        XPathComponent otherCopy = ComponentUtils.cloneComponent(other);
        return new IdConditionComponent(this.wantedId,
                                    ComponentUtils.combineComponents(this.combinatedComponents, otherCopy),
                                        ComponentUtils.combineFilters(this.elementFilterList, otherCopy));
    }

}