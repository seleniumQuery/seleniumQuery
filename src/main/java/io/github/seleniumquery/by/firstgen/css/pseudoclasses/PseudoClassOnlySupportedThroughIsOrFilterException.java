/*
 * Copyright (c) 2016 seleniumQuery authors
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

package io.github.seleniumquery.by.firstgen.css.pseudoclasses;

/**
 * The pseudo-classes that throw this exception are NOT supported through direct use, like:
 *
 * $("some-other-selector:my-pseudo") // WON'T WORK
 *
 * But they do are supported through .is() or .filter():
 *
 * $("some-other-selector").is(":my-pseudo") // WILL WORK
 * $("some-other-selector").filter(":my-pseudo") // WILL WORK
 *
 *
 * That pseudo-classes are to supported directly only at the next (aka second gen) selector system version.
 *
 * @author acdcjunior
 * @since 0.12.0
 */
public class PseudoClassOnlySupportedThroughIsOrFilterException extends RuntimeException {

    private PseudoClassOnlySupportedThroughIsOrFilterException(String pseudoClass) {
        super(String.format(
                "Direct use of this pseudo-class is not supported.\n" +
                "\tUsing :%s *directly* is not supported.\n" +
                "\tUse it through .filter():\n" +
                "\t\tINSTEAD OF: $(\"my-selector:%s\")\n" +
                "\t\tUSE: $(\"my-selector\").filter(\":%s\").\n" +
                "\tUsing .is() is also supported: $(\"my-selector\").is(\":%s\")\n",
                pseudoClass, pseudoClass, pseudoClass, pseudoClass));
    }
    
    static void pseudoClassNotSupportedWhenUsedDirectly(String pseudoClass) {
        throw new PseudoClassOnlySupportedThroughIsOrFilterException(pseudoClass);
    }

}
