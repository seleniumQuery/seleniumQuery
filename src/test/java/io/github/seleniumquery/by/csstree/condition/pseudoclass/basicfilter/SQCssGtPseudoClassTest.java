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

import org.junit.Test;
import org.openqa.selenium.InvalidSelectorException;

import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.assertFunctionalPseudo;
import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.createPseudoClassSelectorAppliedToUniversalSelector;
import static io.github.seleniumquery.by.locator.SQLocatorUtilsTest.UNIVERSAL_SELECTOR_LOCATOR;
import static org.junit.Assert.fail;

public class SQCssGtPseudoClassTest {

    public static final String GT_PSEUDO = ":gt";

    @Test
    public void translate() {
        assertFunctionalPseudo(GT_PSEUDO, SQCssGtPseudoClass.class);
    }

    @Test
    public void toSQLocator__gt_should_throw_exception_if_argument_is_not_an_integer() {
        assertGtArgumentIsNotValid("a");
        assertGtArgumentIsNotValid("");
        assertGtArgumentIsNotValid("+");
        assertGtArgumentIsNotValid("-");
        assertGtArgumentIsNotValid("+ 1");
        assertGtArgumentIsNotValid(" ");
    }

    private void assertGtArgumentIsNotValid(String eqArgument) {
        try {
            gt(eqArgument).toSQLocator(UNIVERSAL_SELECTOR_LOCATOR);
            fail("Should consider *:gt("+eqArgument+") to be invalid.");
        } catch (InvalidSelectorException ignored) { }
    }

    private SQCssGtPseudoClass gt(String eqArgument) {
        return new SQCssGtPseudoClass(createPseudoClassSelectorAppliedToUniversalSelector(eqArgument));
    }

}