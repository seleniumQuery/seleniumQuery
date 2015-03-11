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

/**
 * Given the selector:
 * div > p:visible
 *
 * "div > " would be leftPart
 * "p" would be tag
 * ":visible" would be rightPart
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQLocatorCss {

    public static final String UNIVERSAL_SELECTOR = "*";

    public static SQLocatorCss fromLeftPart(String leftPart, CanFetchAllElementsOfTheQueryByItself canPureCss) {
        return new SQLocatorCss(leftPart, UNIVERSAL_SELECTOR, "", canPureCss);
    }
    public static SQLocatorCss fromTag(String tag) {
        return new SQLocatorCss("", tag, "", CanFetchAllElementsOfTheQueryByItself.YES);
    }
    public static SQLocatorCss universalSelector() {
        return fromTag(UNIVERSAL_SELECTOR);
    }

    public static enum CanFetchAllElementsOfTheQueryByItself { YES, NO }

    private String leftPart;
    private String tag;
    private String rightPart;
    private CanFetchAllElementsOfTheQueryByItself canFetchAllElementsOfTheQueryByItself;

    public SQLocatorCss(String leftPart, String tag, String rightPart, CanFetchAllElementsOfTheQueryByItself canFetchAllElementsOfTheQueryByItself) {
        this.leftPart = leftPart;
        this.tag = tag;
        this.rightPart = rightPart;
        this.canFetchAllElementsOfTheQueryByItself = canFetchAllElementsOfTheQueryByItself;
    }

    public SQLocatorCss(String tag, String rightPart, CanFetchAllElementsOfTheQueryByItself canFetchAllElementsOfTheQueryByItself) {
        this("", tag, rightPart, canFetchAllElementsOfTheQueryByItself);
    }

    public SQLocatorCss(String rightPart, CanFetchAllElementsOfTheQueryByItself canFetchAllElementsOfTheQueryByItself) {
        this("", UNIVERSAL_SELECTOR, rightPart, canFetchAllElementsOfTheQueryByItself);
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

    public SQLocatorCss mergeUsingCurrentNativeness(SQLocatorCss rightSCssSelector) {
        return merge(rightSCssSelector, this.canFetchAllElementsOfTheQueryByItself);
    }

    /**
     * Merges two CSS selector parts into one.
     * The current instance will be the left part of the merged selector.
     * @param rightSCssSelector The right part of the merged selector.
     * @param canPureCss If the ending selector is a natively suported CSS selector.
     * @return The two parts merged as a CSS selector.
     */
    public SQLocatorCss merge(SQLocatorCss rightSCssSelector, CanFetchAllElementsOfTheQueryByItself canPureCss) {
        if (selectorsHaveDifferentTags(this, rightSCssSelector) &&
                noneOfTheTagsIsTheUniversalSelector(this, rightSCssSelector)) {
            throw new IllegalArgumentException("The attempted selector has two element (tag) selectors at the same level. " +
                    "It is incorrect and would never fetch any elements (as no element has more than one tag).");
        }
        if (this.hasUniversalSelector()) {
            return new SQLocatorCss(
                    this.getLeftPart(),
                    rightSCssSelector.getTag(),
                    this.getRightPart() + rightSCssSelector.getRightPart(),
                    canPureCss
            );
        } else {
            return new SQLocatorCss(
                    this.getLeftPart(),
                    this.getTag(),
                    this.getRightPart() + rightSCssSelector.getRightPart(),
                    canPureCss
            );
        }
    }

    private static boolean selectorsHaveDifferentTags(SQLocatorCss leftCssSelector, SQLocatorCss rightSCssSelector) {
        return !leftCssSelector.getTag().equals(rightSCssSelector.getTag());
    }

    private static boolean noneOfTheTagsIsTheUniversalSelector(SQLocatorCss leftCssSelector, SQLocatorCss rightSCssSelector) {
        return !leftCssSelector.hasUniversalSelector() && !rightSCssSelector.hasUniversalSelector();
    }

    public CanFetchAllElementsOfTheQueryByItself canFetchAllElementsOfTheQueryByItself() {
        return canFetchAllElementsOfTheQueryByItself;
    }

}