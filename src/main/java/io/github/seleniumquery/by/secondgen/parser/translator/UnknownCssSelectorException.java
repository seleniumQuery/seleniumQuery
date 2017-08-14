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

package io.github.seleniumquery.by.secondgen.parser.translator;

import org.w3c.css.sac.Selector;

import io.github.seleniumquery.SeleniumQueryException;

/**
 * Informs right away that the given selector is not known.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
class UnknownCssSelectorException extends SeleniumQueryException {

    UnknownCssSelectorException(Selector selector) {
        super("CSS selector \""+selector.getClass().getSimpleName() + "\" (type="+ selector.getSelectorType() + ") is invalid or not supported!");
    }

}
