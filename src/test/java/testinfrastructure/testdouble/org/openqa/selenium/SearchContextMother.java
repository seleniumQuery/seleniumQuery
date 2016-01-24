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

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsById;
import org.openqa.selenium.internal.FindsByXPath;
import testinfrastructure.testdouble.PseudoTestDoubleException;

import java.util.Collections;
import java.util.List;

public class SearchContextMother {

    private static class SearchContextDummy extends WebDriverDummy implements SearchContext, FindsById, FindsByXPath {
        @Override public WebElement findElementById(String s) { throw new PseudoTestDoubleException(); }
        @Override public List<WebElement> findElementsById(String s) { throw new PseudoTestDoubleException(); }
        @Override public WebElement findElementByXPath(String s) { throw new PseudoTestDoubleException(); }
        @Override public List<WebElement> findElementsByXPath(String s) { throw new PseudoTestDoubleException(); }
    }

    public static SearchContext createSearchContextThatReturnsWebElementForId(final String configuredId, final WebElement webElement) {
        return new SearchContextDummy() {
            @Override public WebElement findElementById(String id) {
                if (configuredId.equals(id)) {
                    return webElement;
                }
                return null;
            }
        };
    }

    public static SearchContext createSearchContextThatReturnsWebElementsForXPath(final String configuredXPath, final List<WebElement> webElements) {
        return new SearchContextDummy() {
            @Override
            public List<WebElement> findElementsByXPath(String xPath) {
                if (configuredXPath.equals(xPath)) {
                    return webElements;
                }
                return Collections.emptyList();
            }
        };
    }

}
