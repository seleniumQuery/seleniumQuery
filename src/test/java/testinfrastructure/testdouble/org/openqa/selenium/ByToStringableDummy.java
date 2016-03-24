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

package testinfrastructure.testdouble.org.openqa.selenium;

import io.github.seleniumquery.by.SeleniumQueryBy;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import testinfrastructure.testdouble.PseudoTestDoubleException;

import java.util.List;

/**
 * This is a By that will throw exception at any method call, except .toString()
 */
public class ByToStringableDummy extends SeleniumQueryBy {

    public static By createByToStringableDummy() {
        return new ByToStringableDummy();
    }

    private ByToStringableDummy() {
        super("dummy#by");
    }

    @Override
    public List<WebElement> findElements(SearchContext searchContext) {
        throw new PseudoTestDoubleException();
    }

    @Override
    public WebElement findElement(SearchContext context) {
        throw new PseudoTestDoubleException();
    }

    @Override
    public boolean equals(Object o) {
        throw new PseudoTestDoubleException();
    }

    @Override
    public int hashCode() {
        throw new PseudoTestDoubleException();
    }

}
