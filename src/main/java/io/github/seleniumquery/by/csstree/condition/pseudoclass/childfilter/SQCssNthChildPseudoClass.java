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

package io.github.seleniumquery.by.csstree.condition.pseudoclass.childfilter;

import io.github.seleniumquery.by.css.pseudoclasses.PseudoClassSelector;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.SQCssFunctionalPseudoClassCondition;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.locatorgenerationstrategy.MaybeNativelySupportedPseudoClass;
import io.github.seleniumquery.by.locator.CSSLocator;
import io.github.seleniumquery.by.locator.XPathLocator;
import org.openqa.selenium.InvalidSelectorException;

public class SQCssNthChildPseudoClass extends SQCssFunctionalPseudoClassCondition {

    public static final String PSEUDO = "nth-child";

    public MaybeNativelySupportedPseudoClass nthChildPseudoClassLocatorGenerationStrategy = new MaybeNativelySupportedPseudoClass() {
        @Override
        public String pseudoClassForCSSNativeSupportCheck() {
            return ":"+PSEUDO+"(1)";
        }
        @Override
        public CSSLocator toCssWhenNativelySupported() {
            getNthChildArgument();
            return new CSSLocator(PSEUDO);
        }
        @Override
        public XPathLocator toXPath() {
            getNthChildArgument();
            return null;
        }
    };

    public SQCssNthChildPseudoClass(PseudoClassSelector pseudoClassSelector) {
        super(pseudoClassSelector);
    }

    @Override
    public MaybeNativelySupportedPseudoClass getSQCssLocatorGenerationStrategy() {
        return nthChildPseudoClassLocatorGenerationStrategy;
    }

    private static class NthChildArgument {
        public NthChildArgument(String argument) {
            String reason = String.format("The :%s() pseudo-class requires an integer as argument but got: \"%s\".",
                    PSEUDO, argument);
            throw new InvalidSelectorException(reason);
        }
//        static final NthChildArgument ODD = new NthChildArgument(2,1);
//        static final NthChildArgument EVEN = new NthChildArgument(2,0);
//        final int a;
//        final int b;
//        NthChildArgument(int b) {
//            this(0, b);
//        }
//        NthChildArgument(int a, int b) {
//            this.a = a;
//            this.b = b;
//        }
    }

    private NthChildArgument getNthChildArgument() {
        return new NthChildArgument(super.getArgument());
    }

}