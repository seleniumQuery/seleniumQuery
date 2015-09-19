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

package io.github.seleniumquery.by.firstgen.xpath.component.special;

import io.github.seleniumquery.by.common.elementfilter.ElementFilterList;
import io.github.seleniumquery.by.firstgen.xpath.component.Combinable;
import io.github.seleniumquery.by.firstgen.xpath.component.ComponentUtils;
import io.github.seleniumquery.by.firstgen.xpath.component.ConditionSimpleComponent;
import io.github.seleniumquery.by.firstgen.xpath.component.XPathComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class IdConditionComponent extends ConditionSimpleComponent {

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
        ArrayList<WebElement> webElements = new ArrayList<>();
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