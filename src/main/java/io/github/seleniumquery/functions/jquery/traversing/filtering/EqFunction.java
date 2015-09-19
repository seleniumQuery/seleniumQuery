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

package io.github.seleniumquery.functions.jquery.traversing.filtering;

import io.github.seleniumquery.InternalSeleniumQueryObjectFactory;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.by.SeleniumQueryBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * http://api.jquery.com/eq/
 *
 * $("selector").eq(#)
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class EqFunction {
	
	public static SeleniumQueryObject eq(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements, int index) {
		ArrayList<WebElement> eqElementList = new ArrayList<>();
		
		By selectorAtIndex = getByForElementAtPosition(seleniumQueryObject.getBy(), index);
		if (index < 0) {
			if (elements.size() >= -index) {
				eqElementList.add(elements.get(elements.size() + index));
				selectorAtIndex = getByForElementAtPosition(seleniumQueryObject.getBy(), elements.size() + index);
			}
		} else {
			if (elements.size() > index) {
				eqElementList.add(elements.get(index));
			}
		}

        return InternalSeleniumQueryObjectFactory.instance().create(seleniumQueryObject.getWebDriver(), selectorAtIndex, eqElementList, seleniumQueryObject);
    }

	/**
	 * Zero-based.
	 *
	 * @param by By that should be "positioned".
     * @param position Zero-based index of the wanted element.
     * @return the selector string for the element at <code>position</code>.
     *
	 * @since 0.10.0
	 */
	private static By getByForElementAtPosition(By by, int position) {
        if (!(by instanceof SeleniumQueryBy)) {
            return SeleniumQueryBy.NO_SELECTOR_INVALID_BY;
        }
        SeleniumQueryBy seleniumQueryBy = (SeleniumQueryBy) by;
        if (seleniumQueryBy.isXPathExpression()) {
            return byXPathAt(seleniumQueryBy.getSelector(), position);
        }
        return byCssEq(seleniumQueryBy.getSelector(), position);
    }

    private static SeleniumQueryBy byXPathAt(String expression, int position) {
        // notice that, in XPath, the position is one-based.
        return SeleniumQueryBy.byEnhancedSelector("("+ expression +")["+ (position+1) +"]");
    }

    private static By byCssEq(String selector, int position) {
        return SeleniumQueryBy.byEnhancedSelector(selector +":eq("+position+")");
    }

}