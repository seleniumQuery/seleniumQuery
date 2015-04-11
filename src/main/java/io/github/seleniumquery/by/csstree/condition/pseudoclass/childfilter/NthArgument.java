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

import org.openqa.selenium.InvalidSelectorException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents arguments in the "nth" format, such as {@code even}, {@code odd} or {@code an+b}.
 *
 * NOTE: The current implementation was built to work in a generic form and does the job perfectly. Still,
 * it is a generic "raw" implementation, meaning it does no "optimizations", such as converting 2n+2 into 2n,
 * or absurd arguments (e.g. -2n-2) into XPath's {@code false()}. We did this right now because we don't see
 * the need those optimizations right now. If that changes in the future, they should be easy to add.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
class NthArgument {

    private static final Pattern B_REGEX = Pattern.compile("[+-]?\\d+");
    private static final Pattern ANB_REGEX = Pattern.compile("([+-]?\\d*)n(?:\\s*([+-]\\s*\\d+))?");

    private final Integer a;
    private final Integer b;

    public NthArgument(String argument) {
        String trimmedArg = argument.trim();
        if (even(trimmedArg)) {
            this.a = 2;
            this.b = null;
        } else if (odd(trimmedArg)) {
            this.a = 2;
            this.b = 1;
        } else if (bOnly(trimmedArg)) {
            this.a = null;
            this.b = parseSupposedInt(trimmedArg);
        } else if (anb(trimmedArg)) {
            Matcher m = extractArgumentsFromRegexGroups(trimmedArg);
            this.a = a(m.group(1));
            this.b = b(m.group(2));
        } else {
            throw createInvalidArgumentException(argument);
        }
    }

    /**
     * Tests if the argument is "even", as in :nth-child(even) or :nth-last-child(even)
     */
    private boolean even(String trimmedArg) {
        return "even".equals(trimmedArg);
    }

    /**
     * Tests if the argument is "odd", as in :nth-child(odd) or :nth-last-child(odd)
     */
    private boolean odd(String trimmedArg) {
        return "odd".equals(trimmedArg);
    }

    /**
     * Tests if arguments is just b, as in :nth-child(5) or :nth-last-child(6)
     */
    private boolean bOnly(String trimmedArg) {
        return B_REGEX.matcher(trimmedArg).matches();
    }

    /**
     * Tests if arguments is an+b, as in :nth-child(3n+8) or :nth-last-child(-2n+3)
     */
    private boolean anb(String trimmedArg) {
        return ANB_REGEX.matcher(trimmedArg).matches();
    }

    private int a(String aString) {
        if (aString.isEmpty()) {
            return 1;
        }
        if ("-".equals(aString)) {
            return -1;
        }
        if ("+".equals(aString)) {
            return 1;
        }
        return parseSupposedInt(aString);
    }

    private Integer b(String bString) {
        if (bString == null) {
            return null;
        }
        return parseSupposedInt(bString);
    }

    private int parseSupposedInt(String supposedInteger) {
        String intWithoutSpaces = supposedInteger.replaceAll("\\s", "");
        String intWithoutSpacesAndLeadingPlusSign = intWithoutSpaces.replaceAll("^\\+", "");
        return Integer.parseInt(intWithoutSpacesAndLeadingPlusSign);
    }

    private Matcher extractArgumentsFromRegexGroups(String trimmedArg) {
        Matcher m = ANB_REGEX.matcher(trimmedArg);
        // we know it matches, otherwise this method would not have been called
        // I will not put an IF here "because another method may call this". If another method
        // ever calls this, then whoever made it call this place an if here!
        //noinspection ResultOfMethodCallIgnored
        m.matches();
        return m;
    }

    private InvalidSelectorException createInvalidArgumentException(String argument) {
        String reason = String.format("The :nth-child() pseudo-class must have an argument like" +
                " :nth-child(odd), :nth-child(even), :nth-child(an+b), :nth-child(an) or" +
                " :nth-child(b) - where a and b are positive or negative integers -, but was :nth-child(%s).", argument);
        return new InvalidSelectorException(reason);
    }

    public String toCSS() {
        String sa = a != null ? a+"n" : "";
        String sb = b != null && b != 0 ? (b > 0 && a != null? "+"+b : ""+b) : "";
        return ":nth-child("+sa+sb+")";
    }

    public String toXPath() {
        int realA = a != null ? a : 0;
        if (realA == 0) {
            return "position() = "+b;
        }
        int realB = b != null ? b : 0;
        char operator = realA < 0 ? '<' : '>';
        return "(position() - " + realB + ") mod " + realA + " = 0 and position() "+operator+"= " + realB;
    }

}