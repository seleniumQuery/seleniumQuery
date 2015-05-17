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

package io.github.seleniumquery.by.csstree.selector;

import io.github.seleniumquery.by.filter.ElementFilterList;
import io.github.seleniumquery.by.locator.CSSLocator;
import io.github.seleniumquery.by.locator.ElementFinder;
import io.github.seleniumquery.by.locator.SQLocatorUtils;
import io.github.seleniumquery.by.locator.XPathLocator;
import org.openqa.selenium.WebDriver;

import static io.github.seleniumquery.by.locator.CSSLocator.fromTag;

/**
 * Element or tag selector. Example: {@code "div"}.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssTagNameSelector implements SQCssSelector {

    private String tagName;

    public SQCssTagNameSelector(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    @Override
    public ElementFinder toSQLocator(WebDriver webDriver) {
        XPathLocator xPathLocator = new XPathLocator(toXPath(), ElementFilterList.FILTER_NOTHING_LIST) {
            @Override
            public String getXPathExpression() {
                return ".//*[" + getRawXPathExpression() + "]";
            }
        };
        return new ElementFinder(webDriver, toCSS(), xPathLocator);
    }

    @Override
    public ElementFinder toSQLocator(ElementFinder leftLocator) {
        CSSLocator combinedCssSelector = leftLocator.getCSSLocator().merge(toCSS());
        String combinedXPathExp = SQLocatorUtils.conditionalSimpleXPathMerge(leftLocator.getXPathExpression(), toXPath());
        return new ElementFinder(combinedCssSelector, combinedXPathExp, leftLocator);
    }

    private String toXPath() {
        return "*".equals(this.tagName) ? "true()" : "self::"+tagName;
    }

    private CSSLocator toCSS() {
        return fromTag(this.tagName);
    }

}