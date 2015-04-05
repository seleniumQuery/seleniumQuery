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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQCssNthChildPseudoClass extends SQCssFunctionalPseudoClassCondition {

    public static final String PSEUDO = "nth-child";

    public MaybeNativelySupportedPseudoClass nthChildPseudoClassLocatorGenerationStrategy = new MaybeNativelySupportedPseudoClass() {
        @Override
        public String pseudoClassForCSSNativeSupportCheck() {
            return ":"+PSEUDO+"(1)";
        }
        @Override
        public CSSLocator toCssWhenNativelySupported() {
            NthChildArgument nthChildArgument = getNthChildArgument();
            return new CSSLocator(nthChildArgument.toCSS());
        }
        @Override
        public XPathLocator toXPath() {
            NthChildArgument nthChildArgument = getNthChildArgument();
            return XPathLocator.pureXPath(nthChildArgument.toXPath());
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
        private final Integer a;
        private final Integer b;

        public NthChildArgument(String argument) {
            String trimmedArg = argument.trim();
            if (trimmedArg.matches("[+-]?\\d+")) {
                this.a = null;
                this.b = toInt(trimmedArg);
            } else {
                Pattern p = Pattern.compile("(\\d*)n(?:\\s*([+-]\\s*\\d+))?");
                Matcher m = p.matcher(trimmedArg);
                if (m.matches()) {
                    String aString = m.group(1);
                    if (aString.isEmpty()) {
                        aString = "1";
                    }
                    this.a = toInt(aString);
                    String bString = m.group(2);
                    if (bString == null) {
                        this.b = null;
                    } else {
                        this.b = toInt(bString);
                    }
                } else {
                    throw createInvalidArgumentException(argument);
                }
            }
        }

        private int toInt(String supposedInteger) {
            String supposedIntegerWithoutSpaces = supposedInteger.replaceAll("\\s", "");
            char firstChar = supposedIntegerWithoutSpaces.charAt(0);
            if (firstChar == '+') {
                return toInt(supposedIntegerWithoutSpaces.substring(1));
            }
            return Integer.parseInt(supposedIntegerWithoutSpaces);
        }

        private InvalidSelectorException createInvalidArgumentException(String argument) {
            String reason = String.format("The :nth-child() pseudo-class must have an argument like" +
                    " :nth-child(odd), :nth-child(even), :nth-child(an+b), :nth-child(an) or" +
                    " :nth-child(b) - where a and b are positive or negative integers -, but was :nth-child(%s).", argument);
            return new InvalidSelectorException(reason);
        }
        public String toCSS() {
            String sa = a != null ? a+"n" : "";
            String sb = b != null ? (b > 0 && a != null? "+"+b : ""+b) : "";
            return ":nth-child("+sa+sb+")";
        }
        public String toXPath() {
            int realA = a != null ? a : 0;
            if (realA == 0) {
                return "position() = "+b;
            }
            int realB = b != null ? b : 0;
            return "(position() - " + realB + ") mod " + realA + " = 0 and position() >= " + realB;
        }

        //        static final NthChildArgument ODD = new NthChildArgument(2,1);
//        static final NthChildArgument EVEN = new NthChildArgument(2,0);
//        final int a;
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