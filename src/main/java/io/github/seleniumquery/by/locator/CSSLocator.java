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

package io.github.seleniumquery.by.locator;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Given the selector:
 * div > p:visible
 *
 * "div > " would be leftPart
 * "p" would be tag
 * ":visible" would be rightPart
 *
 *
 * If, during the time the locator is being built, at some point it decides the CSS Selector asked
 * by the user can't be translated directly into another CSS Selector, then
 * {@link CSSLocator#CSS_NOT_NATIVELY_SUPPORTED} (the "Null Object") should be returned, what will
 * mean the locator will use XPath+Filter instead of CSS to fetch the elements.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CSSLocator {

    public static final String UNIVERSAL_SELECTOR = "*";

    public static CSSLocator fromTag(String tag) {
        return new CSSLocator("", tag, "");
    }
    public static CSSLocator universalSelector() {
        return fromTag(UNIVERSAL_SELECTOR);
    }

    public static final CSSLocator CSS_NOT_NATIVELY_SUPPORTED = new CSSLocator("", UNIVERSAL_SELECTOR, "") {
        @Override
        public CSSLocator merge(CSSLocator rightSCssSelector) {
            return this;
        }
        @Override
        public boolean canFetchAllElementsOfTheQueryByItself() {
            return false;
        }
    };

    private String leftPart;
    private String tag;
    private String rightPart;

    public CSSLocator(String leftPart, String tag, String rightPart) {
        this.leftPart = leftPart;
        this.tag = tag;
        this.rightPart = rightPart;
    }

    public CSSLocator(String tag, String rightPart) {
        this("", tag, rightPart);
    }

    public CSSLocator(String rightPart) {
        this("", UNIVERSAL_SELECTOR, rightPart);
    }

    public boolean hasUniversalSelector() {
        return UNIVERSAL_SELECTOR.equals(tag);
    }

    public String getLeftPart() {
        return leftPart;
    }

    public String getTag() {
        return tag;
    }

    public String getRightPart() {
        return rightPart;
    }

    @Override
    public String toString() {
        if (hasUniversalSelector() && !rightPart.isEmpty()) {
            return leftPart + rightPart;
        }
        return leftPart + tag + rightPart;
    }

    /**
     * Merges two CSS selector parts into one.
     * The current instance will be the left part of the merged selector.
     *
     * @param rightSCssSelector The right part of the merged selector.
     * @return The two parts merged as a CSS selector.
     */
    public CSSLocator merge(CSSLocator rightSCssSelector) {
        if (!rightSCssSelector.canFetchAllElementsOfTheQueryByItself()) {
            return CSS_NOT_NATIVELY_SUPPORTED;
        }
        if (selectorsHaveDifferentTags(this, rightSCssSelector) &&
                noneOfTheTagsIsTheUniversalSelector(this, rightSCssSelector)) {
            throw new IllegalArgumentException("The attempted selector has two element (tag) selectors at the same level. " +
                    "It is incorrect and would never fetch any elements (as no element has more than one tag).");
        }
        if (this.hasUniversalSelector()) {
            return new CSSLocator(
                    this.getLeftPart(),
                    rightSCssSelector.getTag(),
                    this.getRightPart() + rightSCssSelector.getRightPart()
            );
        } else {
            return new CSSLocator(
                    this.getLeftPart(),
                    this.getTag(),
                    this.getRightPart() + rightSCssSelector.getRightPart()
            );
        }
    }

    private static boolean selectorsHaveDifferentTags(CSSLocator leftCssSelector, CSSLocator rightSCssSelector) {
        return !leftCssSelector.getTag().equals(rightSCssSelector.getTag());
    }

    private static boolean noneOfTheTagsIsTheUniversalSelector(CSSLocator leftCssSelector, CSSLocator rightSCssSelector) {
        return !leftCssSelector.hasUniversalSelector() && !rightSCssSelector.hasUniversalSelector();
    }

    public boolean canFetchAllElementsOfTheQueryByItself() {
        return true;
    }

    // TODO this method is not unit tested, it was inserted during refactoring, so it MAY have some test (I'm too lazy to check).
    // we need to test the IF condition, though, as it was kind of inserted during refactoring
    // so, yeah, just do the unit test for the if and then be happy!
    // TODO actually, maybe this method should be inlined (and the test moved to the class as well)
    public CSSLocator combineAsLeftPart(String combinator) {
        if (this.canFetchAllElementsOfTheQueryByItself()) {
            return new CSSLocator(this.toString() + combinator, UNIVERSAL_SELECTOR, "");
        }
        return CSS_NOT_NATIVELY_SUPPORTED;
    }

    // TODO dont know if this has unit tests
    public List<WebElement> findElements(SearchContext context) {
        String finalCssSelector = this.toString();
        return new By.ByCssSelector(finalCssSelector).findElements(context);
    }

}