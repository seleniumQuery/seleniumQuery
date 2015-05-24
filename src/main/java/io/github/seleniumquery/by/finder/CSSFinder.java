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

package io.github.seleniumquery.by.finder;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * This object is capable of finding {@link WebElement}s through CSS on a given {@link SearchContext}.<br><br>
 *
 * Components:
 *  Given the selector: "* div > p:visible"
 *
 *  Then:
 *   "div > " would be leftPart
 *   "p" would be tag
 *   ":visible" would be rightPart
 *
 *
 * If, during the time this finder is being built, at some point it decides the CSS Selector asked
 * by the user can't be translated directly into another CSS Selector (e.g. it is an extended selector, such as :hidden),
 * then {@link CSSFinder#CSS_NOT_NATIVELY_SUPPORTED} (the "Null Object") should be returned, what will
 * mean the element finder will use XPath(+Filter) instead of CSS to fetch the elements.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CSSFinder {

    public static final String UNIVERSAL_SELECTOR = "*";

    public static CSSFinder fromTag(String tag) {
        return new CSSFinder("", tag, "");
    }
    public static CSSFinder universalSelector() {
        return fromTag(UNIVERSAL_SELECTOR);
    }

    public static final CSSFinder CSS_NOT_NATIVELY_SUPPORTED = new CSSFinder("", UNIVERSAL_SELECTOR, "") {
        @Override
        public CSSFinder merge(CSSFinder rightCSS) {
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

    public CSSFinder(String leftPart, String tag, String rightPart) {
        this.leftPart = leftPart;
        this.tag = tag;
        this.rightPart = rightPart;
    }

    public CSSFinder(String tag, String rightPart) {
        this("", tag, rightPart);
    }

    public CSSFinder(String rightPart) {
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
     * @param rightCSS The right part of the merged selector.
     * @return The two parts merged as a CSS selector.
     */
    public CSSFinder merge(CSSFinder rightCSS) {
        if (!rightCSS.canFetchAllElementsOfTheQueryByItself()) {
            return CSS_NOT_NATIVELY_SUPPORTED;
        }
        if (selectorsHaveDifferentTags(this, rightCSS) && noneOfTheTagsIsTheUniversalSelector(this, rightCSS)) {
            throw new IllegalArgumentException("The attempted selector has two element (tag) selectors at the same level. " +
                    "It is incorrect and would never fetch any elements (as no element has more than one tag).");
        }
        if (this.hasUniversalSelector()) {
            return new CSSFinder(
                    this.getLeftPart(),
                    rightCSS.getTag(),
                    this.getRightPart() + rightCSS.getRightPart()
            );
        } else {
            return new CSSFinder(
                    this.getLeftPart(),
                    this.getTag(),
                    this.getRightPart() + rightCSS.getRightPart()
            );
        }
    }

    private static boolean selectorsHaveDifferentTags(CSSFinder leftCssSelector, CSSFinder rightSCssSelector) {
        return !leftCssSelector.getTag().equals(rightSCssSelector.getTag());
    }

    private static boolean noneOfTheTagsIsTheUniversalSelector(CSSFinder leftCssSelector, CSSFinder rightSCssSelector) {
        return !leftCssSelector.hasUniversalSelector() && !rightSCssSelector.hasUniversalSelector();
    }

    public boolean canFetchAllElementsOfTheQueryByItself() {
        return true;
    }

    // TODO this method is not unit tested, it was inserted during refactoring, so it MAY have some test (I'm too lazy to check).
    // we need to test the IF condition, though, as it was kind of inserted during refactoring
    // so, yeah, just do the unit test for the if and then be happy!
    // TODO actually, maybe this method should be inlined (and the test moved to the class as well)
    public CSSFinder combineAsLeftPart(String combinator) {
        if (this.canFetchAllElementsOfTheQueryByItself()) {
            return new CSSFinder(this.toString() + combinator, UNIVERSAL_SELECTOR, "");
        }
        return CSS_NOT_NATIVELY_SUPPORTED;
    }

    // TODO dont know if this has unit tests
    public List<WebElement> findElements(SearchContext context) {
        String finalCssSelector = this.toString();
        return new By.ByCssSelector(finalCssSelector).findElements(context);
    }

}