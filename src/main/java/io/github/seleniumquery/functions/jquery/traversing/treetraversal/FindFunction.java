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

package io.github.seleniumquery.functions.jquery.traversing.treetraversal;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.by.SeleniumQueryBy;
import io.github.seleniumquery.by.SeleniumQueryInvalidBy;
import io.github.seleniumquery.internal.SqObjectFactory;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

/**
 * $("selector").find("selector")
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class FindFunction {

	public static SeleniumQueryObject find(SeleniumQueryObject seleniumQueryObject, String selector) {
		List<WebElement> elements = seleniumQueryObject.get();
		SeleniumQueryBy by = SeleniumQueryBy.byEnhancedSelector(selector);
        List<WebElement> allElementsBelow = findOnElements(elements, by);
		return SqObjectFactory.instance().create(
				seleniumQueryObject.getWebDriver(),
                new SeleniumQueryInvalidBy(seleniumQueryObject.getBy(), ".find(\""+selector+"\")"),
                allElementsBelow,
                seleniumQueryObject);
	}

    private static List<WebElement> findOnElements(List<WebElement> elements, SeleniumQueryBy by) {
        List<WebElement> allElementsBelow = new LinkedList<>();
        for (WebElement webElement : elements) {
            List<WebElement> elementsBelowThisElement = webElement.findElements(by);
            allElementsBelow.addAll(elementsBelowThisElement);
        }
        return allElementsBelow;
    }

}