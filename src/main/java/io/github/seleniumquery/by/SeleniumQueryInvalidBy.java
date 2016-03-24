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

package io.github.seleniumquery.by;

import io.github.seleniumquery.SeleniumQueryException;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Denotes an invalid By. Used to be a placeholder and to allow pretty printing of the seleniumQuery object.
 *
 * @author acdcjunior
 * @since 0.15.0
 */
public class SeleniumQueryInvalidBy extends SeleniumQueryBy {

    /**
     * A {@link By} to be used in an element created with no By. Attempting to filter elements through this
     * will throw an exception.
     * @since 0.9.0
     */
    public static final SeleniumQueryBy UNAVAILABLE_BY = new SeleniumQueryInvalidBy();

    private String sourceString;
    private String suffix;

    private SeleniumQueryInvalidBy() {
        super("<unavailable selector>");
    }

    public SeleniumQueryInvalidBy(By sourceBy, String suffix) {
        super(sourceBy.toString());
        this.sourceString = sourceBy.toString();
        this.suffix = suffix;
    }

    @Override public List<WebElement> findElements(SearchContext context) {
        throw new SeleniumQueryException("This seleniumQuery object was not created using a selector directly. Due to that, you cannot search " +
                "elements based on it, as that selector string used to match it is not available.\n\n" +
                "Possible workarounds:\n" +
                " - Try selecting the element by a supported selector (and not use .find() or .filter());\n" +
                " - Try not using more than one .waitUntil() in a single line.");
    }

    @Override
    public String toString() {
        return sourceString + suffix;
    }
}
