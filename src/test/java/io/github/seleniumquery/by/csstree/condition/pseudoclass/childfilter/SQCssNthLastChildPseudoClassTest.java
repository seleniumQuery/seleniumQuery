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

import org.junit.Test;

import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.assertFunctionalPseudo;

public class SQCssNthLastChildPseudoClassTest {

    @Test
    public void translate() {
        assertFunctionalPseudo(":nth-last-child", SQCssNthLastChildPseudoClass.class);
    }

    // ":nth-last-child(2)"     -> "//*[last()+1- position() = 2]"
    // ":nth-last-child(3)"     -> "//*[last()+1- position() = 3]"
    // ":nth-last-child(5n)"    -> "//*[(last()+1- position() - 0) mod 5 = 0 and last()+1- position() >= 0]"
    // ":nth-last-child(-5n)"   -> "//*[(last()+1- position() - 0) mod -5 = 0 and last()+1- position() <= 0]"
    // ":nth-last-child(2n+3)"  -> "//*[(last()+1- position() - 3) mod 2 = 0 and last()+1- position() >= 3]"
    // ":nth-last-child(2n-3)"  -> "//*[(last()+1- position() - -3) mod 2 = 0 and last()+1- position() >= -3]"
    // ":nth-last-child(-2n+3)" -> "//*[(last()+1- position() - 3) mod -2 = 0 and last()+1- position() <= 3]"

}