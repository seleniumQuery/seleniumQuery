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

    public List<WebElement> findWebElements(SearchContext context) {
        ArrayList<WebElement> webElements = new ArrayList<WebElement>();
        try {
            webElements.add(By.id(wantedId).findElement(context));
        } catch (NoSuchElementException ignored) { }
        return webElements;
    }

    @Override
    public IdConditionComponent cloneComponent() {
        return new IdConditionComponent(this.wantedId);
    }

    @Override
    public IdConditionComponent cloneAndCombineTo(Combinable other) {
        XPathComponent otherCopy = other.cloneComponent();
        return new IdConditionComponent(this.wantedId,
                                    ComponentUtils.joinComponents(this.combinatedComponents, otherCopy),
                                        ComponentUtils.joinFilters(this.elementFilterList, otherCopy));
    }

}