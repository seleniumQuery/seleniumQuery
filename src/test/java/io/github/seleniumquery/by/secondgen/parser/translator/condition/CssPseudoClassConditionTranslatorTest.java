/*
 * Copyright (c) 2017 seleniumQuery authors
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

package io.github.seleniumquery.by.secondgen.parser.translator.condition;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.AstCssFunctionalPseudoClassHasNoArgumentsException;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.InvalidPseudoClassSelectorException;

public class CssPseudoClassConditionTranslatorTest {

    @Test(expected = AstCssFunctionalPseudoClassHasNoArgumentsException.class)
    public void extractIndexArgument__should_throw_exception_if_argument_is_NULL() {
        assertIndexArgumentIsNotValid(null);
    }

    @Test
    public void extractIndexArgument__should_throw_exception_if_argument_is_not_an_integer() {
        assertIndexArgumentIsNotValid("a");
        assertIndexArgumentIsNotValid("");
        assertIndexArgumentIsNotValid("+");
        assertIndexArgumentIsNotValid("-");
        assertIndexArgumentIsNotValid("+ 1");
        assertIndexArgumentIsNotValid(" ");
    }

    private void assertIndexArgumentIsNotValid(String indexString) {
        try {
            CssPseudoClassConditionTranslator.extractIndexArgument(indexString, "my-pseudo");
            fail("Should consider index argument `"+indexString+"` to be invalid.");
        } catch (InvalidPseudoClassSelectorException e) {
            assertThat(e.getMessage(), containsString(":my-pseudo()"));
            assertThat(e.getMessage(), containsString(indexString));
        }
    }

    @Test
    public void indexArgGeneratedSuccessfully() {
        assertIndexIsParsedCorrectly("0", 0);
        assertIndexIsParsedCorrectly("+0", 0);
        assertIndexIsParsedCorrectly("-0", 0);
        assertIndexIsParsedCorrectly(" +0", 0);
        assertIndexIsParsedCorrectly(" -0", 0);
        assertIndexIsParsedCorrectly("+0 ", 0);
        assertIndexIsParsedCorrectly("-0 ", 0);
        assertIndexIsParsedCorrectly("  +0   ", 0);
        assertIndexIsParsedCorrectly("  -0   ", 0);
        assertIndexIsParsedCorrectly("1", 1);
        assertIndexIsParsedCorrectly("+1", 1);
        assertIndexIsParsedCorrectly("  +1", 1);
        assertIndexIsParsedCorrectly("+1  ", 1);
        assertIndexIsParsedCorrectly("      +1     ", 1);
        assertIndexIsParsedCorrectly("-2", -2);
        assertIndexIsParsedCorrectly("-2", -2);
        assertIndexIsParsedCorrectly("  -2", -2);
        assertIndexIsParsedCorrectly("-2  ", -2);
        assertIndexIsParsedCorrectly("      -2     ", -2);
    }

    private void assertIndexIsParsedCorrectly(String stringArgument, int expectedArgument) {
        assertEquals(expectedArgument, CssPseudoClassConditionTranslator.extractIndexArgument(stringArgument, "not used in this test"));
    }

}
