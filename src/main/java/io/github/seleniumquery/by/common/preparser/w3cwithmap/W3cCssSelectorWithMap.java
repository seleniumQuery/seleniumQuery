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

package io.github.seleniumquery.by.common.preparser.w3cwithmap;

import org.w3c.css.sac.Selector;

import io.github.seleniumquery.by.common.preparser.ArgumentMap;

/**
 * Represents a w3c parsed Selector along with an ArgumentMap.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class W3cCssSelectorWithMap {

    private ArgumentMap argumentMap;
    private Selector selector;

    public W3cCssSelectorWithMap(Selector selector, ArgumentMap argumentMap) {
        this.argumentMap = argumentMap;
        this.selector = selector;
    }

    public ArgumentMap getArgumentMap() {
        return argumentMap;
    }

    public Selector getSelector() {
        return selector;
    }

}
