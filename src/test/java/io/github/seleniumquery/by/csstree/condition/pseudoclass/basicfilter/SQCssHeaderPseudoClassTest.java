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

import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassAssertLocatorUtils.assertPseudoClassOnlySupportsPureXPathRegardlessOfNativeSupport;
import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;

public class SQCssHeaderPseudoClassTest {

    private static final String HEADER_PSEUDO = ":header";
    public static final String HEADER_XPATH_EXPRESSION = ".//*[" +
                "(self::h0 | self::h1 | self::h2 | self::h3 | self::h4 | " +
                 "self::h5 | self::h6 | self::h7 | self::h8 | self::h9)" +
            "]";

    @Test
    public void translate() {
        assertPseudo(HEADER_PSEUDO, SQCssHeaderPseudoClass.class);
    }

    @Test
    public void toSQLocator__REGARDLESS_of_driver_native_support() {
        assertPseudoClassOnlySupportsPureXPathRegardlessOfNativeSupport(
                new SQCssHeaderPseudoClass(),
                HEADER_PSEUDO,
                HEADER_XPATH_EXPRESSION
        );
    }

}