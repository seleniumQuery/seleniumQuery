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

package io.github.seleniumquery.by.csstree.condition.pseudoclass.basicfilter;

import io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassAssertLocatorUtils;
import org.junit.Test;

import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassAssertLocatorUtils.assertPseudoClassHasLocatorWhenNativelySupported;
import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassAssertLocatorUtils.assertPseudoClassHasLocatorWhenNotNativelySupported;
import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.assertFunctionalPseudo;
import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.createPseudoClassSelectorAppliedToUniversalSelector;
import static org.hamcrest.collection.IsEmptyCollection.empty;

public class SQCssNotPseudoClassTest {

    private static final String NOT_PSEUDO = ":not";

    @Test
    public void translate() {
        assertFunctionalPseudo(NOT_PSEUDO, SQCssNotPseudoClass.class);
    }

    @Test
    public void toSQLocator__when_driver_has_native_support() {
        assertPseudoClassHasLocatorWhenNativelySupported(
                ":not(div)",
                new SQCssNotPseudoClass(createPseudoClassSelectorAppliedToUniversalSelector("span")), // :not(span)
                ":not(span)",
                PseudoClassAssertLocatorUtils.PURE_CSS_IS_SUPPORTED,
                ".//*[not(self::span)]",
                empty()
        );
    }

    @Test
    public void toSQLocator__when_driver_does_NOT_have_native_support() {
        assertPseudoClassHasLocatorWhenNotNativelySupported(
                ":not(div)",
                new SQCssNotPseudoClass(createPseudoClassSelectorAppliedToUniversalSelector("tag")), // :not(tag)
                PseudoClassAssertLocatorUtils.CSS_UNIVERSAL_SELECTOR,
                PseudoClassAssertLocatorUtils.PURE_CSS_IS_NOT_SUPPORTED,
                ".//*[not(self::tag)]",
                empty()
        );
    }

    @Test
    public void toSQLocator__when_driver_has_native_support_BUT_inner_css_can_be_separated() {
        assertPseudoClassHasLocatorWhenNativelySupported(
                ":not(div)",
                new SQCssNotPseudoClass(createPseudoClassSelectorAppliedToUniversalSelector("h1,h2")), // :not(h1,h2)
                ":not(h1):not(h2)",
                PseudoClassAssertLocatorUtils.PURE_CSS_IS_SUPPORTED,
                ".//*[not(self::h1 | self::h2)]",
                empty()
        );
    }

    /*
     * The selectors below are much more complicated than I anticipated. When inside the not(),
     * the XPath conditional must be "reversed" to have the same semantics of the :not() in CSS.
     * e.g. :not(h3 h4) in CSS does not become not(h3//h4) in XPath, it becomes not(h4 and ancestor::h3
     *
     * Due to that, below I just leave a sketch of what the expected XPath conditionals
     * inside the not() would be.
     *
     * The tests just expect that an exception is thrown, letting the user know that is not
     * a supported selector.
     * If this was the final expected behavior, these tests could be much simpler, of course. I just
     * left them like this because I expect to get back to them as soon as possible.
     */

    @Test(expected = io.github.seleniumquery.by.css.pseudoclasses.UnsupportedPseudoClassException.class)
    public void toSQLocator__when_driver_has_native_support_BUT_inner_css_CANT_be_separated() {
        assertPseudoClassHasLocatorWhenNativelySupported(
                ":not(div)",
                new SQCssNotPseudoClass(createPseudoClassSelectorAppliedToUniversalSelector("h3 h4")), // :not(h3 h4)
                "*",
                PseudoClassAssertLocatorUtils.PURE_CSS_IS_NOT_SUPPORTED,
                ".//*[not(self::h4 and ANY-ANCESTOR/*[self::h3])]",
                empty()
        );
    }

    @Test(expected = io.github.seleniumquery.by.css.pseudoclasses.UnsupportedPseudoClassException.class)
    public void toSQLocator__not_and_direct_ancestor() {
        assertPseudoClassHasLocatorWhenNativelySupported(
                ":not(div)",
                new SQCssNotPseudoClass(createPseudoClassSelectorAppliedToUniversalSelector("h3>h4")), // :not(h3>h4)
                "*",
                PseudoClassAssertLocatorUtils.PURE_CSS_IS_NOT_SUPPORTED,
                ".//*[not(self::h4 and DIRECT-ANCESTOR/*[self::h3])]",
                empty()
        );
    }

    @Test(expected = io.github.seleniumquery.by.css.pseudoclasses.UnsupportedPseudoClassException.class)
    public void toSQLocator__not_and_direct_sibling() {
        assertPseudoClassHasLocatorWhenNativelySupported(
                ":not(div)",
                new SQCssNotPseudoClass(createPseudoClassSelectorAppliedToUniversalSelector("h3+h4")), // :not(h3+h4)
                "*",
                PseudoClassAssertLocatorUtils.PURE_CSS_IS_NOT_SUPPORTED,
                ".//*[not(self::h4 and DIRECT-SIBLING/*[self::h3])]",
                empty()
        );
    }

    @Test(expected = io.github.seleniumquery.by.css.pseudoclasses.UnsupportedPseudoClassException.class)
    public void toSQLocator__not_and_general_sibling() {
        assertPseudoClassHasLocatorWhenNativelySupported(
                ":not(div)",
                new SQCssNotPseudoClass(createPseudoClassSelectorAppliedToUniversalSelector("h3+h4")), // :not(h3~h4)
                "*",
                PseudoClassAssertLocatorUtils.PURE_CSS_IS_NOT_SUPPORTED,
                ".//*[not(self::h4 and GENERAL-SIBLING/*[self::h3])]",
                empty()
        );
    }

}